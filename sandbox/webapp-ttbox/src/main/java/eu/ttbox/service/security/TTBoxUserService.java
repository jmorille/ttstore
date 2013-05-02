package eu.ttbox.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.ttbox.domain.dao.user.UserDAO;

@Service("ttBoxmatriculeUserDetailsService")
public class TTBoxUserService implements UserDetailsService {

	@Autowired	@Qualifier("userDAO")
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException { 
		return userDAO.getByMatricule(username);
	}

}
