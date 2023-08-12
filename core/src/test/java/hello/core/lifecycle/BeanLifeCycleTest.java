package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
	@Test
	public void lifeCycleTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();
	}
	@Configuration
	static class LifeCycleConfig {

		@Bean//(initMethod = "init",destroyMethod = "close")
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.seturl("http://hello-spring.dev"); // 객체 생성 후 세팅하는 경우임
			return networkClient;
		}
	}
}
