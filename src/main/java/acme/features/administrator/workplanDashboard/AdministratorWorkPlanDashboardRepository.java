package acme.features.administrator.workplanDashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWorkPlanDashboardRepository extends AbstractRepository{

	@Query("select count(w) from WorkPlan w where w.isPublic = 1")
	Integer totalNumberOfPublicWorkplans();
	
	@Query("select count(w) from WorkPlan w where w.isPublic = 0")
	Integer totalNumberOfPrivateWorkplans();
	
	@Query("select count(w) from WorkPlan w where ?1 > w.end")
	Integer totalNumberOfFinishedWorkplans(Date today);
	
	@Query("select count(w) from WorkPlan w where ?1 <= w.end")
	Integer totalNumberOfNonFinishedWorkplans(Date today);
	
	@Query("select avg(w.executionPeriod) from WorkPlan w")
	Double averageNumberOfPeriods();

	@Query("select min(w.executionPeriod) from WorkPlan w")
	Double minimumNumberOfPeriods();

	@Query("select max(w.executionPeriod) from WorkPlan w")
	Double maximumNumberOfPeriods();

	@Query("select avg(w.workload) from WorkPlan w")
	Double averageNumberOfWorkloads();
	
	@Query("select min(w.workload) from WorkPlan w")
	Double minimumNumberOfWorkloads();

	@Query("select max(w.workload) from WorkPlan w")
	Double maximumNumberOfWorkloads();

	@Query("select stddev(w.workload) from WorkPlan w")
	Double deviationOfWorkloads();

	@Query("select stddev(w.executionPeriod) from WorkPlan w")
	Double deviationOfExecutionPeriods();
	


}
