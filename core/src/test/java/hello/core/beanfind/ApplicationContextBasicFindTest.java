package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName(){
		MemberService memberService = ac.getBean("memberService",MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	//assertThat(memberService).isInstanceOf(MemberServiceImpl.class)는 memberService 객체가 MemberServiceImpl
	//클래스의 인스턴스인지를 확인하는 코드입니다. isInstanceOf() 메서드는 주어진 객체가 특정 클래스의 인스턴스인지를 검사합니다.
	// 이를 통해 memberService 객체가 MemberServiceImpl 클래스로 생성된 인스턴스인지를 검증합니다
	@Test
	@DisplayName("이름없이 타입으로 조회")
	void findBeanByType(){
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2(){
		MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class); // 꼭 인터페이스일 필요는 없다 근데 별로 좋지않다 역할에 의존하지 않고 구현에 의존
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	@Test
	@DisplayName("빈 이름으로 조회x")
	void findBeanByNameX(){
		//MemberService xxxx = ac.getBean("xxxx", MemberService.class);
		assertThrows(NoSuchBeanDefinitionException.class,
			() -> ac.getBean("xxxx", MemberService.class)); //오른쪽에 있는 저 함수가 실행되면 왼쪽의 예외가 터져야한다!
	}

}
