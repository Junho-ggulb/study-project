package hello.study.web.login.controller;

import java.net.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.study.domain.login.LoginService;
import hello.study.domain.member.Member;
import hello.study.web.login.LoginForm;
import hello.study.web.session.SessionManager;
import hello.study.web.session.constant.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	
	private final SessionManager sessionManager;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute LoginForm form, @RequestParam(name = "redirectURL",defaultValue ="/") String redirectURL, BindingResult bindingResult, HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		
		Member loginMember = loginService.Login(form.getLoginId(), form.getPassword());
		log.info("login? {}", loginMember);
		
		if(loginMember == null) {
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		//로그인 성공 처리 TODO
//		sessionManager.createSession(loginMember, response);
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		return "redirect:" + redirectURL;
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
//	 sessionManager.expire(request);
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
	 return "redirect:/";
	}

}
