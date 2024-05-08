package hello.study.web.resolver;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.study.web.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub

		try {

			if (ex instanceof UserException) {
				log.info("UserException resolver to 400");
				String acceptHeader = request.getHeader("accept");
				log.info("acceptHeader={}", acceptHeader);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				if ("application/json".equals(acceptHeader)) {
					Map<String, Object> errorResult = new HashMap<>();
					errorResult.put("ex", ex.getClass());
					errorResult.put("msg", ex.getMessage());

					String result = objectMapper.writeValueAsString(errorResult);
					response.setContentType("application/json");
					response.setCharacterEncoding("utf-8");
					response.getWriter().write(result);
					return new ModelAndView();

				}else {
					return new ModelAndView("error/4xx");
				}
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("resolver ex", e);
		}

		return null;
	}

}
