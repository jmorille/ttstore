package eu.ttbox.server.admin.server.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.web.bindery.requestfactory.shared.Locator;

import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.server.admin.server.model.SearchResult;

public class SalespointServiceMock extends Locator<Salespoint, Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(SalespointServiceMock.class);

	private static ArrayList<Salespoint> allList = null;

	private static Salespoint getMockSalespoint(int id , String name) {
		eu.ttbox.model.salespoint.Salespoint salespoint = new eu.ttbox.model.salespoint.Salespoint();
		salespoint.setId(Integer.valueOf(id));
		salespoint.setName(name);
		salespoint.setVersion(Double.valueOf(Math.random()).intValue());
		return salespoint;
	}
	
	private static ArrayList<Salespoint> getAllList() {
		if (allList == null) {
			int id = 1;
			ArrayList<Salespoint> salespoints = new ArrayList<Salespoint>();
			salespoints.add(getMockSalespoint(id++ , "TTBox"));
			salespoints.add(getMockSalespoint(id++, "GUILLON"));
			salespoints.add(getMockSalespoint(id++, "Sogi"));
			salespoints.add(getMockSalespoint(id++, "Hidelog"));
			salespoints.add(getMockSalespoint(id++, "isi-groupe"));
			salespoints.add(getMockSalespoint(id++, "discount-micro"));
			salespoints.add(getMockSalespoint(id++, "Road Store"));
			salespoints.add(getMockSalespoint(id++, "IMD INFORMATIQUE"));
			salespoints.add(getMockSalespoint(id++, "PC Partners"));
			salespoints.add(getMockSalespoint(id++, "smvi"));
			salespoints.add(getMockSalespoint(id++, "DSF Informatique"));
			salespoints.add(getMockSalespoint(id++, "ADNET CONSULTING"));
			salespoints.add(getMockSalespoint(id++, "fradan"));
			salespoints.add(getMockSalespoint(id++, "mht"));
			salespoints.add(getMockSalespoint(id++, "Informatique Aquitaine Assistance"));
			salespoints.add(getMockSalespoint(id++, "cdc"));
			salespoints.add(getMockSalespoint(id++, "dovenco"));
			salespoints.add(getMockSalespoint(id++, "urgences micro"));
			salespoints.add(getMockSalespoint(id++, "cds"));
			salespoints.add(getMockSalespoint(id++, "setf"));
			salespoints.add(getMockSalespoint(id++, "periphelie"));
			salespoints.add(getMockSalespoint(id++, "unidees"));
			salespoints.add(getMockSalespoint(id++, "p2m"));
			salespoints.add(getMockSalespoint(id++, "Les Indispensables"));
			salespoints.add(getMockSalespoint(id++, "macsi"));
			salespoints.add(getMockSalespoint(id++, "isys"));
			salespoints.add(getMockSalespoint(id++, "Adestore"));
			salespoints.add(getMockSalespoint(id++, "NTS Shop"));
			salespoints.add(getMockSalespoint(id++, "Nano Micro"));
			salespoints.add(getMockSalespoint(id++, "PC 30"));
			salespoints.add(getMockSalespoint(id++, "INFORMATIQUE.COM"));
			salespoints.add(getMockSalespoint(id++, "concepts"));
			salespoints.add(getMockSalespoint(id++, "Click Droit Informatique"));
			salespoints.add(getMockSalespoint(id++, "s2i"));
			salespoints.add(getMockSalespoint(id++, "Distriprimo"));
			salespoints.add(getMockSalespoint(id++, "E-vipc.com"));
			salespoints.add(getMockSalespoint(id++, "techinfo"));
			salespoints.add(getMockSalespoint(id++, "mvi"));
			salespoints.add(getMockSalespoint(id++, "techinfo"));
			salespoints.add(getMockSalespoint(id++, "gm-informatique"));
			salespoints.add(getMockSalespoint(id++, "JLL Informatique"));
			salespoints.add(getMockSalespoint(id++, "WSI"));
			salespoints.add(getMockSalespoint(id++, "RSI"));
			salespoints.add(getMockSalespoint(id++, "Class"));
			salespoints.add(getMockSalespoint(id++, "Informatica 47"));
			salespoints.add(getMockSalespoint(id++, "sgui"));
			salespoints.add(getMockSalespoint(id++, "paul-sabatier"));
			salespoints.add(getMockSalespoint(id++, "EDUC"));
			salespoints.add(getMockSalespoint(id++, "ADMIN"));
			salespoints.add(getMockSalespoint(id++, "MAIRIES"));
			salespoints.add(getMockSalespoint(id++, "UTM"));
			salespoints.add(getMockSalespoint(id++, "UPS"));
			salespoints.add(getMockSalespoint(id++, "Automatic 2000"));
			salespoints.add(getMockSalespoint(id++, "ADMI COMPUTER FRANCE"));
			salespoints.add(getMockSalespoint(id++, "MANAGER"));
			salespoints.add(getMockSalespoint(id++, "CAD"));
			salespoints.add(getMockSalespoint(id++, "nextinfo.fr"));
			salespoints.add(getMockSalespoint(id++, "STARGONAUT"));
			salespoints.add(getMockSalespoint(id++, "Altomag"));
			salespoints.add(getMockSalespoint(id++, "Elio Micro"));
			salespoints.add(getMockSalespoint(id++, "Tilt Informatique"));
			salespoints.add(getMockSalespoint(id++, "WELCOME informatique"));
			salespoints.add(getMockSalespoint(id++, "Point Competence Informatique"));
			salespoints.add(getMockSalespoint(id++, "Defis System"));
			salespoints.add(getMockSalespoint(id++, "cap antigone"));
			salespoints.add(getMockSalespoint(id++, "Micro Rouen"));
			salespoints.add(getMockSalespoint(id++, "Micro Sigma"));
			salespoints.add(getMockSalespoint(id++, "DHS"));
			salespoints.add(getMockSalespoint(id++, "TKS"));
			salespoints.add(getMockSalespoint(id++, "Rom"));
			salespoints.add(getMockSalespoint(id++, "SMD Bureautique"));
			salespoints.add(getMockSalespoint(id++, "MICROTEAM"));
			salespoints.add(getMockSalespoint(id++, "DATA7"));
			salespoints.add(getMockSalespoint(id++, "Mac and Co"));
			salespoints.add(getMockSalespoint(id++, "CID"));
			salespoints.add(getMockSalespoint(id++, "Isatis"));
			salespoints.add(getMockSalespoint(id++, "Pixel Informatique"));
			salespoints.add(getMockSalespoint(id++, "Creatis Informatique"));
			salespoints.add(getMockSalespoint(id++, "Micro Systemes"));
			salespoints.add(getMockSalespoint(id++, "Computer Store TJP Informatique"));
			salespoints.add(getMockSalespoint(id++, "ARIS65"));
			salespoints.add(getMockSalespoint(id++, "Ag Tech"));
			salespoints.add(getMockSalespoint(id++, "gs2i"));
			salespoints.add(getMockSalespoint(id++, "Class"));
			salespoints.add(getMockSalespoint(id++, "Class"));
			salespoints.add(getMockSalespoint(id++, "Class"));
			salespoints.add(getMockSalespoint(id++, "Class"));
			salespoints.add(getMockSalespoint(id++, "CFI Maintenance Informatique"));
			salespoints.add(getMockSalespoint(id++, "Enligne"));
			salespoints.add(getMockSalespoint(id++, "Pro D Rama"));
			salespoints.add(getMockSalespoint(id++, "ordilan"));
			salespoints.add(getMockSalespoint(id++, "mediactive"));
			salespoints.add(getMockSalespoint(id++, "Artymedia"));
			salespoints.add(getMockSalespoint(id++, "Asi 52"));
			salespoints.add(getMockSalespoint(id++, "Cx informatique"));
			salespoints.add(getMockSalespoint(id++, "ko-network"));
			salespoints.add(getMockSalespoint(id++, "EUROGRAPH"));
			salespoints.add(getMockSalespoint(id++, "Goldway France"));
			salespoints.add(getMockSalespoint(id++, "leclerc"));
			salespoints.add(getMockSalespoint(id++, "extranetvence"));
			salespoints.add(getMockSalespoint(id++, "3F Systems"));
			salespoints.add(getMockSalespoint(id++, "Faci"));
			salespoints.add(getMockSalespoint(id++, "Inter Actif"));
			salespoints.add(getMockSalespoint(id++, "akantha"));
			salespoints.add(getMockSalespoint(id++, "MH Informatique"));
			salespoints.add(getMockSalespoint(id++, "ABI France"));
			salespoints.add(getMockSalespoint(id++, "balistik"));
			salespoints.add(getMockSalespoint(id++, "REFINFO"));
			salespoints.add(getMockSalespoint(id++, "NO WORK TECH"));
			salespoints.add(getMockSalespoint(id++, "Web&apos;in Informatique"));
			salespoints.add(getMockSalespoint(id++, "DPI Informatique"));
			salespoints.add(getMockSalespoint(id++, "AIBS"));
			salespoints.add(getMockSalespoint(id++, "Bimp Informatique"));
			salespoints.add(getMockSalespoint(id++, "Gips21"));
			salespoints.add(getMockSalespoint(id++, "Mextor"));
			salespoints.add(getMockSalespoint(id++, "ECCI"));
			salespoints.add(getMockSalespoint(id++, "novalto"));
			salespoints.add(getMockSalespoint(id++, "ISL"));
			salespoints.add(getMockSalespoint(id++, "opsyre"));
			salespoints.add(getMockSalespoint(id++, "BDX informatique"));
			salespoints.add(getMockSalespoint(id++, "Komputer"));
			salespoints.add(getMockSalespoint(id++, "Adesso Multimedia"));
			salespoints.add(getMockSalespoint(id++, "EVEN FRANCE"));
			salespoints.add(getMockSalespoint(id++, "MFVI"));
			salespoints.add(getMockSalespoint(id++, "login"));
			salespoints.add(getMockSalespoint(id++, "EURABIS"));
			salespoints.add(getMockSalespoint(id++, "Micro Plus Informatique"));
			salespoints.add(getMockSalespoint(id++, "AVEROES"));
			salespoints.add(getMockSalespoint(id++, "districad"));
			salespoints.add(getMockSalespoint(id++, "APTOR"));
			salespoints.add(getMockSalespoint(id++, "SLI"));
			salespoints.add(getMockSalespoint(id++, "JCN Informatique"));
			salespoints.add(getMockSalespoint(id++, "SGEI"));
			salespoints.add(getMockSalespoint(id++, "PWI Store"));
			salespoints.add(getMockSalespoint(id++, "jlb formatic"));
			salespoints.add(getMockSalespoint(id++, "GI Informatique"));
			salespoints.add(getMockSalespoint(id++, "Mafip"));
			salespoints.add(getMockSalespoint(id++, "ais (arize informatique service)"));
			salespoints.add(getMockSalespoint(id++, "Pock&apos;it"));
			salespoints.add(getMockSalespoint(id++, "Active developpement"));
			salespoints.add(getMockSalespoint(id++, "Store-Media"));
			salespoints.add(getMockSalespoint(id++, "ais54"));
			salespoints.add(getMockSalespoint(id++, "INACO"));
			salespoints.add(getMockSalespoint(id++, "DVM"));
			salespoints.add(getMockSalespoint(id++, "LID SAS"));

			// java.util.Collections.sort(salespoints, new Comparator<Salespoint>() {
			// @Override
			// public int compare(Salespoint o1, Salespoint o2) {
			// return o1.getName().compareTo(o2.getName());
			// }
			// });
			allList = salespoints;
		}
		return allList;
	}

	public Salespoint getSalespointById(Integer id) {
		LOG.info("Server Search Salaspoint Id={}", id);
		Salespoint result = null;
		ArrayList<Salespoint> salespoints = getAllList();
		for (Salespoint salepoint : salespoints) {
			if (id.equals(salepoint.getId())) {
				result = salepoint;
				return result;
			}
		}
		return result;
	}
	public void persist(Salespoint salespoint){
		saveSalespoint(salespoint);
	}
	
	public void saveSalespoint(Salespoint salespoint){
		Salespoint previous = getSalespointById(salespoint.getId());
		if (previous == null) {
			allList.remove(previous);
		}
		allList.add(salespoint);
	}
	
	
	public SearchResult searchSalespoints(int start, int pageSize) {
		LOG.info("SERVER Search searchSalespoints, start={} / pageSize={}", start, pageSize);

		ArrayList<Salespoint> salespoints = getAllList();
		int salespointSize = salespoints.size();
		// if (start>=salespointSize) {
		// return new SearchResult( new ArrayList<Salespoint>(0),0, start, pageSize);
		// }
		int toIndex = Math.min(salespointSize, start + pageSize);
		ArrayList<Salespoint> sub = new ArrayList<Salespoint>(salespoints.subList(start, toIndex));
		SearchResult result = new SearchResult(sub, salespoints.size(), start, pageSize);
		LOG.info("Search result {}", result);
		return result;
	}

	@Override
	public Salespoint create(Class<? extends Salespoint> clazz) {
		LOG.info("### Server : create({})", clazz);
		Salespoint user = new Salespoint();
		return user;
	}

	@Override
	public Salespoint find(Class<? extends Salespoint> clazz, Integer id) {
		LOG.info("### Server : find(Salespoint, {})", id);
		return getSalespointById(id);
	}

	@Override
	public Class<Salespoint> getDomainType() {
		LOG.info("### Server : getDomainType()");
		return Salespoint.class;
	}

	@Override
	public Integer getId(Salespoint domainObject) {
		LOG.info("### Server : getId");
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		LOG.info("### Server : getIdType");
		return Integer.class;
	}

	@Override
	public Object getVersion(Salespoint domainObject) {
		LOG.info("### Server : getVersion");
		return domainObject.getVersion();
	}

}
