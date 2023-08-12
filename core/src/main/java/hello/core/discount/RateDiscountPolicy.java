package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
@Component
//@Primary // 최상위 빈으로 잡히면서 의존관계 주입이 된다
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) { //커멘드 시프트 t -> test 코드
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
