package hello.core.scope;

import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


	}

	@Test
	void singletonClientUserPrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class ,PrototypeBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		Assertions.assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		Assertions.assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	static class ClientBean {
		//private final PrototypeBean prototypeBean;

		@Autowired
		private Provider<PrototypeBean> prototypeBeanProvider;


		// @Autowired
		// public ClientBean(PrototypeBean prototypeBean) { // 프로토타입 빈을 클라이언트 빈이 관리한다 계속 생성 x 그럼 프로토타입 빈을 쓰는게 의미 없지 않나?
		// 	this.prototypeBean = prototypeBean;
		// }

		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			int count  = prototypeBean.getCount();
			return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		public int getCount() {
			return count;
		}
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destory() {
			System.out.println("prototypeBean.destory");
		}
	}
}
