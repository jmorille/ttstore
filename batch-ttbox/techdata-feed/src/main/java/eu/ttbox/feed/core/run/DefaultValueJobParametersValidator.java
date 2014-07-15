package eu.ttbox.feed.core.run;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.DefaultJobParametersValidator;

import java.util.Map;

public class DefaultValueJobParametersValidator extends DefaultJobParametersValidator {

	private Map<String, JobParameter> defaultJobParameters;

	public void setDefaultJobParameters(Map<String, JobParameter> defaultKeyValues) {
		this.defaultJobParameters = defaultKeyValues;
	}

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		if (defaultJobParameters != null && !defaultJobParameters.isEmpty()) {
			Map<String, JobParameter> jobParams = parameters.getParameters();
			for (String key : jobParams.keySet()) {
				if (!jobParams.containsKey(key)) {
					JobParameter defaultValue = defaultJobParameters.get(key);
					if (defaultValue != null) {
						jobParams.put(key, defaultValue);
					}
				}
			}
		}
		// Validate Values
		super.validate(parameters);
	}
}
