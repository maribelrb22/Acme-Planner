package acme.features.manager.workPlan;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagersWorkPlanRepository extends AbstractRepository {

	@Query("select w from WorkPlan w where (w.isPublic=1 or w.managers.id =?1)")
	public Collection<WorkPlan>getAllWorkPlans(int id);
	
	@Query("select w from WorkPlan w where w.id =?1")
	public WorkPlan findWorkPlanById(int id);

	@Query("select m from Managers m where m.id = ?1")
	public Managers findOneManagersById(int activeRoleId);

	@Query("select t from Task t where (t.isPublic=1 or t.managers.id =?1)")
	public Collection<Task> findTasksAvailable(int id, int idWP);
	
	@Query("select t.begin from WorkPlan w join w.tasks t where w.id=?1 order by t.begin asc" )
	public List<Date> findInitialDateFirstTask(int id);
	
	@Query("select t.end from WorkPlan w join w.tasks t where w.id=?1 order by t.end desc" )
	public List<Date> findEndDateLastTask(int id);
	
}
