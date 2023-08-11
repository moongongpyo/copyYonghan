package hello.core.order;

import hello.core.annotation.SubDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

//AutoAppConfigTest에서는 ac에 AutoAppConfig.class 를 박아놔서 @Component 가 적용되지만 실제 앱은 빈 이름을 discountPolicy로
//맞추어서 수동 설정인 appConfig.class 가 적용된다. 의도한 바와 달라질 수 있어서 조심해야 한다.
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    //private  final MemberRepository memberRepository = new MemoryMemberRepository();
    //private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private  final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // DIP 원칙을 지키기 위해 추상화된 인터페이스만 의존하도록 변경 ->

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //필드 주입(권장하지 않음)
    /*
    @Autowired private   MemberRepository memberRepository;
    @Autowired private  DiscountPolicy discountPolicy;
    */

    //수정자 주입
    /*    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    //일반 매서드 주입(사용할 일이 거의 없다)
    /* @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    //생성자 주입
    @Autowired
    //Qualifier로 subDiscountPolicy 주입
    //public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("subDiscountPolicy") DiscountPolicy discountPolicy) {
    //public OrderServiceImpl(MemberRepository memberRepository, @SubDiscountPolicy DiscountPolicy discountPolicy) {
    //Primary로 mainDiscountPolicy 주입
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
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
