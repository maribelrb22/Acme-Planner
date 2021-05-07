package acme.features.authenticated.manager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Managers;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/managers/")
public class AuthenticatedManagersController extends AbstractController<Authenticated, Managers>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedManagersCreateService	createService;

		@Autowired
		protected AuthenticatedManagersUpdateService	updateService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		}
}
