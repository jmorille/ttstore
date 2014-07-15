package eu.ttbox.batch.icecat.referential.dependency;

import biz.icecat.relations.v1.Relation;
import biz.icecat.relations.v1.Rule;
import biz.icecat.relations.v1.Rules;
import eu.ttbox.icecat.model.relations.IcecatRelation;
import eu.ttbox.icecat.model.relations.IcecatRelationGroup;
import eu.ttbox.icecat.model.relations.IcecatRelationRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("icecatRelationDifferential")
@Transactional(propagation = Propagation.REQUIRED)
public class IcecatRelationDifferential extends AbstractDependencyDifferential<IcecatRelationGroup, IcecatRelation, Relation> {

	@Resource(name = "icecatRelationRuleDifferential")
	IDependencyDifferential<IcecatRelation, IcecatRelationRule, Rule> relationRuleDifferential;

	// @Resource(name = "icecatRelationRuleModelCreator")
	// IIcecatModelCreator<IcecatRelationRule, Rule>
	// icecatRelationRuleModelCreator;

	@Override
	public Integer getFeedElementId(Relation elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatRelation entity) {
		return true;
	}

	@Override
	public boolean isValidFeed(Relation elt) {
		return true;
	}

	@Override
	public IcecatRelation createEntity(IcecatRelationGroup master, Relation elt) {
		IcecatRelation entity = new IcecatRelation();
		entity.setId(elt.getID());
		entity.setRelationGroup(master);
		return entity;
	}

	@Override
	public IcecatRelation updateEntity(IcecatRelationGroup master, Relation elt, IcecatRelation entity) {
		entity.setName(elt.getName());
		getIcecatDAO().flush();

		// --- Manage Dependency ---
		// -------------------------
		// Source Include
		List<IcecatRelationRule> sourceIncludeRules = entity.getSourceIncludeRules();
		if (sourceIncludeRules == null) {
			sourceIncludeRules = new ArrayList<IcecatRelationRule>();
			entity.setSourceIncludeRules(sourceIncludeRules);
		}
		this.relationRuleDifferential.doImportDependencies(entity, sourceIncludeRules, getConvertedToRulesList(elt.getSourceIncludeRules()));
		getIcecatDAO().flush();

		// Destination Include
		List<IcecatRelationRule> destIncludeRules = entity.getDestinationIncludeRules();
		if (destIncludeRules == null) {
			destIncludeRules = new ArrayList<IcecatRelationRule>();
			entity.setDestinationIncludeRules(destIncludeRules);
		}
		this.relationRuleDifferential.doImportDependencies(entity, destIncludeRules, getConvertedToRulesList(elt.getDestinationIncludeRules()));
		getIcecatDAO().flush();

		// Source Exclude
		List<IcecatRelationRule> sourceExcludeRules = entity.getSourceExcludeRules();
		if (sourceExcludeRules == null) {
			sourceExcludeRules = new ArrayList<IcecatRelationRule>();
			entity.setSourceExcludeRules(sourceExcludeRules);
		}
		this.relationRuleDifferential.doImportDependencies(entity, sourceExcludeRules, getConvertedToRulesList(elt.getSourceExcludeRules()));
		getIcecatDAO().flush();

		// Destination Exclude
		List<IcecatRelationRule> destExcludeRules = entity.getDestinationExcludeRules();
		if (destExcludeRules == null) {
			destExcludeRules = new ArrayList<IcecatRelationRule>();
			entity.setDestinationExcludeRules(destExcludeRules);
		}
		this.relationRuleDifferential.doImportDependencies(entity, destExcludeRules, getConvertedToRulesList(elt.getDestinationExcludeRules()));
		getIcecatDAO().flush();

		// doImportIcecatRelationRule(elt.getSourceIncludeRules());
		// doImportIcecatRelationRule(elt.getDestinationIncludeRules());
		// doImportIcecatRelationRule(elt.getSourceExcludeRules());
		// doImportIcecatRelationRule(elt.getDestinationExcludeRules());

		return entity;
	}

	private List<Rule> getConvertedToRulesList(Rules ruleset) {
		List<Rule> rules = null;
		if (ruleset != null && ruleset.getRules() != null) {
			rules = ruleset.getRules();
		} else {
			rules = new ArrayList<Rule>();
		}

		return rules;
	}

	// private IcecatRelationRule doImportIcecatRelationRule(Rules ruleset) {
	// IcecatRelationRule result = null;
	// if (ruleset != null && ruleset.getRules() != null
	// && !ruleset.getRules().isEmpty()) {
	// List<Rule> rules = ruleset.getRules();
	// for (Rule rule : rules) {
	// result = this.icecatRelationRuleModelCreator.doImport(rule);
	// }
	// }
	// return result;
	// }

}
