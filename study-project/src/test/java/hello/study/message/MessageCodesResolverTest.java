package hello.study.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import hello.study.domain.item.Item;

@SpringBootTest
class MessageCodesResolverTest {

	MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

	@Test
	void messageCodesResolverObject() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
		assertThat(messageCodes).containsExactly("required.item", "required");
	}

	@Test
	void messageCodesResolverField() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
		assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName",
				"required.java.lang.String", "required");
	}
}
