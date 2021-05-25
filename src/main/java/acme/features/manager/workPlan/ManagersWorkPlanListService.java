package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagersWorkPlanListService  implements AbstractListService<Managers, WorkPlan>{

    @Autowired
    ManagersWorkPlanRepository managersWorkPlanRepository;
	
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
	     assert request != null;
	     assert entity != null;
	     assert model != null;
	     model.setAttribute("Managers",entity.getManagers().getUserAccount().getUsername() );
	     request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");

	}

	@Override
	public Collection<WorkPlan> findMany(Request<WorkPlan> request) {
		assert request!=null;
		return managersWorkPlanRepository.getAllWorkPlans(request.getPrincipal().getActiveRoleId());
	}

}
