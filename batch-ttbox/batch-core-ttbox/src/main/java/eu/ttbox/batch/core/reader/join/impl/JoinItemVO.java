package eu.ttbox.batch.core.reader.join.impl;

import com.google.common.base.Objects;
import eu.ttbox.batch.core.reader.join.JoinItem;

import java.util.ArrayList;
import java.util.List;

public class JoinItemVO<MASTER, JOIN> implements JoinItem<MASTER, JOIN> {

	private MASTER master;

	private List<JOIN> joins = null;

	public JoinItemVO(MASTER masterItem) {
		super();
		this.master = masterItem;
	}

	public void addJoin(JOIN joinItem) {
		if (joins == null) {
			joins = new ArrayList<JOIN>();
		}
		joins.add(joinItem);
	}

	public List<JOIN> getJoins() {
		return joins;
	}

	public void setJoins(List<JOIN> source) {
		this.joins = source;
	}

	public MASTER getMaster() {
		return master;
	}

	public void setMaster(MASTER master) {
		this.master = master;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("master", master).add("joins", joins).toString();
	}

}
