package hello.study.message;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootTest
class SessionLocaleResolverTest {
	
	@Autowired private MockHttpSession session;

	@Test
	void test() {
		//given
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.ENGLISH);
		//when
		
		Locale locale = (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		//then
		assertThat(locale).isEqualTo(Locale.ENGLISH);
	}
	
	@Test
	void removeSessionLocale() {
		//given
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.ENGLISH);
		
		//when
		session.removeAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		Locale locale = (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		//then
		assertThat(locale).isNull();
	}

}
