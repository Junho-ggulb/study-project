package hello.study.domain.item;

import lombok.Getter;

@Getter
public enum ItemType {
	
	BOOK("도서"), FOOD("식품"), ECT("기타");
	
	private final String description;
	
	private ItemType(String description) {
		// TODO Auto-generated constructor stub
		this.description = description;
	}
	

}
