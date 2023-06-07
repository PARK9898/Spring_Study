package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
