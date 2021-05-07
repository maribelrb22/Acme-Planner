package acme.features.manager.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Managers;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/managers/work-plan/")
public class ManagersWorkPlanController extends AbstractController<Managers, WorkPlan> {
	
	@Autowired
	protected ManagersWorkPlanListService ManagersWorkPlanListService;
	
	@Autowired
	protected ManagersWorkPlanShowService ManagersWorkPlanShowService;
	
	@Autowired
	protected ManagersWorkPlanCreateService ManagersWorkPlanCreateService;
	
	@Autowired
	protected ManagersWorkPlanDeleteService ManagersWorkPlanDeleteService;
	
	@Autowired 
	protected ManagersWorkPlanPublishService ManagersWorkPlanPublishService;
	
	@Autowired 
	protected ManagersWorkPlanEditService ManagersWorkPlanEditService;
	
	@Autowired
	protected ManagersWorkPlanAddTaskService ManagersWorkPlanAddTaskService;
	
	@Autowired
	protected ManagersWorkPlanRemoveTaskService ManagersWorkPlanRemoveTaskService;
	
	@Autowired
	protected ManagersWorkPlanPrivatizeService ManagersWorkPlanPrivatizeService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.ManagersWorkPlanListService);
		super.addBasicCommand(BasicCommand.SHOW, this.ManagersWorkPlanShowService);
		super.addBasicCommand(BasicCommand.CREATE, this.ManagersWorkPlanCreateService);
		super.addBasicCommand(BasicCommand.DELETE,this.ManagersWorkPlanDeleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.ManagersWorkPlanEditService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.ManagersWorkPlanPublishService);
		super.addCustomCommand(CustomCommand.ADD_TASK, BasicCommand.UPDATE, this.ManagersWorkPlanAddTaskService);
		super.addCustomCommand(CustomCommand.REMOVE_TASK, BasicCommand.UPDATE, this.ManagersWorkPlanRemoveTaskService);
		super.addCustomCommand(CustomCommand.PRIVATIZE, BasicCommand.UPDATE, this.ManagersWorkPlanPrivatizeService);
	}


}
