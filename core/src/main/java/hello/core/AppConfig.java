package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
//call AppConfig.memberService
//call AppConfig.memberRepository
//call Appconfig.orderService
//이렇게 세번만 호출되네??
@Configuration
public class AppConfig {
    //커멘드 + e 이전 히스토리 보기
	//@Bean이걸 사용하면 메서드에서 반환되는 객체를 빈으로 등록해준다.
	//@Bean memberService -> new MemmoryMemberRepository()
	//@Bean orderService -> new MemortMemberRepoisitory() -> 그럼 싱글톤이 깨지는게 아닌가?
	//결과적으로 각각 다른 2개의 MemoryMemberRepository()가 생성되면서 싱글톤이 꺠지는것처럼 보인다
	@Bean
	public MemberService memberService(){
		System.out.println("call Appcongig.memberService");
		return new MemberServiceImpl(memberRepository()); //함수를 넣어도 객체가 들어간다 밑에서 MemoryMemberRepoistory()를 뱉어내니까
	}  //AppConfig을 통해서 MemberService를 불러다 씀
	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call Appcongig.MemberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService(){
		System.out.println("call Appcongig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy(){
		//return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
