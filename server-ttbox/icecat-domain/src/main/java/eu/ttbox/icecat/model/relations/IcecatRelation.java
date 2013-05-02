package eu.ttbox.icecat.model.relations;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

@Entity
@Cacheable(true)
@Table(name = "RELATION", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo = "RELATION", indexes = {
// @Index(name = "IDX_RELATION_RELATIONGRP", columnNames = { "relation_group_id"
// }) // })
public class IcecatRelation implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 7073628268518655435L;

	// Fields
	@Id
	@Column(name = "relation_id", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_group_id", nullable = false)
	private IcecatRelationGroup relationGroup;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@ManyToMany(targetEntity = IcecatRelationRule.class, //
	cascade = { CascadeType.PERSIST, CascadeType.MERGE }//
	)
	@JoinTable(schema = "icecat", name = "relation_set_src_include" //
	, joinColumns = @JoinColumn(name = "relation_id") //
	, inverseJoinColumns = @JoinColumn(name = "relation_rule_id") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "relation_id", "relation_rule_id" }) }//
	)
	private List<IcecatRelationRule> sourceIncludeRules;

	@ManyToMany(targetEntity = IcecatRelationRule.class, //
	cascade = { CascadeType.PERSIST, CascadeType.MERGE }//
	)
	@JoinTable(schema = "icecat", name = "relation_set_dest_include" //
	, joinColumns = @JoinColumn(name = "relation_id") //
	, inverseJoinColumns = @JoinColumn(name = "relation_rule_id") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "relation_id", "relation_rule_id" }) }//
	)
	private List<IcecatRelationRule> destinationIncludeRules;

	@ManyToMany(targetEntity = IcecatRelationRule.class, //
	cascade = { CascadeType.PERSIST, CascadeType.MERGE }//
	)
	@JoinTable(schema = "icecat", name = "relation_set_src_exclude" //
	, joinColumns = @JoinColumn(name = "relation_id") //
	, inverseJoinColumns = @JoinColumn(name = "relation_rule_id") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "relation_id", "relation_rule_id" }) }//
	)
	private List<IcecatRelationRule> sourceExcludeRules;

	@ManyToMany(targetEntity = IcecatRelationRule.class, //
	cascade = { CascadeType.PERSIST, CascadeType.MERGE }//
	)
	@JoinTable(schema = "icecat", name = "relation_set_dest_exclude" //
	, joinColumns = @JoinColumn(name = "relation_id") //
	, inverseJoinColumns = @JoinColumn(name = "relation_rule_id") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "relation_id", "relation_rule_id" }) }//
	)
	private List<IcecatRelationRule> destinationExcludeRules;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "include_set_id", nullable = true)
	// private IcecatRelationSet includeSetId;
	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "exclude_set_id", nullable = true)
	// private IcecatRelationSet excludeSetId;
	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "include_set_id_2", nullable = true)
	// private IcecatRelationSet includeSetId2;
	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "exclude_set_id_2", nullable = true)
	// private IcecatRelationSet excludeSetId2;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IcecatRelationGroup getRelationGroup() {
		return relationGroup;
	}

	public void setRelationGroup(IcecatRelationGroup relationGroup) {
		this.relationGroup = relationGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IcecatRelationRule> getSourceIncludeRules() {
		return sourceIncludeRules;
	}

	public void setSourceIncludeRules(List<IcecatRelationRule> sourceIncludeRules) {
		this.sourceIncludeRules = sourceIncludeRules;
	}

	public List<IcecatRelationRule> getDestinationIncludeRules() {
		return destinationIncludeRules;
	}

	public void setDestinationIncludeRules(List<IcecatRelationRule> destinationIncludeRules) {
		this.destinationIncludeRules = destinationIncludeRules;
	}

	public List<IcecatRelationRule> getSourceExcludeRules() {
		return sourceExcludeRules;
	}

	public void setSourceExcludeRules(List<IcecatRelationRule> sourceExcludeRules) {
		this.sourceExcludeRules = sourceExcludeRules;
	}

	public List<IcecatRelationRule> getDestinationExcludeRules() {
		return destinationExcludeRules;
	}

	public void setDestinationExcludeRules(List<IcecatRelationRule> destinationExcludeRules) {
		this.destinationExcludeRules = destinationExcludeRules;
	}

	@Override
	public String toString() {
		return "IcecatRelation [id=" + id + ", name=" + name + ", relationGroup=" + relationGroup + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatRelation castedObj = (IcecatRelation) o;

		if (id == null) {
			if (castedObj.uidInEquals == null) {
				return false;
			}
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();

			}
			return (castedObj.uidInEquals.equals(uidInEquals));
		}

		return id.equals(castedObj.id);

	}

	@Override
	public int hashCode() {
		if (id == null) {
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();
			}
			return uidInEquals.hashCode();
		}
		return id.hashCode();
	}

	@Transient
	private java.rmi.dgc.VMID uidInEquals;

}
