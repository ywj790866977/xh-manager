package com.xh.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * LoginVo
 *
 * @author js-rubyle
 * @date 2020/4/19 14:47
 */
@Data
@AllArgsConstructor
@Builder
public class LoginVo {
 	private String currentAuthority;
 	private String status;
 	private String type;
}
