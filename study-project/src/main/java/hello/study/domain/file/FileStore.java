package hello.study.domain.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hello.study.domain.fileitem.UploadFile;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class FileStore {
	
	@Value("${file.dir}")
	private String fileDir;
	
	public String getFullPath(String filename) {
		return fileDir + filename;
	}
	
	public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IllegalStateException, IOException {
		List<UploadFile> storeFileResult = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {				
				storeFileResult.add(storeFile(multipartFile));
			}
		}
		return storeFileResult;
	}
	
	public UploadFile storeFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		if(multipartFile.isEmpty()) {
			return null;
		}
		log.info("multipartFile={}",multipartFile.isEmpty());
		String originalFilename = multipartFile.getOriginalFilename();
		
		String storeFileName = createStoreFileName(originalFilename);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		
		return new UploadFile(originalFilename, storeFileName);
	}

	private String createStoreFileName(String originalFilename) {
		// TODO Auto-generated method stub
		String ext = extractExt(originalFilename);
		String uuid = UUID.randomUUID().toString();
		String storeFileName = uuid + "." + ext;
		return storeFileName;
	}

	private String extractExt(String originalFilename) {
		int pos = originalFilename.indexOf(".");
		String ext = originalFilename.substring(pos + 1);
		return ext;
	}
	

}
