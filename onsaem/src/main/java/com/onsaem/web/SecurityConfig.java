package com.onsaem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	AuthenticationFailureHandler customFailureHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.headers().frameOptions().sameOrigin(); // security 설정 추가

		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());

		http.formLogin() // <security:form-loging
				.loginPage("/loginPage") // 사용자 정의 로그인 페이지
				.usernameParameter("memberId") // 아이디 파라미터명 설정
				.passwordParameter("password") // 패스워드 파라미터명 설정
				.loginProcessingUrl("/login") // 로그인 Form Action Url
				.defaultSuccessUrl("/").failureHandler(customFailureHandler) // 로그인 실패 후 핸들러
				.permitAll();

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID", "remember");

		http.csrf().disable();

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain resources(HttpSecurity http) throws Exception {
		// example
		http.requestMatchers(matchers -> matchers.antMatchers("/resource/**"))
				.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).requestCache().disable()
				.securityContext().disable().sessionManagement().disable();
		return http.build();
	}

}
