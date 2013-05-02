package eu.ttbox.batch.ingram.price.list;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import eu.ttbox.batch.core.fs.WorkingLocalFileSystemAccessor;
import eu.ttbox.model.salespoint.SalespointSupplier;

public class FileDomainListItemReader implements ItemReader<PriceDifferentialVO>, ItemStream {

	private static final Logger log = LoggerFactory.getLogger(FileDomainListItemReader.class);

	ItemReader<SalespointSupplier> itemReaderResourceMaster;

	int fileIdGroupId = 1;

	WorkingLocalFileSystemAccessor workingLocalFolder;

	String filenamePattern;

	private Map<String, File> fileById;

	public FileDomainListItemReader() {
		super();
	}

	public FileDomainListItemReader(WorkingLocalFileSystemAccessor workingLocalFolder, String filenamePattern) {
		super();
		this.workingLocalFolder = workingLocalFolder;
		this.filenamePattern = filenamePattern;
	}

	public void setItemReaderResourceMaster(ItemReader<SalespointSupplier> itemReaderResourceMaster) {
		this.itemReaderResourceMaster = itemReaderResourceMaster;
	}

	public void setFileIdGroupId(int fileIdGroupId) {
		this.fileIdGroupId = fileIdGroupId;
	}

	public void setFileById(Map<String, File> fileById) {
		this.fileById = fileById;
	}

	public void setWorkingLocalFolder(WorkingLocalFileSystemAccessor workingLocalFolder) {
		this.workingLocalFolder = workingLocalFolder;
	}

	public void setFilenamePattern(String relativePath) {
		this.filenamePattern = relativePath;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		try {
			Pattern pattern = Pattern.compile(filenamePattern);
			List<File> files = workingLocalFolder.getLocalFilesInFolder(pattern);
			Map<String, File> localFileById = new HashMap<String, File>(files.size());
			for (File file : files) {
				String filename = file.getName();
				Matcher m = pattern.matcher(filename);
				if (m.matches()) {
					String fileId = m.group(fileIdGroupId);
					localFileById.put(fileId, file);
				}

			}
			fileById = localFileById;
			log.info("Register {} files to ItemReader", files.size());
		} catch (IOException e) {
			throw new ItemStreamException("Error in getting Files List " + filenamePattern + " : " + e.getMessage(), e);
		}
		// Master Iterm
		if (itemReaderResourceMaster instanceof ItemStream) {
			((ItemStream) itemReaderResourceMaster).open(executionContext);
		}
	}

	@Override
	public PriceDifferentialVO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		PriceDifferentialVO diffVo = null;
		if (fileById != null) {
			SalespointSupplier masterItem = null;
			File supplierFile;
			String supplierId;
			do {
				supplierFile = null;
				supplierId = null;
				masterItem = itemReaderResourceMaster.read();
				if (masterItem != null) {
					supplierId = masterItem.getSupplierId();
					supplierFile = fileById.get(supplierId);
					log.info("Read MasterItem {} associated to Local File {}", masterItem, supplierFile);
				}
			} while (supplierFile == null && masterItem != null);
			// create Differential VO
			if (supplierFile != null && masterItem != null) {
				diffVo = new PriceDifferentialVO(supplierId, supplierFile, masterItem.getSalespoint());
			}

		}
		return diffVo;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// Master Iterm
		if (itemReaderResourceMaster instanceof ItemStream) {
			((ItemStream) itemReaderResourceMaster).update(executionContext);
		}

	}

	@Override
	public void close() throws ItemStreamException {
		fileById = null;
		// Master Iterm
		if (itemReaderResourceMaster instanceof ItemStream) {
			((ItemStream) itemReaderResourceMaster).close();
		}

	}

}
