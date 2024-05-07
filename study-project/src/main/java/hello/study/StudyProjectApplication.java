package hello.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageEncoder;

@SpringBootApplication
public class StudyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyProjectApplication.class, args);
	}

}
