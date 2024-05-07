package hello.study.message;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class MessageSourceTest {
	
	@Autowired
	MessageSource ms;

	@Test
	void helloMessage() {
		log.info("ms ={}", ms);
		String message = ms.getMessage("hello", null, Locale.KOREA);
		
		assertThat(message).isEqualTo("안녕");
	}
	
	@Test
	void argumentMessage() {
		String message = ms.getMessage("hello.name", new Object[] {"테스트"}, Locale.KOREA);
		
		assertThat(message).isEqualTo("안녕 테스트");
	}

}
