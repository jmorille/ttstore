package eu.ttbox.core.listener;

import org.granite.tide.data.DataObserveParams;
import org.granite.tide.data.DataPublishParams;
import org.granite.tide.data.DataTopicParams;

public class ObserveAllPublishAll implements DataTopicParams {

	@Override
	public void observes(DataObserveParams params) {
		params.addValue("user", "__public__"); 
	}

	@Override
	public void publishes(DataPublishParams params, Object arg1) {
		 params.setValue("user", "__public__");

	}

}
