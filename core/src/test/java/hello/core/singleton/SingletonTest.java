package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		MemberService memberService1 = appConfig.memberService();

		MemberService memberService2 = appConfig.memberService();
		//참조값이 다른 것을 확인
		//객체를 계속 만드는것은 효울적이지 못함
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest(){
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		Assertions.assertThat(singletonService1).isSameAs(singletonService2);
		//same이란 ==
		//equals 랑다름
		//스프링 컨테이너를 쓰면 기본적으로 객체를 싱글톤으로 관리해줌 -> 성능 향상
		}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberService memberService1 = ac.getBean("memberService",MemberService.class);
		MemberService memberService2 = ac.getBean("memberService",MemberService.class);


		Assertions.assertThat(memberService1).isSameAs(memberService2);
	}
}
