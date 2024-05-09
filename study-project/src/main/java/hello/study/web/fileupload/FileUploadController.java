package hello.study.web.fileupload;

import java.io.IOException;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/file-upload")
public class FileUploadController {
	
	@GetMapping
	public String fileUpload() {
		return "fileupload/list";
	}
	
	@GetMapping("/v1/upload")
	public String servletUploadV1() {
		return "fileupload/upload-form";
	}
	
	@PostMapping("/v1/upload")
	public String sabeFileV1(HttpServletRequest request) throws IOException, ServletException {
		log.info("request={}",request);
		
		String itemName = request.getParameter("itemName");
		log.info("itemName={}", itemName);
		
		Collection<Part> parts = request.getParts();
		log.info("parts={}", parts);
		
		return "fileupload/upload-form";
	}
	

}
