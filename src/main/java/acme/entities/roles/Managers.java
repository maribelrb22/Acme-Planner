package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Managers extends UserRole {

	protected static final long	serialVersionUID	= 1L;
	
	@NotBlank
	protected String company;

	@NotBlank
	protected String sector;
	
}
