package acme.features.manager.workPlan;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.services.SpamService;

@Service
public class ManagersWorkPlanEditService implements AbstractUpdateService<Managers, WorkPlan> {

	@Autowired
	private ManagersWorkPlanRepository repository;
	
	@Autowired
	protected SpamService spam;

	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		final boolean result;
		WorkPlan workplan;
		int workplanId;
		Managers managers;
		Principal principal;
		
		workplanId=request.getModel().getInteger("id");
		workplan=this.repository.findWorkPlanById(workplanId);
		managers = workplan.getManagers();
		principal = request.getPrincipal();
		
		result = (managers.getUserAccount().getId() == principal.getAccountId());
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

	try{
		final Date now = new Date();
		final Date begin = entity.getBegin();
		final Date end = entity.getEnd();

		final boolean titleSpam = this.spam.isItSpam(entity.getTitle());

		if(!errors.hasErrors("begin") && !errors.hasErrors("end")) {
			errors.state(request, end.after(begin), "begin", "Managers.workplan.form.error.must-be-before-end");
		}
		if(!errors.hasErrors("begin")) {
			errors.state(request, begin.after(now), "begin", "Managers.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, end.after(now), "end", "Managers.workplan.form.error.must-be-in-future");
		}
		if(!errors.hasErrors("begin") && !errors.hasErrors("end")) {
			errors.state(request, begin.before(end), "end", "Managers.workplan.form.error.must-be-after-begin");
		}
		if(!errors.hasErrors("title")) {
			errors.state(request, !titleSpam,  "title", "Managers.workplan.form.error.spam");
		}


		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findWorkPlanById(workplanId);
		final Managers managers = workplan.getManagers();
		final Principal principal = request.getPrincipal();
		final Boolean itsMine = managers.getUserAccount().getId() == principal.getAccountId();
		final Boolean canPublish= itsMine && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();

		
		if(!workplan.getTasks().isEmpty()) {
			final Date recommendedInitialDate = this.repository.findInitialDateFirstTask(workplanId).get(0);
			final Date recommendedEndDate= this.repository.findEndDateLastTask(workplanId).get(0);

			//Add or substract one day in miliseconds
			final Calendar cal1 = Calendar.getInstance();
            cal1.setTime(recommendedInitialDate);
            cal1.add(Calendar.DAY_OF_MONTH, -1);
            cal1.set(Calendar.HOUR, 8);

            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(recommendedEndDate);
            cal2.add(Calendar.DAY_OF_MONTH, +1);
            cal2.set(Calendar.HOUR, 17);
			
			request.getModel().setAttribute("recommendedInitialDate", cal1.getTime().toString());
			request.getModel().setAttribute("recommendedEndDate", cal2.getTime().toString());
		}else {
			final Task t = this.repository.findTasksAvailable(managers.getId(), workplanId).stream().filter(x->!workplan.getTasks().contains(x)).collect(Collectors.toList()).get(0);
			request.getModel().setAttribute("recommendedInitialDate", t.getBegin());
		}		
		List<Task>taskList = this.repository.findTasksAvailable(managers.getId(), workplanId).stream().filter(x->!workplan.getTasks().contains(x)).collect(Collectors.toList());//cambiar publicas por todas
		if(workplan.getIsPublic().booleanValue())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(Task::getIsPublic).collect(Collectors.toList());
		
		request.getModel().setAttribute("ItsMine", itsMine);
		request.getModel().setAttribute("canPublish", canPublish);
		request.getModel().setAttribute("tasks", workplan.getTasks());
		request.getModel().setAttribute("tasksEneabled", taskList);

		final Collection<Task> ls = workplan.getTasks();
		errors.state(request, ((end.getTime() - begin.getTime()) / (1000 * 3600))
						>= (ls.stream().mapToDouble(Task::getExecutionPeriod).sum()), "executionPeriod",
				"Managers.workplan.form.addTask.error.executionPeriod");


	}catch(final Exception e){
		errors.state(request, false, "begin", "Please, introduce a correct Date");
		errors.state(request, false, "end", "Please, introduce a correct Date");
	}
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findWorkPlanById(entity.getId());
		wp.setEnd(entity.getEnd());
		wp.setBegin(entity.getBegin());
		wp.setTitle(entity.getTitle());
		wp.setExecutionPeriod();
		this.repository.save(wp);
	}

}
