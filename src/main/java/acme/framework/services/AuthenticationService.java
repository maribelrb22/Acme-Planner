/*
 * AuthenticationService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.framework.services;

import acme.framework.entities.Anonymous;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

;

@Service
@Transactional(TxType.SUPPORTS)
public class AuthenticationService implements UserDetailsService {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticationRepository authenticationRepository;

	// Business methods -------------------------------------------------------


	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		// assert username is nullable

		Principal result;
		UserAccount userAccount;

		userAccount = this.authenticationRepository.findByUsername(username);
		if (userAccount == null) {
			throw new UsernameNotFoundException(username);
		}

		result = new Principal();

		result.setUsername(userAccount.getUsername());
		result.setPassword(userAccount.getPassword());

		result.setEnabled(userAccount.isEnabled());

		result.setAuthorities(userAccount.getRoles());
		if (userAccount.isAnonymous()) {
			result.setActiveRole(Anonymous.class);
		} else {
			result.setActiveRole(Authenticated.class);
		}

		result.setAccountId(userAccount.getId());

		return result;
	}

}
