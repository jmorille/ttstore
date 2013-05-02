package eu.ttbox.service.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("uploadService")
@Transactional 
public class UploadService {

	public String doUpload(byte[] bytes, String fileName) throws Exception {
		String tmpFileName = System.getProperty("java.io.tmpdir") + "/"
				+ fileName;
		File f = new File(tmpFileName);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(bytes);
		fos.close();
		return "success";
	}

	public List<String> getDownloadList() {
		File dir = new File(System.getProperty("java.io.tmpdir"));
		String[] children = dir.list();
		List<String> dirList = new ArrayList<String>();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				dirList.add(children[i]);
			}
		}
		return dirList;
	}

	public byte[] doDownload(String fileName) throws IOException {
		FileInputStream fis = null;
		byte[] data = null;
		FileChannel fc;

		try {
			fis = new FileInputStream(System.getProperty("java.io.tmpdir")
					+ "/" + fileName);
			fc = fis.getChannel();
			data = new byte[(int) (fc.size())];
			ByteBuffer bb = ByteBuffer.wrap(data);
			fc.read(bb);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return data;
	}

}
