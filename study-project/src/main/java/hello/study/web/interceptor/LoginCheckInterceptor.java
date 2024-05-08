package hello.study.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import hello.study.web.session.constant.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession(false);
		
		log.info("인증 체크 인터셉터 실행 {}", requestURI);
		if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
			log.info("미인증 사용자 요청");
			response.sendRedirect("/login?" + requestURI);
			return false;
		}
		
		
		return true;
	}

}
