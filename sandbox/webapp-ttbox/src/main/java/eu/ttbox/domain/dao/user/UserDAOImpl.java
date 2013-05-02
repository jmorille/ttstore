package eu.ttbox.domain.dao.user;

 

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import eu.ttbox.domain.dao.AbstractHibernateDAOImpl;
import eu.ttbox.domain.model.user.User;

@Repository("userDAO")
public class UserDAOImpl extends AbstractHibernateDAOImpl<User, Long> implements UserDAO {

	
	public User getByMatricule(String matricule) {
		Query query = getSession().createQuery("from User where matricule=:matricule");
		query.setString("matricule", matricule);
		User user = (User)query.uniqueResult();
		return user;
	}
}
