package eu.ttbox.batch.techdata.core.cmd;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import eu.ttbox.batch.techdata.TechDataFeedEnum;

public class TechDataFeedEnumConverter extends BaseConverter<TechDataFeedEnum> {

	public TechDataFeedEnumConverter(String optionName) {
		super(optionName); 
	}

	@Override
	public TechDataFeedEnum convert(String value) {
		TechDataFeedEnum feed = null;
		if (!Strings.isNullOrEmpty(value)) {
			try {
			feed = TechDataFeedEnum.valueOf(value);
			} catch (java.lang.IllegalArgumentException e) {
				
			}
		}
		if (feed != null) {
			return feed;
		} else {
			throw new ParameterException(getErrorString(value, " not in (" + Joiner.on(", ").join(TechDataFeedEnum.getValidEnums()) + ")"));
		}
	}

}
