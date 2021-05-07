package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfPublicTasks;
	Integer						totalNumberOfPrivateTasks;
	Integer						totalNumberOfFinishedTasks;
	Integer						totalNumberOfPendingTasks;
	Double						averageNumberOfTaskExecutionPeriod;
	Double						deviationOfTaskExecutionPeriod;
	Double						minTaskExecutionPeriod;
	Double						maxTaskExecutionPeriod;
	Double						averageNumberOfWorkload;
	Double						deviationOfWorkload;
	Double						minWorkload;
	Double						maxWorkload;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
