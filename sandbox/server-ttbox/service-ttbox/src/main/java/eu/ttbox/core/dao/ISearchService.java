package eu.ttbox.core.dao;

import org.springframework.ui.ModelMap;

import eu.ttbox.model.IBoxPersistantModelObject;

public interface ISearchService<E extends IBoxPersistantModelObject, CRITERIA> {

	public final static String RESULT_MAX_KEY = "maxResults";

	public final static String RESULT_FIRST_KEY = "firstResult";

	public final static String RESULT_COUNT_KEY = "resultCount";

	public final static String RESULT_LIST_KEY = "resultList";

	// @Transactional(readOnly = true)
	ModelMap list( //
			CRITERIA searchCriteria, //
			Integer first, Integer max, //
			String[] order, Boolean[] desc);
}
