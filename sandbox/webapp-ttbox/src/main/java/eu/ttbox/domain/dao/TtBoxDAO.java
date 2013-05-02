package eu.ttbox.domain.dao;

import java.util.Collection;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TtBoxDAO {

	public abstract void flushAndClear();

	public abstract void flush();

	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract void saveObject(Object entity);

 
	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract void saveObjects(@SuppressWarnings("rawtypes") Collection entities);

 
	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract void deleteAll(@SuppressWarnings("rawtypes") Collection entities);

	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract void deleteAll(Object entity);

}