package acme.entities.workPlan;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Managers;
import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{
	
	// Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
		
	// Attributes

	@NotBlank
	protected String title;
	
	protected Boolean isPublic;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date begin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<@Valid Task> tasks;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Managers managers;
		
	protected double workload;
	
	protected double executionPeriod;
	
    //	Derived attributes
	
	public void setExecutionPeriod() {
		this.executionPeriod = (double) (this.end.getTime() - this.begin.getTime()) / (1000 * 3600);
	}
	
	@Transient
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	public void setWorkload() {
		this.workload = this.tasks.stream().mapToDouble(Task::getWorkload).sum();
	}
	
}