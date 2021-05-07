
package acme.features.administrator.word;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.Word;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/word/")
public class AdministratorWordController extends AbstractController<Administrator, Word> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorWordListService administratorWordListService;
	
	@Autowired
	protected AdministratorWordShowService administratorWordShowService;

	@Autowired
	protected AdministratorWordCreateService administratorWordCreateService;
	
	@Autowired
	protected AdministratorWordDeleteService administratorWordDeleteService;
	
	@Autowired
	protected AdministratorWordUpdateService administratorWordUpdateService;
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.administratorWordListService);
		super.addBasicCommand(BasicCommand.SHOW, this.administratorWordShowService);
		super.addBasicCommand(BasicCommand.CREATE, this.administratorWordCreateService);
		super.addBasicCommand(BasicCommand.DELETE, this.administratorWordDeleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.administratorWordUpdateService);

	}

}
