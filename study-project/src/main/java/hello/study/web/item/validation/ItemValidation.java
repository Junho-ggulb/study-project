package hello.study.web.item.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hello.study.domain.item.Item;

@Component
public class ItemValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		
		return Item.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		// TODO Auto-generated method stub
		Item item = (Item) target;
		
//		log.info("objectName={}", bindingResult.getObjectName());
//		log.info("target={}", bindingResult.getTarget());
		//MessageCodesResolver 가 자동으로 code + object + field 로 조합해서 찾아준다.
		if(!StringUtils.hasText(item.getItemName())) {
//			errors.put("itemName", "상품 이름은 필수입니다.");
//			bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다"));
//			bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[] {"required.item.itemName"}, null, ""));
//			bindingResult.rejectValue("itemName", "required");
			error.rejectValue("itemName", "required");
		}
		
		if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
//			errors.put("price", "가격은 1,000 ~ 100,000 까지 허용합니다.");
//			bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 100,000 까지 허용합니다."));
//			bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[] {"range.item.price"}, new Object[] {"1,000", "100,000"}, null));
//			bindingResult.rejectValue("price", "range", new Object[] {"1000,1000000"}, null);
			error.rejectValue("price", "range", new Object[] {"1000,1000000"}, null);
		}
		if(item.getQuantity() == null || item.getQuantity() > 9999) {
//			errors.put("quantity", "수량은 최대 9,999 까지 허용합니다.");
//			bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999 까지 허용합니다."));
//			bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[] {"max.item.quantity"},new Object[] {"9,999"}, "수량은 최대 9,999 까지 허용합니다."));
//			bindingResult.rejectValue("quantity", "max", new Object[] {"9999"}, null);
			error.rejectValue("quantity", "max", new Object[] {"9999"}, null);
		}
		
		if(item.getPrice() != null && item.getQuantity() != null) {
			int resultPrice = item.getPrice() * item.getQuantity();
			if(resultPrice < 10000) {
//				errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice);
//				bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = "+ resultPrice));
//				bindingResult.addError(new ObjectError("item", new String[] {"totalPriceMin"}, new Object[] {resultPrice}, null));
//				bindingResult.reject("totalPriceMin", new Object[] {resultPrice}, null);
				error.reject("totalPriceMin", new Object[] {resultPrice}, null);
			}
		}
	}

}
