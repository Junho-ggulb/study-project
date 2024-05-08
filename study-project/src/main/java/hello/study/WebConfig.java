package hello.study;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import hello.study.web.argumentresolver.LoginMemberArgumentResolver;
import hello.study.web.interceptor.LogInterceptor;
import hello.study.web.resolver.MyHandlerExceptionResolver;
import hello.study.web.resolver.UserHandlerExceptionResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// TODO Auto-generated method stub
		resolvers.add(new LoginMemberArgumentResolver());
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}
	
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		// TODO Auto-generated method stub
		resolvers.add(new MyHandlerExceptionResolver());
		resolvers.add(new UserHandlerExceptionResolver());
	}
	
//	@Bean
//	public FilterRegistrationBean<Filter> logFilter() {
//		FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
//		filterRegistration.setFilter(new LogFilter());
//		filterRegistration.setOrder(1);
//		filterRegistration.addUrlPatterns("/*");
//		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//		return filterRegistration;
//	}
//	
//	@Bean
//	public FilterRegistrationBean<Filter> loginFilter() {
//		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new LoginCheckFilter());
//		filterRegistrationBean.setOrder(2);
//		filterRegistrationBean.addUrlPatterns("/*");
//		return filterRegistrationBean;
//	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**", "/*.ico", "/error", "/error-page/**");
//		registry.addInterceptor(new LoginCheckInterceptor())
//				.order(2)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/", "/members/add", "/login", "/logout",
//						 "/css/**", "/*.ico", "/error");
	}


}
