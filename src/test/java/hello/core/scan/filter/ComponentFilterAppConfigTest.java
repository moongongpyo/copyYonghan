package hello.core.scan.filter;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();


        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }


    @Configuration
    @ComponentScan(
            includeFilters = @Filter(classes = MyIncludeComponent.class), //@Component 를 사용하면 자동 스캔 하기 때문에 굳이 하지 않음
            excludeFilters = {
                    @Filter(classes = MyExcludeComponent.class), //MyExcludeComponent 어노테이션이 붙은 클래스만 제외
                    //@Filter(type = FilterType.ASSIGNABLE_TYPE,classes = BeanA.class) //클래스 A만 따로 지정하여 제외시키고 싶을 때
            }
    )
    static class ComponentFilterAppConfig{

    }
}
