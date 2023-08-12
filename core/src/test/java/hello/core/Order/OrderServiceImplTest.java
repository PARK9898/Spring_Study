package hello.core.Order;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;

class OrderServiceImplTest {

	@Test
	void creareOrder() {
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L,"name", Grade.VIP));

		OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy()); // 수정자 주입을 하면 필요한 것들을 직접 넣어줘야함
		Order order = orderService.createOrder(1L,"itemA",10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

		//테스트코드를 순수한 자바코드로 만든 경우
	}

}