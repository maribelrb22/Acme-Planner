package acme.features.manager.workPlan;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagersWorkPlanAddTaskService implements AbstractUpdateService<Managers, WorkPlan> {

	@Autowired
	private ManagersWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		final boolean result;
		WorkPlan workplan;
		int workplanId;
		Managers Managers;
		Principal principal;
		
		workplanId=request.getModel().getInteger("id");
		workplan=this.repository.findWorkPlanById(workplanId);
		Managers = workplan.getManagers();
		principal = request.getPrincipal();
		
		result = (Managers.getUserAccount().getId() == principal.getAccountId());
		return result;
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);			
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
		
	    request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {		
		final int id = request.getModel().getInteger("id");
		return this.repository.findWorkPlanById(id);
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(request.getModel().hasAttribute("taskSelected")) {
			
			final WorkPlan wp = this.repository.findWorkPlanById(entity.getId());
			final Task task = (Task) this.taskRepository.findById(request.getModel().getInteger("taskSelected")).orElse(null);
			final Collection<Task> ls = wp.getTasks();
			
			if(Boolean.TRUE.equals(wp.getIsPublic())) 
				errors.state(request, task!=null && Boolean.TRUE.equals(task.getIsPublic()), "taskSelected", "Managers.workplan.form.addTask.error.public");

			errors.state(request, task!=null && task.getBegin().after(wp.getBegin()) && task.getEnd().before(wp.getEnd()) && wp.getExecutionPeriod() >= 
				(ls.stream().mapToDouble(Task::getExecutionPeriod).sum() + task.getExecutionPeriod()), "taskSelected", 
				"Managers.workplan.form.addTask.error.executionPeriod");
			
			final Managers Managers = wp.getManagers();
			final Principal principal = request.getPrincipal();
			final Boolean itsMine = Managers.getUserAccount().getId() == principal.getAccountId();
			final Boolean canPublish= itsMine && wp.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !wp.getIsPublic();
			
			List<Task>taskList = this.repository.findTasksAvailable(Managers.getId(), wp.getId()).stream().filter(x->!wp.getTasks().contains(x)).collect(Collectors.toList());
				taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
			
			request.getModel().setAttribute("ItsMine", itsMine);
	        request.getModel().setAttribute("canPublish", canPublish);
	        request.getModel().setAttribute("tasks", wp.getTasks());
			request.getModel().setAttribute("tasksEneabled", taskList);
			
		}else {
			errors.state(request, false , "taskSelected", "Managers.workplan.form.addTask.error.task");
		}
		
		request.unbind(entity, request.getModel(),  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");

		if(errors.hasErrors()) {
			request.getModel().setAttribute("errorsAdd", true);
		}
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findWorkPlanById(entity.getId());
		final Task task = (Task) this.taskRepository.findById(request.getModel().getInteger("taskSelected")).orElse(null);
		final Collection<Task> ls = wp.getTasks();
		ls.add(task);
		wp.setTasks(ls);
		wp.setWorkload();
		
		this.repository.save(wp);
		
	}

}
