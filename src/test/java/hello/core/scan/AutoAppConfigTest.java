package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        OrderService orderservice = ac.getBean(OrderService.class);
        assertThat(orderservice).isInstanceOf(OrderService.class);

    }
    @Test
    void basicScan2(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberservice = ac.getBean(MemberService.class);
        assertThat(memberservice).isInstanceOf(MemberService.class);

    }
}
