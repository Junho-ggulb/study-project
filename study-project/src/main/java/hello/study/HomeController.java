package hello.study;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import hello.study.domain.member.Member;
import hello.study.web.argumentresolver.Login;
import hello.study.web.session.SessionManager;
import hello.study.web.session.constant.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final SessionManager sessionManager;

//	@GetMapping("/")
//	public String home(@ModelAttribute LocaleDto localeDto, Model model) {
//		model.addAttribute("languages", Language.values());
//
//		return "index";
//	}
//
//	@PostMapping
//	public String changeLocale(@ModelAttribute LocaleDto localeDto, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Locale locale = localeDto.getLocale();
//		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//		return "redirect:/";
//	}

	@GetMapping("/")
	public String homeLoginV2(@Login Member loginMember, HttpServletRequest request, Model model) {
		// 세션 관리자에 저장된 회원 정보 조회
//		Member member = (Member) sessionManager.getSession(request);
//		HttpSession session = request.getSession(false);
//		
//		if (session == null) {
//			return "home";
//		}
		
//		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		// 로그인
		if(loginMember == null) {
			return "home";
		}
		model.addAttribute("member", loginMember);
		return "loginHome";
	}

}
