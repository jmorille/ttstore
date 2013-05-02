package eu.ttbox.batch.ingram.price.list;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class ListEntityDifferentialVO<T> {

	private List<T> creates = new ArrayList<T>(100000);

	private List<T> updates = new ArrayList<T>(100000);

	private List<T> deletes = new ArrayList<T>(100000);

	// *** *** *** Create *** *** *** //

	public List<T> getCreates() {
		return creates;
	}

	public int getCountCreates() {
		return creates.size();
	}

	public void addCreate(T create) {
		this.creates.add(create);
	}

	// *** *** *** Update *** *** *** //
	public List<T> getUpdates() {
		return updates;
	}

	public int getCountUpdates() {
		return updates.size();
	}

	public void addUpdate(T updates) {
		this.updates.add(updates);
	}

	// *** *** *** Delete *** *** *** //

	public List<T> getDeletes() {
		return deletes;
	}

	public int getCountDeletes() {
		return deletes.size();
	}

	public void addDelete(T deletes) {
		this.deletes.add(deletes);
	}

	// *** *** *** Business *** *** *** //

	public int getCountRetrieve() {
		return getCountUpdates() + getCountCreates();
	}

	public int getCountCUD() {
		return getCountUpdates() + getCountCreates() + getCountDeletes();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this) //
				.add("creates", creates.size()) //
				.add("updates", updates.size()) //
				.add("deletes", deletes.size()) //
				.toString();
	}

}
