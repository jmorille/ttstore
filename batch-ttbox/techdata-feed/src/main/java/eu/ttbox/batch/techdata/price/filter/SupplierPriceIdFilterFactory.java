package eu.ttbox.batch.techdata.price.filter;

import eu.ttbox.batch.techdata.core.fs.filter.FtpFileIdFileListFilter;
import eu.ttbox.batch.techdata.dao.TTBoxDAO;
import eu.ttbox.model.supplier.SupplierEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplierPriceIdFilterFactory //implements FactoryBean<FtpFileIdFileListFilter>
{

	@Autowired
	TTBoxDAO ttbox;

	String patternString = "(?i)Price_Feed_([0-9]+)\\.txt(\\.gz)?";
	
	int groupId =1;

	
	
	public void setTtbox(TTBoxDAO ttbox) {
		this.ttbox = ttbox;
	}

	public void setPatternString(String patternString) {
		this.patternString = patternString;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	private FtpFileIdFileListFilter getFilter() { 
		List<String> acceptFileIds = ttbox.getSupplierIds(SupplierEnum.TECHDATA);
		FtpFileIdFileListFilter filter = new FtpFileIdFileListFilter(patternString, groupId, acceptFileIds);
		return filter;
	}

//	@Override
	public FtpFileIdFileListFilter getObject() throws Exception { 
		return getFilter();
	}

//	@Override
//	public Class<?> getObjectType() {
//		return FtpFileIdFileListFilter.class;
//	}

//	@Override
//	public boolean isSingleton() {
//		return false;
//	}
}
