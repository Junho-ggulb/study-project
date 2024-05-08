package hello.study.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hello.study.web.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionController {
	
	@GetMapping("/api/members/{id}")
	public MemberDto getMember(@PathVariable("id") String id) {
		if(id.equals("ex")) {
			throw new RuntimeException("잘못된 사용자");
		}
		if(id.equals("bad")) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}
		if(id.equals("user-ex")) {
			log.info("진입");
			throw new UserException("사용자 오류");
		}
		
		return new MemberDto(id, "hello "+  id);
	}
	
	
	
	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String memberId;
		private String name;
	}

}
