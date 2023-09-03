package com.sparta.springauth.food;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //[2]방법 -> @Primary가 추가되면, 2개 이상의 Bean이 등록되더라도 기본값으로 설정 된다. -> Food food; 사용가습
public class Chicken implements Food { //인터페이스의 구현체에 @Component를 등록하면 Bean으로 등록자체는 문제되지않는다.
    @Override
    public void eat() {
        System.out.println("치킨을 먹습니다.");
    }
}