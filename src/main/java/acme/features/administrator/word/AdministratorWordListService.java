package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.WordClass;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorWordListService implements AbstractListService<Administrator, WordClass>{
	
	@Autowired
	AdministratorWordRepository repository;
	
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
	public Collection<WordClass> findMany(final Request<WordClass> request) {
		assert request != null;
		
        Collection<WordClass> words;
        words = this.repository.findAllWords();
        return words;
	}

}
