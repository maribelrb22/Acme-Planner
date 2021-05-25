package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.services.SpamService;

@Service
public class ManagersTaskPublishService implements AbstractUpdateService<Managers, Task>{
	
	@Autowired
	protected ManagersTaskRepository repository;
	
	@Autowired
	protected SpamService spam;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		final boolean result;
		Task task;
		int taskId;
		Managers manager;
		Principal principal;
		
		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		manager = task.getManagers();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();
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
		
		request.unbind(entity, model, "title", "begin", "end","description","link", "isPublic", "workload", "executionPeriod");
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
		if(entity.getIsPublic().equals(true)) {
			final int workPlansPublic=this.repository.findNumberOfPublicWorkPlansByTaskId(entity.getId());
			errors.state(request, workPlansPublic==0, "title", "Managers.task.form.error.worPlan-public");
		}
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		final Task task = this.repository.findOneTaskById(entity.getId());
		
		if(task.getIsPublic().equals(false)) {
			task.setIsPublic(true);
		}else {
			task.setIsPublic(false);	
		}
		this.repository.save(task);
		
	}
}
