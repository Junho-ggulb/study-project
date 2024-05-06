package hello.study.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	
	private static final Map<Long, Item> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Item save(Item item) {
		
		
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}
	
	public Item findById(Long id) {
		return store.get(id);
	}
	
	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void update(Long itemId, Item item) {
		Item findItem = findById(itemId);
		findItem.setItemName(item.getItemName());
		findItem.setPrice(item.getPrice());
		findItem.setQuantity(item.getQuantity());
		findItem.setOpen(item.getOpen());
		findItem.setDeliveryCode(item.getDeliveryCode());
		findItem.setRegions(item.getRegions());
		findItem.setItemType(item.getItemType());
	}
	
	public void clearStore() {
		store.clear();
	}

}
