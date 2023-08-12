package hello.core.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository; // 필수(필수값이 있어야하기 떄문에)
	//private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 직접 객체가 구체적인 선택까지 자기가 선택한다!(안좋은 방법)
	private final DiscountPolicy discountPolicy; // final 은 무조건 값이 할당되어야한다. 생성자 주입을 해주면 값이 세팅된다! 초기단계에 값이 들어와야한다.
	@Autowired // 중요! 생성자가 하나면 Autowired는 자동임 생략해도 상관없다!
	//필드주입 setter주입 확인해보기 각각의 용도에 따라 필드 인젝션은 어차피 Setter를 만들어줘야한다 그냥 선언만 해주는 작업이 때문이다.
	public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy; // memberRepository나 discountPolicy를 생성자 호출 이후에는 건들수가 없다 (*불변)\

	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice); // orderservice는 할인정책에대해서는 아예 모른다 !

		return new Order(memberId,itemName,itemPrice,discountPrice);
	}
	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
