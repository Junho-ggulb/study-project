package hello.study.web.fileupload.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileItemForm {
	
	private Long id;
	private String itemName;
	private MultipartFile attachFile;
	private List<MultipartFile> imageFiles;

}
