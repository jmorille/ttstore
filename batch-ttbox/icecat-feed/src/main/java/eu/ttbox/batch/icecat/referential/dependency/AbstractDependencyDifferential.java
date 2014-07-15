package eu.ttbox.batch.icecat.referential.dependency;

import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDependencyDifferential<MASTER, REF extends IIcecatPersistantModelObject, FEED> implements
		IDependencyDifferential<MASTER, REF, FEED>, InitializingBean {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	boolean isLogValidatedMessgage = true;

	@Override
	public void afterPropertiesSet() throws Exception {
		// Nothing to do
	}

	public IcecatDAO getIcecatDAO() {
		return icecatDAO;
	}

	public void setIcecatDAO(IcecatDAO icecatDAO) {
		this.icecatDAO = icecatDAO;
	}

	@Override
	public abstract Integer getFeedElementId(FEED elt);

	@Override
	public abstract REF createEntity(MASTER master, FEED elt);

	@Override
	public abstract REF updateEntity(MASTER master, FEED elt, REF entity);

	@Override
	public abstract boolean isValidEntity(REF entity);

	public abstract boolean isValidFeed(FEED elt);

	public boolean isLogValidatedMessgage() {
		return isLogValidatedMessgage;
	}

	public void setLogValidatedMessgage(boolean isLogValidatedMessgage) {
		this.isLogValidatedMessgage = isLogValidatedMessgage;
	}

	protected Map<Integer, FEED> getDedupElementById(List<FEED> elements, MASTER master) {
		Map<Integer, FEED> eltsById = new HashMap<Integer, FEED>();
		for (FEED elt : elements) {
			boolean isValid = isValidFeed(elt);
			Integer feedId = getFeedElementId(elt);
			if (isValid) {
				if (feedId != null) {
					if (eltsById.containsKey(feedId)) {
						FEED keepFeed = eltsById.get(feedId);
						if (isValidFeed(keepFeed)) {
							log.info("Ignore Duplicated Id {}", feedId);
						} else {
							eltsById.put(feedId, elt);
							log.info("Ignore Duplicated Id {} and Keep Second", feedId);
						}
					} else {
						eltsById.put(feedId, elt);
					}
				}
			} else {
				// log.info("Ignore Not Valid Feed Id {}", feedId);
			}

		}
		return eltsById;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void doImportDependencies(MASTER master, List<REF> entities, List<FEED> elements) {
		this.doImportDependencies(master, entities, elements, true, true, true);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void doImportDependencies(MASTER master, List<REF> entities, List<FEED> elements, boolean create, boolean update, boolean delete) {
		List<REF> toSave = new ArrayList<REF>();
		List<REF> toDelete = new ArrayList<REF>();

		// Dedup Feed Id
		Map<Integer, FEED> eltsById = getDedupElementById(elements, master);

		// Update Or Delete Entity
		for (REF entityOri : entities) {
			REF entity = entityOri;
			Integer refId = entity.getId();
			FEED elt = eltsById.get(refId);
			if (elt != null) {
				// Update
				if (update) {
					entity = updateEntity(master, elt, entity);
					if (entity != null && isValidEntity(entity)) {
						// log.debug("Mask to update {}", entity);
						toSave.add(entity);
					} else {
						log.info("Ignore Update entity id {} with entity {} and redirect to delete process", refId, entity);
						log.debug("Mask as delete for not valid {}", entity);
						toDelete.add(entity);
					}
				}
			} else {
				if (delete) {
					// Delete
					log.debug("Mask as delete {}", entity);
					toDelete.add(entity);
				}
			}
			// Remove Elt by Id
			eltsById.remove(refId);
		}

		// Create Entity
		List<REF> toCreate = new ArrayList<REF>();
		if (create) {
			for (FEED elt : eltsById.values()) {
				// Create Elt
				REF entity = createEntity(master, elt);
				entity = updateEntity(master, elt, entity);
				if (entity != null && isValidEntity(entity)) {
					// log.debug("Mask to create {}", entity);
					toSave.add(entity);
					toCreate.add(entity);
				} else {
					Integer refId = null;
					if (entity != null) {
						refId = entity.getId();
					}
					if (isLogValidatedMessgage) {
						log.info("Ignore Create entity id {} with entity {}", refId, entity);
					}

				}
			}
		}

		// Delete
		persitToDelete(entities, toDelete);
		entities.removeAll(toDelete);

		// Save
		persitToSave(entities, toCreate, toSave);
		entities.addAll(toCreate);
	}

	protected void persitToDelete(List<REF> entities, List<REF> toDelete) {
		if (!toDelete.isEmpty()) {
			IcecatDAO icecatDAO = getIcecatDAO();
			// Delete Entity From DataBase
			icecatDAO.deleteAll(toDelete);
			icecatDAO.flush();
			// Delete Entity from Collection
			log.debug("toDelete List {} entities", toDelete.size());
			entities.removeAll(toDelete);
		}
	}

	protected void persitToSave(List<REF> entities, List<REF> toCreate, List<REF> toSave) {
		if (!toSave.isEmpty()) {
 			IcecatDAO icecatDAO = getIcecatDAO();
			// Persist
			boolean needFlush = false;
			// for (REF entity : entiti
			for (REF entity : entities) {
				if (isValid(entity)) {
					// log.info("ToSave entity  {} : {}" ,++count , entity );
					icecatDAO.saveObject(entity);
					needFlush = true;
				} else {
					log.warn("Ignore Save of not valid entity {}", entity);
					// TODO Redirect to Delete
				}
			}
			// Flush Data
			if (needFlush) {
				icecatDAO.flush();
			}
		}
	}

	protected boolean isValid(REF entity) {
		return true;
	}
	
	public String left(String val, int len, String catLog) {
		String valueContent = val;
		if (valueContent != null && valueContent.length() > len) {
			int valueContentSize = valueContent.length();
			String newVal = valueContent.substring(0, len);
			if (log.isWarnEnabled()) {
				log.warn(catLog + " too long with " + valueContentSize + " caracters need to be trunc to \n" + valueContent + "\n" + newVal);
			}
			valueContent = newVal;
		}
		return valueContent;
	}
}
