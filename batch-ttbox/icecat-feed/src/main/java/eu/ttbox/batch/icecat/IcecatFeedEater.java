package eu.ttbox.batch.icecat;

import eu.ttbox.batch.icecat.dao.audit.EntityAuditInterceptor;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.ValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.*;

public class IcecatFeedEater {

	private static Logger log = LoggerFactory.getLogger(IcecatFeedEater.class);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Prepare parser
		OptionParser parser = new OptionParser();
		parser.acceptsAll(Arrays.asList("?", "h", "help"), "print help");
		// --- Service list ---
		// --------------------
		ValueConverter<IcecatFeedEnum> enumConverter = new ValueConverter<IcecatFeedEnum>() {

			@Override
			public IcecatFeedEnum convert(String value) {
				IcecatFeedEnum converrted = IcecatFeedEnum.valueOf(value);
				return converrted;
			}

			@Override
			public String valuePattern() {
				return "IcecatFeedEnum";
			}

			@Override
			public Class<IcecatFeedEnum> valueType() {
				return IcecatFeedEnum.class;
			}
		};
		parser.acceptsAll(Arrays.asList("forceOrder"), "Force order for file list and disaable dedup");
		parser.acceptsAll(Arrays.asList("all", "a"), "Import all files");
		parser.acceptsAll(Arrays.asList("referential", "ref", "r"), "Import referentials files");
		parser.acceptsAll(Arrays.asList("productDaily", "daily", "d"), "Import Daily product files");
		parser.acceptsAll(Arrays.asList("productFull", "full", "f"), "Import Full Product files");
		parser.acceptsAll(Arrays.asList("service", "s"), "Import List of files").withRequiredArg().ofType(IcecatFeedEnum.class)
				.withValuesConvertedBy(enumConverter).withValuesSeparatedBy(',').describedAs(IcecatFeedEater.toPrintHelpService());
		parser.acceptsAll(Arrays.asList("exclude", "x"), "The exclude list of files").withRequiredArg().ofType(IcecatFeedEnum.class)
				.withValuesConvertedBy(enumConverter).withValuesSeparatedBy(',').describedAs(IcecatFeedEater.toPrintHelpService());

		// --- parse line ---
		// ------------------
		try {
			OptionSet options = null;
			try {
				options = parser.parse(args);
			} catch (OptionException e) {
				System.err.println(e.getMessage());
				parser.printHelpOn(System.out);
				System.exit(1);
			}

			// Validate line entry
			boolean isValid = true;
			// boolean isValid = options.hasArgument("service");
			if (options.has("help") || !isValid) {
				System.out.println("Use with JVM arguments at least with -Xmx1024m");
				parser.printHelpOn(System.out);
				System.exit(1);
			}

			// Do Service
			Collection<IcecatFeedEnum> enums = new TreeSet<IcecatFeedEnum>();
			if (options.has("forceOrder")) {
				enums = new ArrayList<IcecatFeedEnum>();
			}
			if (options.has("all")) {
				enums.addAll(IcecatFeedEnum.getValidEnums());
			}
			if (options.hasArgument("service")) {
				enums.addAll((List<IcecatFeedEnum>) options.valuesOf("service"));
			}
			if (options.has("ref")) {
				enums.addAll(IcecatFeedEnum.getReferentialEnums());
			}
			if (options.has("daily")) {
				enums.add(IcecatFeedEnum.iceProductDaily);
			}
			if (options.has("full")) {
				enums.add(IcecatFeedEnum.iceProductFull);
			}
			// Exclude List
			if (enums != null && options.has("exclude")) {
				List<IcecatFeedEnum> excludeEnums = (List<IcecatFeedEnum>) options.valuesOf("exclude");
				enums.removeAll(excludeEnums);
			}
			// Run Services
			if (enums != null && !enums.isEmpty()) {
				IcecatFeedEater.doImport(enums);
			} else {
				System.err.println("Missing parameter");
				parser.printHelpOn(System.out);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String toPrintHelpService() {
		StringBuffer sb = new StringBuffer();
		List<IcecatFeedEnum> validServices = IcecatFeedEnum.getValidEnums();
		boolean isFirst = true;
		for (IcecatFeedEnum validService : validServices) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}
			sb.append(validService.name());
		}
		return sb.toString();
	}

	public static void doImport(Collection<IcecatFeedEnum> listFiles) {
		IcecatFeedEnum[] referentialFiles = (IcecatFeedEnum[]) listFiles.toArray(new IcecatFeedEnum[listFiles.size()]);
		doImport(referentialFiles);
	}

	public static void doImport(IcecatFeedEnum... referentialFiles) {
		EntityAuditInterceptor auditInterceptor = (EntityAuditInterceptor) SpringContextFactory.getBean("auditInterceptor");

		try {
			StopWatch stopWatch = new StopWatch("IcecatFeedEater");
			int refFileSize = referentialFiles.length;
			int idx = 0;
			for (IcecatFeedEnum refFile : referentialFiles) {
				idx++;
				stopWatch.start("" + idx + "/" + refFileSize + ": " + refFile.name() + " (" + refFile.name() + ")");
				// .FIXME String serviceName = refFile.getImportServiceName();

				if (auditInterceptor != null) {
					auditInterceptor.toLog();
					auditInterceptor.reset();
				}
				stopWatch.stop();
				// log.info("Import {} => {} entitites.", refFile, count);
				log.info(stopWatch.prettyPrint());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			// auditInterceptor.toLog();
			// SpringContextFactory.shutdown();
		}
	}
}
