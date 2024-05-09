package hello.study.domain.fileitem;

import java.util.List;

import lombok.Data;

@Data
public class FileItem {
	
	private Long id;
	private String itemName;
	private UploadFile attachFile;
	private List<UploadFile> imageFiles;

}
