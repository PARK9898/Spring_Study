package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository;
	//private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 직접 객체가 구체적인 선택까지 자기가 선택한다!(안좋은 방법)
	private final DiscountPolicy discountPolicy; // final 은 무조건 값이 할당되어야한다.

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice); // orderservice는 할인정책에대해서는 아예 모른다 !

		return new Order(memberId,itemName,itemPrice,discountPrice);
	}
}
