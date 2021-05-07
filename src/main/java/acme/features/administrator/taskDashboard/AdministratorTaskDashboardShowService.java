package acme.features.administrator.taskDashboard;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.TaskDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTaskDashboardShowService implements AbstractShowService<Administrator, TaskDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorTaskDashboardRepository repository;

	// AbstractShowService<Administrator, TaskDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<TaskDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<TaskDashboard> request, final TaskDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberOfPublicTasks", "totalNumberOfPrivateTasks", // 
			"totalNumberOfFinishedTasks", "totalNumberOfPendingTasks", //
			"averageNumberOfTaskExecutionPeriod", "deviationOfTaskExecutionPeriod", // 
			"minTaskExecutionPeriod", "maxTaskExecutionPeriod", // 
			"averageNumberOfWorkload", "deviationOfWorkload", // 
			"minWorkload", "maxWorkload");
	}

	@Override
	public TaskDashboard findOne(final Request<TaskDashboard> request) {
		assert request != null;

		TaskDashboard result;
		Integer	totalNumberOfPublicTasks;
		Integer	totalNumberOfPrivateTasks;
		Integer	totalNumberOfFinishedTasks;
		Integer	totalNumberOfPendingTasks;
		Double	averageNumberOfTaskExecutionPeriod;
		Double	deviationOfTaskExecutionPeriod;
		Double	minTaskExecutionPeriod;
		Double	maxTaskExecutionPeriod;
		Double	averageNumberOfWorkload;
		Double	deviationOfWorkload;
		Double	minWorkload;
		Double	maxWorkload;

		totalNumberOfPublicTasks = this.repository.totalNumberOfPublicTasks();
		totalNumberOfPrivateTasks = this.repository.totalNumberOfPrivateTasks();
		totalNumberOfFinishedTasks = this.repository.totalNumberOfFinishedTasks(new Date());
		totalNumberOfPendingTasks = this.repository.totalNumberOfPendingTasks(new Date());
		averageNumberOfTaskExecutionPeriod = this.repository.averageNumberOfTaskExecutionPeriod();
		deviationOfTaskExecutionPeriod = this.repository.deviationOfTaskExecutionPeriod();
		minTaskExecutionPeriod = this.repository.minTaskExecutionPeriod();
		maxTaskExecutionPeriod = this.repository.maxTaskExecutionPeriod();
		averageNumberOfWorkload = this.repository.averageNumberOfWorkload();
		deviationOfWorkload = this.repository.deviationOfWorkload();
		minWorkload = this.repository.minWorkload();
		maxWorkload = this.repository.maxWorkload();
		
		result = new TaskDashboard();
		result.setTotalNumberOfPublicTasks(totalNumberOfPublicTasks);
		result.setTotalNumberOfPrivateTasks(totalNumberOfPrivateTasks);
		result.setTotalNumberOfFinishedTasks(totalNumberOfFinishedTasks);
		result.setTotalNumberOfPendingTasks(totalNumberOfPendingTasks);
		result.setAverageNumberOfTaskExecutionPeriod(averageNumberOfTaskExecutionPeriod);
		result.setDeviationOfTaskExecutionPeriod(deviationOfTaskExecutionPeriod);
		result.setMinTaskExecutionPeriod(minTaskExecutionPeriod);
		result.setMaxTaskExecutionPeriod(maxTaskExecutionPeriod);
		result.setAverageNumberOfWorkload(averageNumberOfWorkload);
		result.setDeviationOfWorkload(deviationOfWorkload);
		result.setMinWorkload(minWorkload);
		result.setMaxWorkload(maxWorkload);

		return result;
	}

}