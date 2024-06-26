package hello.study.web.resolver;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		try {
			if (ex instanceof IllegalArgumentException) {
				log.info("IllegalArgumentException resolver to 400");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
				return new ModelAndView();
			}
			// 정상 흐름으로 서블릿 리턴 됨
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("resolver ex", e);
		}
		return null;
	}

}
