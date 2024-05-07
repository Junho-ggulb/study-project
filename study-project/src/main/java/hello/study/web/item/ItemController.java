package hello.study.web.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.study.domain.item.DeliveryCode;
import hello.study.domain.item.Item;
import hello.study.domain.item.ItemRepository;
import hello.study.domain.item.ItemType;
import hello.study.web.item.dto.ItemSaveForm;
import hello.study.web.item.dto.ItemUpdateForm;
import hello.study.web.item.validation.ItemValidation;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemRepository itemRepository;
	
//	private final ItemValidation itemValidation;

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
	public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		Map<String, String> errors = new HashMap<>();
		
//		itemValidation.validate(item, bindingResult);
		
		if(form.getPrice() != null && form.getQuantity() != null) {
			int resultPrice = form.getPrice() * form.getQuantity();
			if(resultPrice < 10000) {
//				errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice);
//				bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice));
//				bindingResult.addError(new ObjectError("item", new String[] {"totalPriceMin"}, new Object[] {resultPrice}, null));
				bindingResult.reject("totalPriceMin", new Object[] {resultPrice}, null);
			}
		}
		
		
		if(bindingResult.hasErrors()) {
			return "basic/addForm";
		}
		
		Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
		
		
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
	public String updateItem(@PathVariable("itemId") Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {
		
		if(form.getPrice() != null && form.getQuantity() != null) {
			int resultPrice = form.getPrice() * form.getQuantity();
			if(resultPrice < 10000) {
//				errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice);
//				bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice));
//				bindingResult.addError(new ObjectError("item", new String[] {"totalPriceMin"}, new Object[] {resultPrice}, null));
				bindingResult.reject("totalPriceMin", new Object[] {resultPrice}, null);
			}
		}
		log.info("bindingResult={}",bindingResult.getAllErrors());
		
		
		if(bindingResult.hasErrors()) {
			return "basic/editForm";
		}
		
		Item itemParam = new Item();
		 itemParam.setItemName(form.getItemName());
		 itemParam.setPrice(form.getPrice());
		 itemParam.setQuantity(form.getQuantity());
		
		itemRepository.update(itemId, itemParam);
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
