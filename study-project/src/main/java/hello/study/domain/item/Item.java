package hello.study.domain.item;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {


	private Long Id;
	private String itemName;
	private Integer price;
	private Integer quantity;
	
	private Boolean open;
	private List<String> regions;
	private ItemType itemType;
	private String deliveryCode;
	
	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

}
