package com.boot.json.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll() // 로그인 페이지와 정적 파일은 누구나 접근 가능
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 페이지는 관리자만 접근 가능
                    .requestMatchers("/store/**").hasRole("STORE") // 매장 페이지는 매장 직원만 접근 가능
                    .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만 접근 가능
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login") // 로그인 페이지 URL
                    .loginProcessingUrl("/login") // 로그인 처리 URL
                    .successHandler(authenticationSuccessHandler()) // 로그인 성공 후 리다이렉트 처리 핸들러
                    .failureUrl("/login?error") // 로그인 실패 시 에러 페이지로
                    .permitAll()
            )
            .logout(logout -> logout.permitAll()); // 로그아웃은 누구나 가능

        return http.build();
    }

    // 로그인 성공 후 리다이렉트 URL 구분을 위한 AuthenticationSuccessHandler 설정
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                String role = request.getParameter("role");  // role 파라미터 값 가져오기

                if ("ADMIN".equals(role)) {
                    // 관리자일 경우 /admin으로 리다이렉트
                    response.sendRedirect("/admin");
                } else if ("STORE".equals(role)) {
                    // 매장 직원일 경우 /store로 리다이렉트
                    response.sendRedirect("/store");
                } else {
                    // 기본적으로 인증된 사용자에게 /default 페이지로 리다이렉트 (예시)
                    response.sendRedirect("/default");
                }
            }
        };
    }
}