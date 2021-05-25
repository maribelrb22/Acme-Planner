
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
import acme.framework.services.AbstractCreateService;
import acme.services.SpamService;

@Service
public class AdministratorWordCreateService implements AbstractCreateService<Administrator, WordClass> {

	@Autowired
	protected AdministratorWordRepository	repository;

	@Autowired
	protected SpamService					spamService;


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
	public WordClass instantiate(final Request<WordClass> request) {
		assert request != null;
		WordClass result;

		result = new WordClass();
		result.setWord("Word");

		return result;
	}

	@Override
	public void validate(final Request<WordClass> request, final WordClass entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean spam = this.spamService.isASpamWord(entity.getWord());
		errors.state(request, Boolean.FALSE.equals(spam), "word", "administrator.word.form.error.exists");

	}

	@Override
	public void create(final Request<WordClass> request, final WordClass entity) {
		assert request != null;
		assert entity != null;
		final Spam spam = this.repository.findSpam();
		final Collection<WordClass> spamWords = spam.getSpamWords();
		spamWords.add(entity);
		this.repository.save(entity);

	}

}
