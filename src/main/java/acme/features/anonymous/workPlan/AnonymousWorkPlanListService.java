package acme.features.anonymous.workPlan;


import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan> {

    @Autowired
    AnonymousWorkPlanRepository anonymousWorkPlanRepository;

    @Override
    public boolean authorise(final Request<WorkPlan> request) {
        assert request != null;
        return true;
    }

    @Override
    public void unbind(final Request<WorkPlan> request,final WorkPlan entity,final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model,  "isPublic", "begin", "end", "tasks", "title");
    }

    public Boolean isFinished(WorkPlan w) {
        Date now;
        now = new Date();
        return now.after(w.getEnd());
    }

    @Override 
    public Collection<WorkPlan> findMany(Request<WorkPlan> request) { 
        assert request != null; 
        Collection<WorkPlan> result; 
        result = this.anonymousWorkPlanRepository.findPublicWorkPlan();
        return result.stream().filter(x->isFinished(x).equals(false)).collect(Collectors.toList());
    }
}
