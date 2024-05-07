package hello.study.web.item.dto;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import hello.study.domain.item.ItemType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemSaveForm {

	private Long Id;

	@NotBlank
	private String itemName;

	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;
	@NotNull
	@Max(value = 9999)
	
	private Integer quantity;
	private Boolean open;
	private List<String> regions;
	private ItemType itemType;
	private String deliveryCode;

}
