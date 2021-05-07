package acme.features.manager.task;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/managers/task/")
public class ManagersTaskController extends AbstractController<Managers, Task> {

	@Autowired
	private ManagersTaskListService listService;
	
	@Autowired
	private ManagersTaskShowService showService;
	
	@Autowired
	private ManagersTaskCreateService createService;
	
	@Autowired
	private ManagersTaskUpdateService updateService;
	
	@Autowired
	private ManagersTaskDeleteService deleteService;
	
	@Autowired
	private ManagersTaskPublishService publishService;
	
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
	}
}
