package hello.study.domain.fileitem;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class FileItemRepository {
	
	private final Map<Long, FileItem> store = new HashMap<>();
	private long sequence = 0L;
	
	public FileItem save(FileItem item) {
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}
	
	public FileItem findById(Long id) {
		return store.get(id);
	}

}
