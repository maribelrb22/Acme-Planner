package acme.features.administrator.workplanDashboard;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.WorkplanDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWorkPlanDashboardShowService implements AbstractShowService<Administrator, WorkplanDashboard> {

	@Autowired
	AdministratorWorkPlanDashboardRepository administratorWorkPlanDashboardRepository;
	
	@Override
	public boolean authorise(Request<WorkplanDashboard> request) {
		assert request != null;
        return true;
	}

	@Override
	public void unbind(Request<WorkplanDashboard> request, WorkplanDashboard entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, //
        		"totalNumberOfPublicWorkplans", "totalNumberOfPrivateWorkplans", //
        		"totalNumberOfFinishedWorkplans", "totalNumberOfNonFinishedWorkplans", //
        		"averageNumberOfPeriods", "deviationOfExecutionPeriods", "minimumNumberOfPeriods", "maximumNumberOfPeriods",  //
        		"averageNumberOfWorkloads", "deviationOfWorkloads", "minimumNumberOfWorkloads", "maximumNumberOfWorkloads");
	}

	@Override
	public WorkplanDashboard findOne(Request<WorkplanDashboard> request) {
		assert request != null;
		WorkplanDashboard result;
		
		Integer totalNumberOfPublicWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfPublicWorkplans();
		Integer totalNumberOfPrivateWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfPrivateWorkplans();
		Integer totalNumberOfFinishedWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfFinishedWorkplans(new Date());
		Integer totalNumberOfNonFinishedWorkplans = this.administratorWorkPlanDashboardRepository.totalNumberOfNonFinishedWorkplans(new Date());
		Double averageNumberOfWorkloads = this.administratorWorkPlanDashboardRepository.averageNumberOfWorkloads();
		Double minimumNumberOfWorkloads = this.administratorWorkPlanDashboardRepository.minimumNumberOfWorkloads();
		Double maximumNumberOfWorkloads = this.administratorWorkPlanDashboardRepository.maximumNumberOfWorkloads();
		Double deviationOfWorkloads = this.administratorWorkPlanDashboardRepository.deviationOfWorkloads();
		Double averageNumberOfPeriods = this.administratorWorkPlanDashboardRepository.averageNumberOfPeriods();
		Double minimumNumberOfPeriods = this.administratorWorkPlanDashboardRepository.minimumNumberOfPeriods();
		Double maximumNumberOfPeriods = this.administratorWorkPlanDashboardRepository.maximumNumberOfPeriods();
		Double deviationOfExecutionPeriods = this.administratorWorkPlanDashboardRepository.deviationOfExecutionPeriods();
		
		result = new WorkplanDashboard();
		result.setTotalNumberOfPublicWorkplans(totalNumberOfPublicWorkplans);
		result.setTotalNumberOfPrivateWorkplans(totalNumberOfPrivateWorkplans);
		result.setTotalNumberOfFinishedWorkplans(totalNumberOfFinishedWorkplans);
		result.setTotalNumberOfNonFinishedWorkplans(totalNumberOfNonFinishedWorkplans);
		result.setAverageNumberOfWorkloads(averageNumberOfWorkloads);
		result.setMinimumNumberOfWorkloads(minimumNumberOfWorkloads);
		result.setMaximumNumberOfWorkloads(maximumNumberOfWorkloads);
		result.setAverageNumberOfPeriods(averageNumberOfPeriods);
		result.setMinimumNumberOfPeriods(minimumNumberOfPeriods);
		result.setMaximumNumberOfPeriods(maximumNumberOfPeriods);
		result.setDeviationOfWorkloads(deviationOfWorkloads);
		result.setDeviationOfExecutionPeriods(deviationOfExecutionPeriods);
		
		return result;
	}

	
}
