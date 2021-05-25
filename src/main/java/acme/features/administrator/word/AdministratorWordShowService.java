package acme.features.administrator.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.WordClass;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWordShowService implements AbstractShowService<Administrator, WordClass>{

	@Autowired
	protected AdministratorWordRepository repository;
	
	@Override
	public boolean authorise(final Request<WordClass> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<WordClass> request, final WordClass entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
		
	}

	@Override
	public WordClass findOne(final Request<WordClass> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		WordClass res;
		res = this.repository.findOneWordById(id);
		return res;
	}

}
