package acme.features.manager.task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagersTaskRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Task t WHERE t.Managers.userAccount.username = ?1 ")
	Collection<Task> findAllManagersTasks(String Managers);

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select m from Managers m where m.id = ?1")
	Managers findOneManagersById(int id);
	
	@Query("select w from WorkPlan w join w.tasks t where t.id = ?1")
	List<WorkPlan> findWorkPlansByTaskId(int id);
	
	@Query("select count(w) from WorkPlan w join w.tasks t where t.id = ?1 and w.isPublic = false")
	int findPivateWorkPlansByTaskId(int id);
	
	@Query("select count(w) from WorkPlan w join w.tasks t where t.id = ?1 and w.isPublic = true")
	int findNumberOfPublicWorkPlansByTaskId(int id);


}