package acme.features.administrator.workplanDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.forms.WorkplanDashboard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/workplan-dashboard/")
public class AdministratorWorkPlanDashboardController extends AbstractController<Administrator, WorkplanDashboard> {

	@Autowired
	protected AdministratorWorkPlanDashboardShowService administratorWorkPlanDashboardShowService;
	
	@PostConstruct
	protected void initialise() {
        super.addBasicCommand(BasicCommand.SHOW, this.administratorWorkPlanDashboardShowService);
    }
	
}
