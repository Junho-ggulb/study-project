package hello.study.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {
	
	private Long id;
	
	@NotEmpty
	private String loginId; //로긍니 ID
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;

}
