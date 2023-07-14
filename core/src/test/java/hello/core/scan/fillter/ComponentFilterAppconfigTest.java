package hello.core.scan.fillter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppconfigTest {
	@Test
	void filterScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA", BeanA.class);
		BeanB beanB = ac.getBean("beanB", BeanB.class);

		Assertions.assertThrows(
			NoSuchBeanDefinitionException.class,
			() -> ac.getBean("beanB", BeanB.class)
		);
	}
	@Configuration
	@ComponentScan(
		includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
	)
	 static class ComponentFilterAppConfig {

	}
}
