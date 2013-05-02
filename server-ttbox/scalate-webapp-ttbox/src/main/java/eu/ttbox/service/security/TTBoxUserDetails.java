package eu.ttbox.service.security;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.ttbox.model.user.User;

@Service("ttboxUserDetails")
public class TTBoxUserDetails implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = null;
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", username);
        List<User> resultList = query.getResultList();
        if (resultList.size() > 0) {
            result = resultList.get(0);
        } else {
            throw new UsernameNotFoundException("No registered username " + username);
        }
        return result;
    }
}
