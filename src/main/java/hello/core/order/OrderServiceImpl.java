package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    //private  final MemberRepository memberRepository = new MemoryMemberRepository();
    //private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private  final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // DIP 원칙을 지키기 위해 추상화된 인터페이스만 의존하도록 변경 ->
    private  final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
