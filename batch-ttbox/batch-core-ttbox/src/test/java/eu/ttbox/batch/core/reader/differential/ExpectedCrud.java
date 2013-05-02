package eu.ttbox.batch.core.reader.differential;

import java.util.Arrays;
import java.util.List;

public class ExpectedCrud {

	List<String> createExpected = Arrays.asList("A", "B", "C", "I", "J");
	List<String> updateExpected = Arrays.asList("D", "E");
	List<String> deleteExpected = Arrays.asList("F", "G", "H");

	public ExpectedCrud(List<String> createExpected,
			List<String> updateExpected, List<String> deleteExpected) {
		super();
		this.createExpected = createExpected;
		this.updateExpected = updateExpected;
		this.deleteExpected = deleteExpected;
	}

	public List<String> getCreateExpected() {
		return createExpected;
	}

	public void setCreateExpected(List<String> createExpected) {
		this.createExpected = createExpected;
	}

	public List<String> getUpdateExpected() {
		return updateExpected;
	}

	public void setUpdateExpected(List<String> updateExpected) {
		this.updateExpected = updateExpected;
	}

	public List<String> getDeleteExpected() {
		return deleteExpected;
	}

	public void setDeleteExpected(List<String> deleteExpected) {
		this.deleteExpected = deleteExpected;
	}

}
