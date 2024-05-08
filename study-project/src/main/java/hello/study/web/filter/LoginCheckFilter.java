package hello.study.web.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import hello.study.web.session.constant.SessionConst;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter{
	
	private static final String[] whitelist = {"/", "/members/add", "/login", "/logout","/css/*"};

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestURI = httpRequest.getRequestURI();
		
		try {
			boolean loginCheckPath = isLoginCheckPath(requestURI);
			if(loginCheckPath) {
				log.info("인증 체크 로직 실행 {}", requestURI);
				HttpSession session = httpRequest.getSession(false);
				if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
					log.info("미인증 사용자 요청 {}", requestURI);
					httpResponse.sendRedirect("/login?redirectURL="+ requestURI);
					return;
				}
			}
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e; // 톰캣까지 예외를 보내줌
		}finally {
			log.info("인증 체크 필터 종료 {} ", requestURI);
		}
		
		
		
	}


	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
	}

}
