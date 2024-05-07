package hello.study.domain.item;

import lombok.Data;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */

@Data
public class DeliveryCode {
	
	public DeliveryCode(String code, String displayName) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.displayName = displayName;
	}
	private String code;
	private String displayName;

}
