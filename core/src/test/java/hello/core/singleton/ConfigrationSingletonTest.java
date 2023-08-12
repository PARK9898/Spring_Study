package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;

public class ConfigrationSingletonTest {
	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);

		//같은 인스턴스이다 멤버 리포지토리를 2번 호출했는데?
	}
	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
		//bean = class hello.core.AppConfig$$SpringCGLIB$$0 CGLIB ~~ 뒤에건 뭐자? 스프링 빈을 등록하는 과정에서 내가 만든 클래스가 아니라 임의의 다른 클래스를 만들고 다른 클래스를 스프링 빈으로 등록

	}
}
