
package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.WordClass;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorWordDeleteService implements AbstractDeleteService<Administrator, WordClass> {

	@Autowired
	protected AdministratorWordRepository repository;


	@Override
	public boolean authorise(final Request<WordClass> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<WordClass> request, final WordClass entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

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
		WordClass word;
		final int id = request.getModel().getInteger("id");
		word = this.repository.findOneWordById(id);
		return word;
	}

	@Override
	public void validate(final Request<WordClass> request, final WordClass entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<WordClass> request, final WordClass entity) {
		assert request != null;
		assert entity != null;
		final Spam spam = this.repository.findSpam();
		final Collection<WordClass> spamWords = spam.getSpamWords();
		spamWords.remove(entity);
		this.repository.deleteById(entity.getId());
	}

}
