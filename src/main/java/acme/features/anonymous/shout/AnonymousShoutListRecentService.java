package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListRecentService implements AbstractListService<Anonymous, Shout>{

	@Autowired
    AnonymousShoutRepository repository;
	
	@Override
	public boolean authorise(Request<Shout> request) {
		 assert  request != null;
	     return true;
	}

	@Override
	public void unbind(Request<Shout> request, Shout entity, Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "author","text", "moment");
		
	}

	@Override
	public Collection<Shout> findMany(Request<Shout> request) {
		assert request != null;
        Collection<Shout> result;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);//Obtenemos la fecha de hoy y le restamos un mes para obtener los shouts más recientes.
        result = this.repository.findManyRecent(calendar.getTime());
        return result;
	}

}
