package acme.features.administrator.threshold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.spam.Threshold;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/threshold/")
public class AdministratorThresholdController extends AbstractController<Administrator, Threshold> {
	
	// Internal state ---------------------------------------------------------
		
	@Autowired
	protected AdministratorThresholdShowService administratorThresholdShowService;
	
	@Autowired
	protected AdministratorThresholdUpdateShowService administratorThresholdUpdateShowService;
	
	@Autowired
	protected AdministratorThresholdUpdateService administratorThresholdUpdateService;
	
	@Autowired
	protected AdministratorThresholdUpdateResetService administratorThresholdUpdateResetService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.administratorThresholdShowService);
		super.addCustomCommand(CustomCommand.UPDATE_SHOW, BasicCommand.SHOW, this.administratorThresholdUpdateShowService);
		super.addBasicCommand(BasicCommand.UPDATE, this.administratorThresholdUpdateService);
		super.addCustomCommand(CustomCommand.RESET, BasicCommand.UPDATE, this.administratorThresholdUpdateResetService);
	}

}
