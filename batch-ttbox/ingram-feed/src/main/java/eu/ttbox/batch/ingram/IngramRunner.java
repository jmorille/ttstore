package eu.ttbox.batch.ingram;

import java.util.HashSet;
import java.util.Set;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import eu.ttbox.batch.core.BatchJobRunner;
import eu.ttbox.batch.ingram.cmd.IngramFeedEaterParams;

public class IngramRunner {

	public static void main(String[] args) throws Exception {
		IngramFeedEaterParams params = new IngramFeedEaterParams();
		JCommander jc = new JCommander(params);
		boolean printHelp = true;
		try {
			jc.parse(args);
		} catch (ParameterException e) {
			System.err.println(e.getMessage());
		}
		// Read desc

		printHelp = params.help;
		if (printHelp || !params.isJobTodo()) {
			System.out.println(params.usage(jc));
		} else {
			// command.exit(result);
			Set<String> opts = new HashSet<String>();
			String[] parameters = new String[0];
			BatchJobRunner runner = new BatchJobRunner();
			runner.start(params.getJobPath(), params.getJobIdentifiers(), parameters, opts);
		}
	}

}
