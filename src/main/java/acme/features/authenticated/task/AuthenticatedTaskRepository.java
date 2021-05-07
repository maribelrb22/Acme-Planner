package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	@Query("SELECT t FROM Task t WHERE t.isPublic = true")
	Collection<Task> findManyPublicTasks();

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneTaskById(int id);
}
