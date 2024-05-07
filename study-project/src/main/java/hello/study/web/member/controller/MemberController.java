package hello.study.web.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.study.domain.member.Member;
import hello.study.domain.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberRepository memberRepository;

	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
		return "members/addMemberForm";
	}

	@PostMapping("/add")
	public String save(@Valid @ModelAttribute Member member, BindingResult result) {
		if (result.hasErrors()) {
			return "members/addMemberForm";
		}
		memberRepository.save(member);
		return "redirect:/";
	}
	
	/**
	 * 테스트용 데이터 추가
	 */
	 @PostConstruct
	 public void init() {
	 Member member = new Member();
	 member.setLoginId("test");
	 member.setPassword("test!");
	 member.setName("테스터");
	 memberRepository.save(member);
	 }

}
