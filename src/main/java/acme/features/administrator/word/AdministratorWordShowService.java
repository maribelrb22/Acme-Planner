package acme.features.administrator.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Word;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWordShowService implements AbstractShowService<Administrator, Word>{

	@Autowired
	protected AdministratorWordRepository repository;
	
	@Override
	public boolean authorise(final Request<Word> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Word> request, final Word entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
		
	}

	@Override
	public Word findOne(final Request<Word> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		Word res;
		res = this.repository.findOneWordById(id);
		return res;
	}

}
