package eu.ttbox.batch.icecat.referential.model;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.referential.dependency.IIcecatTexDifferential;
import eu.ttbox.batch.icecat.referential.dependency.IIcecatVocabularyDifferential;

@SuppressWarnings("unchecked")
public abstract class AbstractIcecatModelCreatorImpl<REF, FEED> implements IIcecatModelCreator<REF, FEED> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private Class<REF> entityClass = (Class<REF>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@Autowired
	@Qualifier("icecatDAO")
	private IcecatDAO icecatDAO;

	@Autowired
	@Qualifier("icecatVocabularyDifferential")
	IIcecatVocabularyDifferential icecatVocabularyDifferential;

	@Autowired
	@Qualifier("icecatTexDifferential")
	IIcecatTexDifferential icecatTexDifferential;

	public void setIcecatDAO(IcecatDAO icecatDAO) {
		this.icecatDAO = icecatDAO;
	}

	public IcecatDAO getIcecatDAO() {
		return icecatDAO;
	}

	public abstract Integer getFeedElementId(FEED elt);

	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract REF createEntity(FEED elt);

	@Transactional(propagation = Propagation.SUPPORTS)
	public abstract REF updateEntity(FEED elt, REF entity);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public REF doImport(FEED referential) {
		Integer entityId = getFeedElementId(referential);
		REF entity = (REF) getIcecatDAO().getById(entityId, entityClass);
		if (entity == null) {
			entity = createEntity(referential);
		}
		entity = updateEntity(referential, entity);
		// Save
		getIcecatDAO().saveObject(entity);

		return entity;
	}

}
