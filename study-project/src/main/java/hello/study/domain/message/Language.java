package hello.study.domain.message;

import java.util.Locale;

import lombok.Getter;

@Getter
public enum Language {
	
	BASIC("한국어", Locale.KOREA), EGLISH("영어", Locale.ENGLISH);
	
	private String lang;
	private Locale locale;
	
	private Language(String lang, Locale locale) {
		// TODO Auto-generated constructor stub
		this.lang = lang;
		this.locale = locale;
	}

}
