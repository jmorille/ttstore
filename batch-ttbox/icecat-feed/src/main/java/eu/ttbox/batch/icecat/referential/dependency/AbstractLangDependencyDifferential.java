package eu.ttbox.batch.icecat.referential.dependency;

import eu.ttbox.icecat.model.IIcecatLangModelObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractLangDependencyDifferential<MASTER, REF extends IIcecatLangModelObject, FEED> extends
		AbstractDependencyDifferential<MASTER, REF, FEED> {

	public abstract Integer getFeedLangId(FEED elt);

	@Override
	protected Map<Integer, FEED> getDedupElementById(List<FEED> elements, MASTER master) {
		Map<Integer, FEED> eltsById = new HashMap<Integer, FEED>();
		Map<Integer, FEED> eltsByLangId = new HashMap<Integer, FEED>();
		for (FEED elt : elements) {
			boolean isValid = isValidFeed(elt);
			Integer feedId = getFeedElementId(elt);
			if (isValid) {
				Integer langId = getFeedLangId(elt);
				if (feedId != null) {
					if (eltsById.containsKey(feedId)) {
						FEED keepFeed = eltsById.get(feedId);
						if (isValidFeed(keepFeed)) {
							log.info("Ignore Duplicated Id (Id, langId) = (" + feedId + ", " + langId + ") and Keep First (Id, langId) = ("
									+ getFeedElementId(keepFeed) + ", " + getFeedLangId(keepFeed) + ")");
						} else {
							// TODO Inversion of Keep Feed
							// eltsById.put(feedId, elt);
							// eltsByLangId.put(langId, elt);
							log.warn("TODO Ignore Duplicated Id (Id, langId) = (" + getFeedElementId(keepFeed) + ", " + getFeedLangId(keepFeed)
									+ ") and Keep Second (Id, langId) = (" + feedId + ", " + langId + ")");
						}
					} else {
						if (eltsByLangId.containsKey(langId)) {
							FEED keepFeed = eltsByLangId.get(langId);
							if (isValidFeed(keepFeed)) {
								log.info("Ignore Duplicated LangId (Id, langId) = (" + feedId + ", " + langId + ") and Keep First (Id, langId) = ("
										+ getFeedElementId(keepFeed) + ", " + getFeedLangId(keepFeed) + ")");
							} else {
								// Inversion of Keep Feed
								eltsById.remove(getFeedElementId(keepFeed));
								eltsById.put(feedId, elt);
								eltsByLangId.put(langId, elt);
								log.info("Ignore Duplicated LangId (Id, langId) = (" + getFeedElementId(keepFeed) + ", " + getFeedLangId(keepFeed)
										+ ") and Keep Second (Id, langId) = (" + feedId + ", " + langId + ")");
							}
						} else {
							eltsById.put(feedId, elt);
							eltsByLangId.put(langId, elt);
						}
					}
				}
			} else {
				// log.info("Ignore Not Valid Feed Id {}", feedId);
			}
		}
		return eltsById;
	}

}
