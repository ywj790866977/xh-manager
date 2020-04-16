package com.xh.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * SheepUserDetailsService
 *
 * @author js-rubyle
 * @create: 2020/4/11 12:49
 */
@Service
public class UserServiceImpl implements UserDetailsService {
	@Resource
	private PasswordEncoder passwordEncoder;

	/**
	 * 这里创建了一个用户名为codesheep，密码 123456的模拟用户，并且赋予了 普通权限（ROLE_NORMAL）和 中等权限（ROLE_MEDIUM）
	 * @param s
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		// 查询数据库操作
		if (!"admin".equals(s)) {
			throw new UsernameNotFoundException("用户" + s + "不存在");
		}
		String passwd = passwordEncoder.encode("123456");
		String role = "ROLE_ADMIN";
		List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
		authorities.add(new SimpleGrantedAuthority(role));
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM");
		return new User(s, passwd,authorities);
	}
}
