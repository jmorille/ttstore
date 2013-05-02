package eu.ttbox.batch.icecat.core.cmd;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import eu.ttbox.batch.icecat.IcecatFeedEnum;

public class IcecatFeedEnumValidador implements IParameterValidator {

	@Override
	public void validate(String name, String value) throws ParameterException {
		IcecatFeedEnum n = null;
		if (!Strings.isNullOrEmpty(name)) {
			n = IcecatFeedEnum.valueOf(value);
		}
		if (n == null) {
			throw new ParameterException("Parameter " + name + " should be in " + Joiner.on(", ").join(IcecatFeedEnum.getValidEnums()));
		}
	}

}
