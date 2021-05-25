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
	protected ManagersWorkPlanListService managersWorkPlanListService;
	
	@Autowired
	protected ManagersWorkPlanShowService managersWorkPlanShowService;
	
	@Autowired
	protected ManagersWorkPlanCreateService managersWorkPlanCreateService;
	
	@Autowired
	protected ManagersWorkPlanDeleteService managersWorkPlanDeleteService;
	
	@Autowired 
	protected ManagersWorkPlanPublishService managersWorkPlanPublishService;
	
	@Autowired 
	protected ManagersWorkPlanEditService managersWorkPlanEditService;
	
	@Autowired
	protected ManagersWorkPlanAddTaskService managersWorkPlanAddTaskService;
	
	@Autowired
	protected ManagersWorkPlanRemoveTaskService managersWorkPlanRemoveTaskService;
	
	@Autowired
	protected ManagersWorkPlanPrivatizeService managersWorkPlanPrivatizeService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.managersWorkPlanListService);
		super.addBasicCommand(BasicCommand.SHOW, this.managersWorkPlanShowService);
		super.addBasicCommand(BasicCommand.CREATE, this.managersWorkPlanCreateService);
		super.addBasicCommand(BasicCommand.DELETE,this.managersWorkPlanDeleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.managersWorkPlanEditService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.managersWorkPlanPublishService);
		super.addCustomCommand(CustomCommand.ADD_TASK, BasicCommand.UPDATE, this.managersWorkPlanAddTaskService);
		super.addCustomCommand(CustomCommand.REMOVE_TASK, BasicCommand.UPDATE, this.managersWorkPlanRemoveTaskService);
		super.addCustomCommand(CustomCommand.PRIVATIZE, BasicCommand.UPDATE, this.managersWorkPlanPrivatizeService);
	}


}
