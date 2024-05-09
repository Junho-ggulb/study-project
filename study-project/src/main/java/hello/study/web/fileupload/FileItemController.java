package hello.study.web.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import hello.study.domain.file.FileStore;
import hello.study.domain.fileitem.FileItem;
import hello.study.domain.fileitem.FileItemRepository;
import hello.study.domain.fileitem.UploadFile;
import hello.study.web.fileupload.dto.FileItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileItemController {

	private final FileItemRepository fileItemRepository;
	private final FileStore fileStore;

	@GetMapping("/items/new2")
	public String newItem(@ModelAttribute FileItemForm form) {
		return "item-form";
	}

	@PostMapping("/items/new2")
	public String saveItem(@ModelAttribute FileItemForm form, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException {
		log.info("form={}",form.getAttachFile());
		UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
		List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

		FileItem item = new FileItem();
		item.setItemName(form.getItemName());
		item.setAttachFile(attachFile);
		item.setImageFiles(storeImageFiles);
		FileItem saveItem = fileItemRepository.save(item);
		log.info("saveItem={}", saveItem);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		return "redirect:/items/{itemId}";
	}

	@GetMapping("/items/{id}")
	public String items(@PathVariable("id") Long id, Model model) {
		FileItem item = fileItemRepository.findById(id);
		model.addAttribute("item", item);
		return "item-view";
	}
	
	@ResponseBody
	@GetMapping("/images/{filename}")
	public UrlResource downloadImage(@PathVariable("filename") String filename) throws MalformedURLException {
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}
	
	@GetMapping("/attach/{id}")
	public ResponseEntity<UrlResource> attach(@PathVariable("id") Long id) throws IOException {
		FileItem item = fileItemRepository.findById(id);
		UploadFile attachFile = item.getAttachFile();
		String storeFileName = attachFile.getStoreFileName();
		String uploadFileName = attachFile.getUploadFileName();
		
		String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
		
		UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
		log.info("urlResource={}",urlResource.getFilename());
//		InputStream openStream = new URL("file:"+fileStore.getFullPath(storeFileName)).openStream();
//		FileCopyUtils.copy(openStream, );
//		log.info("openStream={}",openStream.read());
		String contentdisposition = "attachment; filename=" + encodedUploadFileName + ";";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentdisposition).body(urlResource);
		
	}

}
