package hello.study.domain.login;

import org.springframework.stereotype.Service;

import hello.study.domain.member.Member;
import hello.study.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;

	/**
	 * @return null이면 로그인 실패
	 */
	public Member Login(String loginId, String password) {
		return memberRepository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
	}

}
