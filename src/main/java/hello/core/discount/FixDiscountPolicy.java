package hello.core.discount;

import hello.core.annotation.SubDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("subDiscountPolicy")
//Qualifier를 이용하여 직접만든 어노테이션
@SubDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
        return 0;
            }
    }
}
