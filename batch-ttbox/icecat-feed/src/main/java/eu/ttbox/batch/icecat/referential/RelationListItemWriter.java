package eu.ttbox.batch.icecat.referential;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.relations.v1.Relation;
import biz.icecat.relations.v1.RelationGroup;
import eu.ttbox.batch.icecat.referential.dependency.IDependencyDifferential;
import eu.ttbox.icecat.model.relations.IcecatRelation;
import eu.ttbox.icecat.model.relations.IcecatRelationGroup;

@Service("relationListIcecatItemWriter")
public class RelationListItemWriter extends AbstractReferentialItemWriter<RelationGroup> implements ItemWriter<RelationGroup> {

	@Autowired
	@Qualifier("icecatRelationDifferential")
	IDependencyDifferential<IcecatRelationGroup, IcecatRelation, Relation> relationDifferential;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(RelationGroup relGroup) {
		int countCreate = 0;
		// int countDelete = 0;
		Integer entityId = relGroup.getID();
		IcecatRelationGroup entity = (IcecatRelationGroup) getIcecatDAO().getById(entityId, IcecatRelationGroup.class);
		if (entity == null) {
			entity = new IcecatRelationGroup();
			entity.setId(entityId);
		}
		entity.setName(relGroup.getName());
		entity.setDescription(relGroup.getDescription());
		getIcecatDAO().saveObject(entity);
		getIcecatDAO().flush();

		// Manage Dependency
		List<IcecatRelation> relations = entity.getRelations();
		if (relations == null) {
			relations = new ArrayList<IcecatRelation>();
		}
		this.relationDifferential.doImportDependencies(entity, relations, relGroup.getRelations());

		return countCreate;
	}

}
