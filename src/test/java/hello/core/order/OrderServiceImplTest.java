package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;



class OrderServiceImplTest {

    //순수한 자바 코드 테스트

    //수정자 주입시 nullPointException 발생
    //어디서 오류가 나는지 찾기 힘듬
    //생성자 주입시 필요한 매개변수가 없으므로 컴파일 오류 발생
   /* @Test
    void createOrder(){
        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1l,"itemA",10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }*/

    // ->
    // 코드 수정

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1l,"itemA",20000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}