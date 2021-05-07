package acme.features.anonymous.task;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task>{

	@Autowired
	AnonymousTaskRepository anonymousTaskRepository;
	
	//Check that the request is authorised
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}

	//Select which attributes must be transferred to the model
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        
        request.unbind(entity, model, "title", "workload", "executionPeriod");
		
	}
	
	//To return a collection of entities
	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;
        Collection<Task> tasks;
        tasks = this.anonymousTaskRepository.findPublicTask();
        return tasks.stream().filter(x->x.isFinished().equals(false)).collect(Collectors.toList());
	}

}
