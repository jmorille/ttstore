package eu.ttbox.batch.icecat.indexor;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.batch.item.ItemProcessor;

import eu.ttbox.icecat.model.referential.IcecatBrand;

public class IcecatBrandIndexProcessor implements ItemProcessor<IcecatBrand, IcecatBrand> {

	@Override
	public IcecatBrand process(IcecatBrand item) throws Exception {
		XContentBuilder content = XContentFactory.jsonBuilder().startObject();
		// XContentBuilder content = XContentFactory.smileBuilder().startObject();
		item.getName();

		return null;
	}

}
