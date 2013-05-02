package eu.ttbox.service.security;

import eu.ttbox.model.user.User;
import eu.ttbox.model.user.User_;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("ttBoxUserDetails")
public class TTBoxUserDetails implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = null;
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
        query.setParameter(User_.email.getName(), username);
        List<User> resultList = query.getResultList();
        if (resultList.size() > 0) {
            result = resultList.get(0);
        } else {
            throw new UsernameNotFoundException("No registered username " + username);
        }
        return result;
    }
}
