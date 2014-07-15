package eu.ttbox.batch.core.reader.multijoin.impl;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import eu.ttbox.batch.core.reader.multijoin.MultiJoinItem;

import java.util.ArrayList;
import java.util.List;

public class MultiJoinItemVO<MASTER> implements MultiJoinItem<MASTER> {

	private MASTER master;

	private List[] joins = null;

	private int joinCount = 0;

	public MultiJoinItemVO(MASTER master, int maxCount) {
		super();
		this.master = master;
		this.joinCount = maxCount;
		this.joins = new List[joinCount];

	}

	public void addJoin(int idx, Object joinItem) {
		List currentList = joins[idx];
		if (currentList == null) {
			currentList = new ArrayList();
			joins[idx] = currentList;
		}
		currentList.add(joinItem);
	}

	public List[] getJoins() {
		return joins;
	}

	public List getJoin(int idx) {
		return joins[idx];
	}

	public MASTER getMaster() {
		return master;
	}

	public void setMaster(MASTER master) {
		this.master = master;
	}

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this).add("master", master);
		sb.add("joinItemCount", joinCount);
		for (int i = 0; i < joins.length; i++) {
			int joinSize = 0;
			List join =  joins[i];
			if (join!=null) {
				joinSize = join.size();
			}
					
			sb.add("join[" + i + "]",joinSize);
		}
		for (int i = 0; i < joins.length; i++) { 
			sb.add("joinItem[" + i + "]",joins[i]);
		}
		return sb.toString();
	}

}
