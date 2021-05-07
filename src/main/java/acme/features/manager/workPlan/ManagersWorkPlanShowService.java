package acme.features.manager.workPlan;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagersWorkPlanShowService implements AbstractShowService<Managers, WorkPlan>{

    @Autowired
    ManagersWorkPlanRepository ManagersWorkPlanRepository;
    
	@Autowired
	AnonymousTaskRepository taskRepository;
    
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
        
        int workplanId = request.getModel().getInteger("id");
		WorkPlan workplan = this.ManagersWorkPlanRepository.findWorkPlanById(workplanId);
		Managers Managers = workplan.getManagers();
		Principal principal = request.getPrincipal();
		Boolean itsMine = Managers.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= itsMine && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		//You can publish a workplan if you have created it and all tasks inside are public
		
		if(workplan.getTasks().size()>0) {
			Date recommendedInitialDate = ManagersWorkPlanRepository.findInitialDateFirstTask(workplanId).get(0);
			Date recommendedEndDate= ManagersWorkPlanRepository.findEndDateLastTask(workplanId).get(0);
			
			//Add or substract one day in miliseconds
			recommendedInitialDate = new Date(recommendedInitialDate.getTime() - (1000 * 60 * 60 * 24));
			recommendedInitialDate.setHours(8);
			recommendedInitialDate.setMinutes(0);

			recommendedEndDate= new Date(recommendedEndDate.getTime() + (1000 * 60 * 60 * 24));
			recommendedEndDate.setHours(17);
			recommendedEndDate.setMinutes(0);

			model.setAttribute("recommendedInitialDate", recommendedInitialDate.toString());
			model.setAttribute("recommendedEndDate", recommendedEndDate.toString());
		}
		
		List<Task>taskList = ManagersWorkPlanRepository.findTasksAvailable(Managers.getId(), workplanId).stream()
				.filter(x->!workplan.getTasks().contains(x))
				.collect(Collectors.toList());
		
		if(workplan.getIsPublic())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
			
		model.setAttribute("tasksEneabled", taskList);
        
	    request.unbind(entity, model,  "isPublic", "begin", "end", "tasks","title","executionPeriod","workload");
        model.setAttribute("ItsMine", itsMine);
        model.setAttribute("canPublish", canPublish);
	}

	@Override
	public WorkPlan findOne(Request<WorkPlan> request) {
		assert request != null;
        WorkPlan result;
        int id;
        id = request.getModel().getInteger("id");
        result = this.ManagersWorkPlanRepository.findWorkPlanById(id);

        return result;
	}

}
