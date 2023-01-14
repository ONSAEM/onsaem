package com.onsaem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
	            .anyRequest().permitAll()
	            );

		http.formLogin()	//<security:form-loging
//				.loginPage("/loginPage") // 사용자 정의 로그인 페이지
//				.defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
//				.failureUrl("content/loginFrom")  // 로그인 실패 후 이동 페이지
				.usernameParameter("memberId") // 아이디 파라미터명 설정
				.passwordParameter("password") // 패스워드 파라미터명 설정
				.loginProcessingUrl("/login") // 로그인 Form Action Url
				.successHandler(
						new AuthenticationSuccessHandler() {
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request,
									HttpServletResponse response, Authentication authentication)
									throws IOException, ServletException {
								
								System.out.println("authentication : " + authentication.getName());
		                        response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
							}
				})		// 로그인 성공 후 핸들러
			    .failureHandler(
			    		 new AuthenticationFailureHandler() {
							@Override
							public void onAuthenticationFailure(HttpServletRequest request,
									HttpServletResponse response, AuthenticationException exception)
									throws IOException, ServletException {
								
								 System.out.println("exception : " + exception.getMessage());
			                        response.sendRedirect("/login");
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

	
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
		
		/* 임시객체 생성*/
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		/* 비밀번호 암호화*/
		String password = passwordEncoder.encode("1234");
		
		manager.createUser(User.withUsername("user")
				.password(password)
				.roles("USER")
				.build());
		manager.createUser(User.withUsername("admin")
				.password(password)
				.roles("ADMIN")
				.build());
		
//		UserDetails user =
//			 User.builder()  //.withDefaultPasswordEncoder()
//				.username("user")
//				.password("{bcrypt}$2a$04$q2lVd7PUoOlUh.nRcUZZwuMJ1/6FhHbIL.QQ0r8Ngd3EKtS61N4sS")
//				.roles("USER")
//				.build();
//		
//		UserDetails admin =
//			User.builder()  //.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("{bcrypt}$2a$04$q2lVd7PUoOlUh.nRcUZZwuMJ1/6FhHbIL.QQ0r8Ngd3EKtS61N4sS")
//				.roles("ADMIN")
//				.build();

//		return new InMemoryUserDetailsManager(user, admin);
		return manager;
	}
}
