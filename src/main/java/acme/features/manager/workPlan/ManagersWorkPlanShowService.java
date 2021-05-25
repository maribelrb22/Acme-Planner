package acme.features.manager.workPlan;

import java.util.Calendar;
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
    ManagersWorkPlanRepository managersWorkPlanRepository;
    
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
		WorkPlan workplan = this.managersWorkPlanRepository.findWorkPlanById(workplanId);
		Managers managers = workplan.getManagers();
		Principal principal = request.getPrincipal();
		Boolean itsMine = managers.getUserAccount().getId() == principal.getAccountId();
		Boolean canPublish= itsMine && workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		//You can publish a workplan if you have created it and all tasks inside are public
		
		if(!workplan.getTasks().isEmpty()) {
			Date recommendedInitialDate = managersWorkPlanRepository.findInitialDateFirstTask(workplanId).get(0);
			Date recommendedEndDate= managersWorkPlanRepository.findEndDateLastTask(workplanId).get(0);
			
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
		}
		
		List<Task>taskList = managersWorkPlanRepository.findTasksAvailable(managers.getId(), workplanId).stream()
				.filter(x->!workplan.getTasks().contains(x))
				.collect(Collectors.toList());
		
		if(workplan.getIsPublic().booleanValue())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(Task::getIsPublic).collect(Collectors.toList());
			
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
        result = this.managersWorkPlanRepository.findWorkPlanById(id);

        return result;
	}

}
