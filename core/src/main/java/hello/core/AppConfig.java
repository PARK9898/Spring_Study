package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;


public class AppConfig {
    //커멘드 + e 이전 히스토리 보기
	public MemberService memberService(){
		return new MemberServiceImpl(memberRepository());
	}  //AppConfig을 통해서 MemberService를 불러다 씀

	private MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	public OrderService orderService(){
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	public DiscountPolicy discountPolicy(){
		//return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
