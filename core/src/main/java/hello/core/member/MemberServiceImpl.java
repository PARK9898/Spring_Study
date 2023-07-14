package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	//의존성 주입 (부모는 자식을 품을 수 있다)
	@Autowired // 스프링이 여기에 MemberRepository에 맞는 지금 componet로 설정되어있는 MemoryMemberRepository를 넣어준다 의존관계 자동으로 주입
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	//memberServiceImpl은 추상체에도 의존하고 구현체에도 의존하게 되어있음 현재
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	//테스트용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
