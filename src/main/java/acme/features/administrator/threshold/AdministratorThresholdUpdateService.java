package acme.features.administrator.threshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold>{
	
	@Autowired
	protected AdministratorThresholdRepository repository;

	@Override
	public boolean authorise(final Request<Threshold> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "thresholdNumber");
	}

	@Override
	public Threshold findOne(final Request<Threshold> request) {
        assert request != null;
		
		Threshold result;
		double threshold;
		
		threshold = this.repository.findThreshold();
		
		result = new Threshold();
		result.setThresholdNumber(threshold);
		
		return result;
	}

	@Override
	public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		errors.state(request, entity.getThresholdNumber() <= 100 && entity.getThresholdNumber() >= 0, 
			"thresholdNumber", "administrator.threshold.form.error.exceded");
	}

	@Override
	public void update(final Request<Threshold> request, final Threshold entity) {
		assert request != null;
		assert entity != null;
		
		Spam spam;
		
		spam = this.repository.findSpam();
		spam.setThreshold(entity.getThresholdNumber());

		this.repository.save(spam);
	}

}