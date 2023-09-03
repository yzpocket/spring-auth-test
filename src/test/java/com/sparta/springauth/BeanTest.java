package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {
    //@Autowired
    //Food food; //Could not autowire. There is more than one bean of 'Food' type. 자동주입 할수없다고 나타난다.
    //Food type 객체가 2개 이상이다 Chicken, Pizza 중 어떤것을 선택할지 정해주지않았기 때문.
    //정해주는 방법은?
    //[1] 명시적으로 Bean의 이름(Pizza 클래스의 빈이름은 pizza)을 적어준다.
    @Autowired
    Food chicken;

    @Autowired
    Food pizza;

    @Test
    @DisplayName("Bean 테스트")
    void test1(){
        pizza.eat();
        chicken.eat();
    }

    //[2] 구현체가 2개 이상 일 때, 둘 중하나 class에 @Primary 작성하면 그 값이 Food의 기본값이 되서 Bean이 등록 될 수 있다.
    //@Autowired
    //Food food; //<- 사실상 chiken Bean이 Primary가 입력되어 있어서 기본값이 된다.

    @Test
    @DisplayName("Primary Bean 테스트")
    void test2(){
        food.eat(); //"치킨을 먹습니다" <- 두개 이상이더라도 Primary를 우선시하여 food bean이 chicken으로 설정된 모뼈.
    }

    //[3] 구현체가 2개 이상 일 때, 둘 중 하나 class에 @Qualifier("chicken")을 작성하면 chicken이라고 명시적으로 구분할 수 있도록 등록 할 수 있다.
    // 해당 @Qualifier("pizza") 어노테이션은 Bean을 초기화하는 부분에서도 @Qualifier("pizza")를 동일하게 작성해 주어야 한다.
    @Autowired
    @Qualifier("pizza") //<- 이 명칭은 Bean 명칭과 정확히 일치해야 찾을 수 있다.
    Food food;

    @Test
    @DisplayName("@Qualifier() 테스트")
    void test3(){
        food.eat();
    }

    //Primary와 Qualifier의 우선순위 확인
    //현재 Primary로는 chicken, Qualifier로는 pizza를 bean으로 등록되었고 동일한 food 이름으로 객체 1개를 생성했다. 둘중 어떤것이 선택되어 출력될까?
    // -> "피자를 먹습니다." 우선순위는 Qualifier가 높다. 하지만 명시적으로 두군데 모두 작성해줘야 된다.
    // 좁은 범위가 보통 우선순위가 높다. Qualifier가 더 좁은 범위로 더욱 명시적인 효과가 있다고 보면 된다. ex)변수의 scope도 호출부분에서는 좁은 범위의 lv가 가장 우선시 된다를 예로 들어 생각하자.
    @Test
    @DisplayName("Primary & Qualifier중 우선순위는?")
    void test4(){
        food.eat();
    }
}
