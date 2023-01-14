package com.onsaem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
	            .anyRequest().permitAll()
	            );

		http.formLogin()	//<security:form-loging
				.loginPage("/loginPage") // 사용자 정의 로그인 페이지
				.usernameParameter("memberId") // 아이디 파라미터명 설정
				.passwordParameter("password") // 패스워드 파라미터명 설정
				.loginProcessingUrl("/login") // 로그인 Form Action Url
				.defaultSuccessUrl("/")
			    .failureHandler(
			    		 new AuthenticationFailureHandler() {
							@Override
							public void onAuthenticationFailure(HttpServletRequest request,
									HttpServletResponse response, AuthenticationException exception)
									throws IOException, ServletException {
								
								 System.out.println("exception : " + exception.getMessage());
			                        response.sendRedirect("/loginPage?exception"+exception.getMessage());
							}
			    })		// 로그인 실패 후 핸들러
			    .permitAll();
		
				
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			  .deleteCookies("JSESSIONID", "remember");
		
		http
        .csrf().disable();

		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        //example
        http.requestMatchers(matchers -> matchers.antMatchers("/resource/**"))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();
        return http.build();
    }

}
