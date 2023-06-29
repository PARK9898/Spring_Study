package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

@Configuration
public class AppConfig {
    //커멘드 + e 이전 히스토리 보기
	@Bean
	public MemberService memberService(){
		return new MemberServiceImpl(memberRepository());
	}  //AppConfig을 통해서 MemberService를 불러다 씀
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService(){
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy(){
		//return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
