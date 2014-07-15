package eu.ttbox.batch.icecat.core.cmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import eu.ttbox.batch.icecat.IcecatFeedEnum;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandDescription = "Icecat Feeds")
public class IcecatFeedEaterParams {

	@Parameter(description = "[commands]", converter = IcecatFeedEnumConverter.class)
	public List<IcecatFeedEnum> feeds = new ArrayList<IcecatFeedEnum>();

	@Parameter(names = { "-i", "--init" }, description = "Initialisation of Elasticsearch Index")
	public boolean init = false;

	@Parameter(names = { "-r", "--ref" }, description = "Import referentials files")
	public boolean referential = false;

	@Parameter(names = { "-d", "-pd", "--daily" }, description = "Import Daily product files")
	public boolean productDaily = false;

	@Parameter(names = { "-f", "-pf", "--full" }, description = "Import Full product files")
	public boolean productFull = false;

	@Parameter(names = { "-h", "--help" }, description = "print this help")
	public boolean help = false;

	public List<IcecatFeedEnum> getWantedFeeds() {
		List<IcecatFeedEnum> wantedFeeds = new ArrayList<IcecatFeedEnum>();
		if (init) {
			wantedFeeds.addAll(IcecatFeedEnum.getInitEnums());
		}
		if (referential) {
			wantedFeeds.addAll(IcecatFeedEnum.getReferentialEnums());
		}
		if (productDaily) {
			wantedFeeds.add(IcecatFeedEnum.iceProductDaily);
		}
		if (productFull) {
			wantedFeeds.add(IcecatFeedEnum.iceProductFull);
		}
		wantedFeeds.addAll(feeds);
		return wantedFeeds;
	}

	public String[] getJobPath() {
		List<IcecatFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jobPaths = new String[wantedFeeds.size() + 1];
		int ixd = 0;
		jobPaths[ixd++] = "/applicationContext-job-repository.xml";
		for (IcecatFeedEnum feed : wantedFeeds) {
			String jobPath = feed.getJobPath();
			jobPaths[ixd++] = jobPath;
		}
		return jobPaths;
	}

	public String[] getJobIdentifiers() {
		List<IcecatFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jonIdentifiers = new String[wantedFeeds.size()];
		int idx = 0;
		for (IcecatFeedEnum feed : wantedFeeds) {
			jonIdentifiers[idx++] = feed.getJobIdentifier();
		}

		return jonIdentifiers;
	}

	public boolean isJobTodo() {
		List<IcecatFeedEnum> wantedFeeds = getWantedFeeds();
		return wantedFeeds.size() > 0;
	}

	public String usage(JCommander jc) {
		StringBuilder sb = new StringBuilder(738);
		jc.usage(sb);
		// Add Commands
		sb.append("  Commands:");
		for (IcecatFeedEnum feedEnum : IcecatFeedEnum.getValidEnums()) {
			sb.append("\n    ").append(feedEnum.name());
		}
		return sb.toString();
	}
}
