package acme.features.anonymous.shout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.shouts.Shout;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/shout/")
public class AnonymousShoutController extends AbstractController<Anonymous, Shout> {

	//Internal state
	@Autowired
	private AnonymousShoutListService listService;
	
	@Autowired
	private AnonymousShoutCreateService createService;
	
	@Autowired 
	private AnonymousShoutListRecentService listRecentService;
	
	//Constructors
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.LIST_RECENT, BasicCommand.LIST , this.listRecentService);
	}
	
}
