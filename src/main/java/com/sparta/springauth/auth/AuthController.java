package com.sparta.springauth.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//Spring에서는 Cookie class를 통해 cookie의 생성과 조회를 제공해주고 있다.
@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization"; // 헤더의 Name을 상수 변수에 넣어두자. 아래 들어가면 메소드가 복잡해지기때문 크게 상관은없음.

    // 쿠키 생성 메소드
    @GetMapping("/create-cookie") // <- http://localhost:8080/api/create-cookie 로 테스트해보자.
    public String createCookie(HttpServletResponse res) {//<-- 아래 쿠키 생성 메소드에서 Cookie 는 Name,Value로 만들어졌다, 그리고 setPath로 요청의 적용 범위를 저장했고, setMaxAge를 통해 30분이 설정되었다.
        addCookie("Robbie Auth", res); //<-- Value는 "Robbie Auth"과 비어있는 객체인 res를 인자로 메소드에 전달한다.  -> 쿠키추가 메소드를 통해 res에 각종정보들이 담긴 res객체가 돌아왔다.

        return "createCookie"; //메소드에서 addCookie(cookie)를 통해 저장이 끝났으며, 옆 쿠키생성 메시지 문자열을 클라이언트에게 반환해준다.
    }

    // 쿠키 조회 메소드
    @GetMapping("/get-cookie") // <-http://localhost:8080/api/get-cookie  로 테스트해보자.
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
        System.out.println("value = " + value); //value = Robbie Auth 콘솔에서도 쿠키가 잘들어온다.

        return "getCookie : " + value; //반환했으므로 getCookie : Robbie Auth 가 클라이언트 브라우저에서 보여진다. //개발자도구에서도 요청에대한 get-cookie에 대한 헤더를 보면 Authorization=Robbie%20Auth가 쿠키에 보여진다.
    }

    public static void addCookie(String cookieValue, HttpServletResponse res) { //get메소드에 따라 쿠키저장 메소드인 이부분이 실행된다. value와 빈 res 객체가 매개변수로 전달되어들어왔다.
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 URLEncoder를 통해서 encoding 진행 -> 공백을 %20로 바꿔줌

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value // Spring에서는 Cookie 를 사용 할 수 있도록 Cookie 클래스를 제공하고 있다. <여기서 쿠키객체를 만든다. value는 호출부로부터 전달된 value를 사용하고, name은 cv로 선언된 클래스변수 상수를 사용하고 있다.
            cookie.setPath("/"); // cookie의 path를 설정 cookie의 path는 뭘해주는 걸까? 저장위치인가? -> 아니다 모든 경로가 호출될 때 Cookie 값을 전달할 수 있도록 root경로를 나타내는것이다.
            cookie.setMaxAge(30 * 60); // -> setMaxAge는 cookie가 브라우저가 닫혀도 저장되는 시간을 말한다 30*60은 30번 * 60초로 30분을 이야기한다.

            // Response 객체에 Cookie 추가 <<--
            res.addCookie(cookie); // 비어있던 res 객체에 위 cookie객체에 정보들이 담긴것이 담겨지고 그것은 HTTP통신관련 라이브러리인 servlet의 인터페이스  HttpServletResponse의 addCookie()메소드를 통해 쿠키가 저장된다.
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}