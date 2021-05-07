package acme.features.manager.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagersTaskDeleteService implements AbstractDeleteService<Managers, Task>{
	
	@Autowired
	protected ManagersTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		final boolean result;
		Task task;
		int taskId;
		Managers Managers;
		Principal principal;
		
		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		Managers = task.getManagers();
		principal = request.getPrincipal();
		result = Managers.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "begin", "end","description");
		request.unbind(entity, model, "link", "isPublic", "workload");
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		Task task;
		int taskId;
		
		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		return task;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		final List<WorkPlan> workPlans = this.repository.findWorkPlansByTaskId(entity.getId());
		for(int i=0; i<workPlans.size();i++) {
			final WorkPlan w = workPlans.get(i);
			w.getTasks().remove(entity);
		}
		
		this.repository.deleteById(entity.getId());
		
	}

}
