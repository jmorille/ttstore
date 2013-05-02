package eu.ttbox.batch.core.reader.differential;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;

public class DifferentialItemTest {

	@Test
	public void testHashCode() {
		List<DifferentialItem<String, String>> cache = new ArrayList<DifferentialItem<String, String>>();
		DifferentialItem<String, String> item = new DifferentialItem<String, String>(CUDStatus.CREATE, "ref01", "join01");
		cache.add(item);
		Assert.assertTrue(cache.remove(item));
	}

}
