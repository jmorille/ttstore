package eu.ttbox.batch.techdata.core.cmd;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import eu.ttbox.batch.techdata.TechDataFeedEnum;

@Parameters(commandDescription = "Icecat Feeds")
public class TechdataFeedEaterParams {

	@Parameter(description = "[commands]", converter = TechDataFeedEnumConverter.class)
	public List<TechDataFeedEnum> feeds = new ArrayList<TechDataFeedEnum>();

	@Parameter(names = { "-a", "--all" }, description = "Import all techdata files")
	public boolean all = false;

	@Parameter(names = { "-s", "--stock", "--techStock" }, description = "Import Stock techdata files")
	public boolean stock = false;

	@Parameter(names = { "-k", "--kit", "--techKit" }, description = "Import Kit techdata files")
	public boolean kit = false;

	@Parameter(names = { "-t", "--tax", "--techTax" }, description = "Import Tax techdata files")
	public boolean tax = false;

	@Parameter(names = { "-p", "--price", "--techPrice" }, description = "Import Price techdata files")
	public boolean price = false;

	@Parameter(names = { "-m", "--material", "--techMat" }, description = "Import Material techdata files")
	public boolean material = false;

	@Parameter(names = { "-h", "--help" }, description = "print this help")
	public boolean help = false;

	public List<TechDataFeedEnum> getWantedFeeds() {
		List<TechDataFeedEnum> wantedFeeds = new ArrayList<TechDataFeedEnum>();
		if (all) {
			wantedFeeds.addAll(TechDataFeedEnum.getValidEnums());
		}
		if (material) {
			wantedFeeds.add(TechDataFeedEnum.techMat);
		}
		if (kit) {
			wantedFeeds.add(TechDataFeedEnum.techKit);
		}
		if (stock) {
			wantedFeeds.add(TechDataFeedEnum.techStock);
		}
		if (tax) {
			wantedFeeds.add(TechDataFeedEnum.techTax);
		}
		if (price) {
			wantedFeeds.add(TechDataFeedEnum.techPrice);
		}
		
		wantedFeeds.addAll(feeds);
		return wantedFeeds;
	}

	public String[] getJobPath() {
		List<TechDataFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jobPaths = new String[wantedFeeds.size() + 1];
		int ixd = 0;
		jobPaths[ixd++] = "/applicationContext-job-repository.xml";
		for (TechDataFeedEnum feed : wantedFeeds) {
			String jobPath = feed.getJobPath();
			jobPaths[ixd++] = jobPath;
		}
		return jobPaths;
	}

	public String[] getJobIdentifiers() {
		List<TechDataFeedEnum> wantedFeeds = getWantedFeeds();
		String[] jonIdentifiers = new String[wantedFeeds.size()];
		int idx = 0;
		for (TechDataFeedEnum feed : wantedFeeds) {
			jonIdentifiers[idx++] = feed.getJobIdentifier();
		}

		return jonIdentifiers;
	}

	public boolean isJobTodo() {
		List<TechDataFeedEnum> wantedFeeds = getWantedFeeds();
		return wantedFeeds.size() > 0;
	}

	public String usage(JCommander jc) {
		StringBuilder sb = new StringBuilder(738);
		jc.usage(sb);
		// Add Commands
		sb.append("  Commands:");
		for (TechDataFeedEnum feedEnum : TechDataFeedEnum.getValidEnums()) {
			sb.append("\n    ").append(feedEnum.name());
		}
		return sb.toString();
	}

}
