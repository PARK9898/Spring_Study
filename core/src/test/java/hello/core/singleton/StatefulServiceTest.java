package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSington(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		//ThreadA: A사용자가 10000원을 주문
		statefulService1.order("userA",10000);
		int userAPrice = statefulService1.order("userA",10000);
		//TreadB: B사용자가 20000원 주문
		statefulService2.order("userB",20000);
		int userBPrice = statefulService1.order("userA",10000); //이렇게 지역변수로 stateless로 설계
		//a가 조회하는 도중에 b가 20000원을 조회했다
		//ThreadA: 사용자 a 가 주문 금액 조회
		//int price = statefulService1.getPrice();

		//org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}