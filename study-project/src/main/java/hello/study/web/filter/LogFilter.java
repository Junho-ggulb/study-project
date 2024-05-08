package hello.study.web.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter{
	
	@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String requestURI = httpServletRequest.getRequestURI();
			
			String uuid = UUID.randomUUID().toString();
			
			log.info("REQUEST [{}][{}][{}]", uuid, httpServletRequest.getDispatcherType(), requestURI);
			chain.doFilter(request, response);
			
		}

}
