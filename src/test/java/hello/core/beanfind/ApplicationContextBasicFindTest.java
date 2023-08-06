package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로")
    void findBeanByName(){

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);


    }

    //구현보단 역할에 의존해야 하기 때문에 이상적으로 좋은 코드는 아니나 종종 사용할 때 가 있음
    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2(){

        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    //실패코드
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){

        assertThrows(NoSuchBeanDefinitionException.class,
                () ->ac.getBean("XXXX", MemberService.class));


    }

}
