package hello.study.cookie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import hello.study.domain.member.Member;
import hello.study.web.session.SessionManager;

@SpringBootTest
class SessionManagerTest {
	
	SessionManager sessionManager = new SessionManager();

	@Test
	void SessionTest() {
		//given
		Member member = new Member();
		MockHttpServletResponse response = new MockHttpServletResponse();
		sessionManager.createSession(member, response);
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(response.getCookies());
		//when
		
		Object result = sessionManager.getSession(request);
		
		
		//then
		assertThat(result).isEqualTo(member);
		
		sessionManager.expire(request);
		Object expired = sessionManager.getSession(request);
		assertThat(expired).isNull();
		
	}

}
