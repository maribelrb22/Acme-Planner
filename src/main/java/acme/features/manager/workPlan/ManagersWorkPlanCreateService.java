package acme.features.manager.workPlan;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;
import acme.services.SpamService;

@Service
public class ManagersWorkPlanCreateService implements AbstractCreateService<Managers, WorkPlan>{

	@Autowired
	ManagersWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
	@Autowired
	protected SpamService spam;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request!=null;
		return true;
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
		model.setAttribute("ItsMine", true);

		
	}

	@Override
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		assert request != null;

		final Managers Managers = this.repository.findOneManagersById(request.getPrincipal().getActiveRoleId());
		final WorkPlan workPlan = new WorkPlan();
		workPlan.setManagers(Managers);
		workPlan.setTasks(new ArrayList<Task>());
		
		return workPlan;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert errors != null;
		assert entity != null;
		
		final Date now =new Date();
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
		
		request.getModel().setAttribute("ItsMine", true);


	}

	@Override
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request!= null;
		assert entity!= null;
		entity.setWorkload();
		entity.setExecutionPeriod();
		this.repository.save(entity);
	}

}
