package acme.features.anonymous.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkPlanShowService implements AbstractShowService<Anonymous, WorkPlan> {

    @Autowired
    AnonymousWorkPlanRepository anonymousWorkPlanRepository;

    @Override
    public boolean authorise(final Request<WorkPlan> request) {
        assert request != null;
        return true;
    }

    @Override
    public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        model.setAttribute("workload", entity.getWorkload());
        model.setAttribute("id", entity.getId());
        request.unbind(entity, model, "title", "begin", "end", "workload", "id", "executionPeriod", "tasks");
    }

    @Override
    public WorkPlan findOne(final Request<WorkPlan> request) {
        assert request != null;
        WorkPlan result;
        int id;
        id = request.getModel().getInteger("id");
        result = this.anonymousWorkPlanRepository.findWorkPlanById(id);

        return result;
    }
    
}