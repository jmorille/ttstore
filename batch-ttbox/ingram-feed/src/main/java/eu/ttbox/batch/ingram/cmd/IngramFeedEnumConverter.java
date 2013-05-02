package eu.ttbox.batch.ingram.cmd;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import eu.ttbox.batch.ingram.IngramFeedEnum;

public class IngramFeedEnumConverter extends BaseConverter<IngramFeedEnum> {

	public IngramFeedEnumConverter(String optionName) {
		super(optionName);
	}

	@Override
	public IngramFeedEnum convert(String value) {
		IngramFeedEnum feed = null;
		if (!Strings.isNullOrEmpty(value)) {
			try {
				feed = IngramFeedEnum.valueOf(value);
			} catch (java.lang.IllegalArgumentException e) {

			}
		}
		if (feed != null) {
			return feed;
		} else {
			throw new ParameterException(getErrorString(value, " not in (" + Joiner.on(", ").join(IngramFeedEnum.getValidEnums()) + ")"));
		}
	}

}
