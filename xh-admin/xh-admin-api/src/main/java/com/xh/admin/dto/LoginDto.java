package com.xh.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * LoginDto
 *
 * @author js-rubyle
 * @date 2020/4/19 14:49
 */
@Data
@AllArgsConstructor
@Builder
public class LoginDto {
	private String username;
	private String password;
}
