package acme.features.manager.task;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagersTaskListService implements AbstractListService<Managers, Task>{
	
	@Autowired
	protected ManagersTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "executionPeriod", "workload", "isPublic");
		
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;
		Collection<Task> result;
		final Principal principal = request.getPrincipal();
		result = this.repository.findAllManagersTasks(principal.getUsername()).stream().collect(Collectors.toList());
		return result;
	}
}
