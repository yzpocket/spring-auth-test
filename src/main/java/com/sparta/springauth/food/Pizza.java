package com.sparta.springauth.food;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pizza") //[3]방법 -> @Qualifier 추가되면, 대명사를 지정하는 것 처럼 Bean 명칭을 명시적으로 지정 하게 된다.
public class Pizza implements Food { //인터페이스의 구현체에 @Component를 등록하면 Bean으로 등록자체는 문제되지않는다.
    @Override
    public void eat() {
        System.out.println("피자를 먹습니다.");
    }
}