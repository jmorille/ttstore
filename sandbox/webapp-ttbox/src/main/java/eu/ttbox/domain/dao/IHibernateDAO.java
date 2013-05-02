package eu.ttbox.domain.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import eu.ttbox.domain.model.PersistantModelObject;

public interface IHibernateDAO<ENTITY extends PersistantModelObject<? extends Serializable>, ID extends Serializable> {

	void flushAndClear();

	void flush();

	void persist(ENTITY entity);

	void persistAll(Collection<ENTITY> entities);

	void removeAll(Collection<ENTITY> entities);

	void remove(ENTITY entity);

	ENTITY findById(ID id);

	List<ENTITY> findAll();

}