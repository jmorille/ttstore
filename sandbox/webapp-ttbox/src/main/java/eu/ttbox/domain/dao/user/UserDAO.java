package eu.ttbox.domain.dao.user;

import eu.ttbox.domain.dao.IHibernateDAO;
import eu.ttbox.domain.model.user.User;

public interface UserDAO  extends IHibernateDAO<User, Long> {

	User getByMatricule(String matricule);
}