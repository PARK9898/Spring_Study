package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;

@Scope
@Configuration
@ComponentScan(
	basePackages = "hello.core" , //이 패키지부터 밑으로 컴포넌트 스캔을 해준다.
	basePackageClasses = AutoAppConfig.class , //지정하지 않으면 하위패키지 다 뒤진다
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
)
public class AutoAppConfig {
	//@Bean(name = "memoryMemberRepository")
	//public MemberRepository memberRepository() {
	//	return new MemoryMemberRepository();
	//} // 자동 빈 등록과 수동 빈 등록이 충돌하면 수동 빈 등록이 우선권을 가진다 하지만 스프링은 이제 오류가 나도록 하게 한다
	//@bean으로 등록한 클래스가 하나도 없다
}
