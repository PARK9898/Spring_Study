package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean(){
		String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //iter하고 텝 누르면 자동으로 for 문 완성됨
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("name = " + beanDefinitionName + "object" + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 출력하기")
	void findApplicationBean(){
		String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //iter하고 텝 누르면 자동으로 for 문 완성됨
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//BeanDefinition은 빈의 클래스 타입, 생성 방법, 라이프사이클 콜백, 의존성 등과 같은 빈에 대한 정보를 저장합니다. 이 정보를 사용하여 스프링 컨테이너는 빈을 생성하고 구성하며, 필요한 경우에는 의존성을 해결하여 빈들 간의 관계를 구성합니다.
			//Application은 내가 애플리케이션을 개발하기 위해서 등록한 빈들 내가 Appconfig에 등롣ㄱ한 빈들만 출력이 된다!
			//ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name = " + beanDefinitionName + "object" + bean);
			}
		}
	}


}
