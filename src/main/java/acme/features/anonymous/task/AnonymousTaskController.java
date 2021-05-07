package acme.features.anonymous.task;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/anonymous/task/")
public class AnonymousTaskController extends AbstractController<Anonymous, Task>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnonymousTaskListService anonymousTaskListService;
	
	@Autowired
	protected AnonymousTaskShowService anonymousTaskShowService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.anonymousTaskListService);
		super.addBasicCommand(BasicCommand.SHOW, this.anonymousTaskShowService);
	}
}
