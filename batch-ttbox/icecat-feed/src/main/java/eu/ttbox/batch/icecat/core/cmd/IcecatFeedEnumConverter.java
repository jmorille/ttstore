package eu.ttbox.batch.icecat.core.cmd;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import eu.ttbox.batch.icecat.IcecatFeedEnum;

public class IcecatFeedEnumConverter extends BaseConverter<IcecatFeedEnum> {

	public IcecatFeedEnumConverter(String optionName) {
		super(optionName);
	}

	@Override
	public IcecatFeedEnum convert(String value) {
		IcecatFeedEnum feed = null;
		if (!Strings.isNullOrEmpty(value)) {
			try {
				feed = IcecatFeedEnum.valueOf(value);
			} catch (java.lang.IllegalArgumentException e) {

			}
		}
		if (feed != null) {
			return feed;
		} else {
			throw new ParameterException(getErrorString(value, " not in (" + Joiner.on(", ").join(IcecatFeedEnum.getValidEnums()) + ")"));
		}
	}

}
