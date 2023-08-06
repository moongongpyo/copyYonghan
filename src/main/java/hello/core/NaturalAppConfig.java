package hello.core;
//스프링을 사용하지 않고 자바만 사용했을 때의 Di컨테이너
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


public class NaturalAppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }

    public MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy DiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
