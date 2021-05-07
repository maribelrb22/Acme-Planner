package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Word;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorWordListService implements AbstractListService<Administrator, Word>{
	
	@Autowired
	AdministratorWordRepository repository;
	
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
	public Collection<Word> findMany(final Request<Word> request) {
		assert request != null;
		
        Collection<Word> words;
        words = this.repository.findAllWords();
        return words;
	}

}
