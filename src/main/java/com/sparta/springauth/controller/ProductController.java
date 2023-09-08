package com.sparta.springauth.controller;

import com.sparta.springauth.dto.ProductRequestDto;
import com.sparta.springauth.entity.User;
import com.sparta.springauth.entity.UserRoleEnum;
import com.sparta.springauth.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public String getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Authentcation 의 Principle
        User user = userDetails.getUser();
        System.out.println("user.getUsername() = " + user.getUsername());

        return "redirect:/";
    }

    // 관리자 접근 페이지 요청
    @Secured(UserRoleEnum.Authority.ADMIN) // 관리자 전용 접근가능 페이지 설정방법 Secured
    @GetMapping("/products/secured") // http://localhost:8080/api/products/secured -> 관리자 (ROLE이 ADMIN아니면 접속이 불가능하다.)
    public String getProductsByAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            System.out.println("authority.getAuthority() = " + authority.getAuthority());
        }

        return "redirect:/";
    }

    // 유효성 검사 테스트용
    // POSTMAN에서 해당 URL로 Cookie key:value 넣고 테스트해봐야 함. 오류로그에서 유효성검사 안된부분 확인 가능.
    @PostMapping("/validation")
    @ResponseBody
    public ProductRequestDto testValid(@RequestBody @Valid ProductRequestDto requestDto) { // <- @Valid를 검사하고싶은 Dto에 붙여줘야 한다.
        return requestDto;
    }
}