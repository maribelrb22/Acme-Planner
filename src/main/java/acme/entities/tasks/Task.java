package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Managers;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
public class Task extends DomainEntity {

	// Serialisation identifier

	protected static final long serialVersionUID = 1L;

	// Attributes

	@NotBlank
	@Length(max = 80)
	protected String title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date begin;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date end;

	@NotBlank
	@Length(max = 500)
	@Column(length = 500)
	protected String description;

	@URL
	protected String link;

	@NotNull
    protected Boolean isPublic;
	
	@NotNull
	@Positive
	protected double workload;
	
	protected double executionPeriod;
	
	
	//	Derived attributes

	public void setExecutionPeriod() {
		this.executionPeriod = (double) (this.end.getTime() - this.begin.getTime()) / (1000 * 3600);
	}

	
	public Boolean isFinished() {
		Date now;
		now = new Date();
		return now.after(this.end);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.begin == null) ? 0 : this.begin.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.end == null) ? 0 : this.end.hashCode());
		result = prime * result + ((this.isPublic == null) ? 0 : this.isPublic.hashCode());
		result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Task other = (Task) obj;
		if (this.begin == null) {
			if (other.begin != null)
				return false;
		} else if (!this.begin.equals(other.begin))
			return false;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.end == null) {
			if (other.end != null)
				return false;
		} else if (!this.end.equals(other.end))
			return false;
		if (this.isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!this.isPublic.equals(other.isPublic))
			return false;
		if (this.link == null) {
			if (other.link != null)
				return false;
		} else if (!this.link.equals(other.link))
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		return true;
	}
	
	

	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Managers Managers;

}
