package eu.ttbox.batch.ingram.cmd;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import eu.ttbox.batch.ingram.IngramFeedEnum;

@Parameters(commandDescription = "Icecat Feeds")
public class IngramFeedEaterParams {

	@Parameter(description = "[commands]", converter = IngramFeedEnumConverter.class)
	public List<IngramFeedEnum> feeds = new ArrayList<IngramFeedEnum>();

	@Parameter(names = { "-a", "--all" }, description = "Import all ingram files")
	public boolean all = false;

	@Parameter(names = { "-s", "--stock", "--techStock" }, description = "Import Stock ingram files")
	public boolean stock = false;

	@Parameter(names = { "-k", "--kit", "--techKit" }, description = "Import Kit ingram files")
	public boolean kit = false;

	@Parameter(names = { "-t", "--tax", "--techTax" }, description = "Import Tax ingram files")
	public boolean tax = false;

	@Parameter(names = { "-p", "--price", "--techPrice" }, description = "Import Price ingram files")
	public boolean price = false;

	@Parameter(names = { "-m", "--material", "--techMat" }, description = "Import Material ingram files")
	public boolean material = false;

	@Parameter(names = { "-h", "--help" }, description = "print this help")
	public boolean help = false;

	public List<IngramFeedEnum> getWantedFeeds() {
		List<IngramFeedEnum> wantedFeeds = new ArrayList<IngramFeedEnum>();
		if (all) {
			wantedFeeds.addAll(IngramFeedEnum.getValidEnums());
		}
		// if (material) {
		// wantedFeeds.add(IngramFeedEnum.techMat);
		// }
		// if (kit) {
		// wantedFeeds.add(IngramFeedEnum.techKit);
		// }
		// if (stock) {
		// wantedFeeds.add(IngramFeedEnum.techStock);
		// }
		// if (tax) {
		// wantedFeeds.add(IngramFeedEnum.techTax);
		// }
		if (price) {
			wantedFeeds.add(IngramFeedEnum.price);
		}

		wantedFeeds.addAll(feeds);
		return wantedFeeds;
	}

	public String[] getJobPath() {
		List<IngramFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jobPaths = new String[wantedFeeds.size() + 1];
		int ixd = 0;
		jobPaths[ixd++] = "/META-INF/ingram/ingram-job-repository.xml";
		for (IngramFeedEnum feed : wantedFeeds) {
			String jobPath = feed.getJobPath();
			jobPaths[ixd++] = jobPath;
		}
		return jobPaths;
	}

	public String[] getJobIdentifiers() {
		List<IngramFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jonIdentifiers = new String[wantedFeeds.size()];
		int idx = 0;
		for (IngramFeedEnum feed : wantedFeeds) {
			jonIdentifiers[idx++] = feed.getJobIdentifier();
		}

		return jonIdentifiers;
	}

	public boolean isJobTodo() {
		List<IngramFeedEnum> wantedFeeds = getWantedFeeds();
		return wantedFeeds.size() > 0;
	}

	public String usage(JCommander jc) {
		StringBuilder sb = new StringBuilder(738);
		jc.usage(sb);
		// Add Commands
		sb.append("  Commands:");
		for (IngramFeedEnum feedEnum : IngramFeedEnum.getValidEnums()) {
			sb.append("\n    ").append(feedEnum.name());
		}
		return sb.toString();
	}

}
