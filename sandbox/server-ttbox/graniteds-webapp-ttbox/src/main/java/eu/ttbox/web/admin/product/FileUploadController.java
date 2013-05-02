package eu.ttbox.web.admin.product;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/uploadProduct", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		log.info("Start File Upload {}", name);
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				log.info("Upload File {} whith data [{}]", name, bytes );
			} catch (IOException e) {
				log.error("Error uploadinf file " + name  + " : " + e.getMessage(),e);
  			}
			// store the bytes somewhere
			return "redirect:uploadSuccess";
		} else {
			return "redirect:uploadFailure";
		}
	}


}
