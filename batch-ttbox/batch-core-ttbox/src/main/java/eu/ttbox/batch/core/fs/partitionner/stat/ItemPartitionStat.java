package eu.ttbox.batch.core.fs.partitionner.stat;

import com.google.common.base.Objects;

public class ItemPartitionStat implements Comparable<ItemPartitionStat> {

	private String partitionId;

	private int itemCount = 0;

	public ItemPartitionStat(String partitionId) {
		this(partitionId, 0);
	}

	public ItemPartitionStat(String partitionId, int itemCount) {
		super();
		this.partitionId = partitionId;
		this.itemCount = itemCount;
	}

	public String getPartitionId() {
		return partitionId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void addItemCount() {
		this.itemCount++;
	}

	public void setPartitionId(String partitionId) {
		this.partitionId = partitionId;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("ItemPartitionCounter").add("partitionId", partitionId)
				.add("itemCount", itemCount).toString();
	}

	@Override
	public int compareTo(ItemPartitionStat o) {
		int thisVal = itemCount;
		int anotherVal = o.itemCount;
		return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
	}

}
