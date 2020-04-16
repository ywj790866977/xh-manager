package com.xh.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SsoApplication
 *
 * @author js-rubyle
 * @create: 2020/4/11 12:55
 */
@SpringBootApplication
public class SsoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class,args);
	}
}
