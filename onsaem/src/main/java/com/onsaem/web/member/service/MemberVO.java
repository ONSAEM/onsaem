package com.onsaem.web.member.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO implements UserDetails{
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private String postalCode;
	private String addr;
	private String detailAddr;
	private String bank;
	private String bankAccount;
	private String businessNumber;
	private String role;
	private int point;
	private String status;
	private String memberId;
	

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
         authorities.add(new SimpleGrantedAuthority(this.getRole()));
         return authorities;
    }
	@Override
	public String getUsername() {

		return memberId;
	}
	@Override
	public String getPassword() {
		
	   return password;
	}
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}
	@Override
	public boolean isEnabled() {

		return true;
	}
	
}
