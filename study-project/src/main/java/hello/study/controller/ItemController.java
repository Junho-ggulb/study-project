package hello.study.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.study.domain.DeliveryCode;
import hello.study.domain.Item;
import hello.study.domain.ItemRepository;
import hello.study.domain.ItemType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemRepository itemRepository;

	@ModelAttribute("regions")
	public Map<String, String> regions() {
		Map<String, String> regions = new LinkedHashMap<>();
		regions.put("SEOUL", "서울");
		regions.put("BUSAN", "부산");
		regions.put("JEJU", "제주");
		return regions;
	}

	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes() {
		log.info("itemTpye={}", ItemType.values());
		return ItemType.values();
	}

	@ModelAttribute("deliveryCodes")
	public List<DeliveryCode> deliveryCodes() {
		List<DeliveryCode> deliveryCodes = new ArrayList<>();
		deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
		deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
		deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
		return deliveryCodes;
	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();

		model.addAttribute("items", items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable("itemId") Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "basic/item";

	}

	@GetMapping("/add")
	public String addForm(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "basic/addForm";
	}

	@PostMapping("/add")
	public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/{itemId}";
	}

	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable("itemId") Long itemId, Model model) {
		Item findItem = itemRepository.findById(itemId);
		model.addAttribute("item", findItem);
		return "basic/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";

	}

	/**
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}

}
