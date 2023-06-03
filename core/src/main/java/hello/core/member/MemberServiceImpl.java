package hello.core.member;

public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository = new MemoryMemberRepository();
  	//memberServiceImpl은 추상체에도 의존하고 구현체에도 의존하게 되어있음 현재
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
