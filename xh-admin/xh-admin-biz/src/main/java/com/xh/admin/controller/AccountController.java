package com.xh.admin.controller;

import com.xh.admin.entity.Account;
import com.xh.admin.service.impl.AccountServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * account controller
 *
 * @author rubyle
 */
@RestController
@RequestMapping("/account")
@Api(tags = "测试")
public class AccountController {

	@Resource
	private AccountServiceImpl accountServiceImpl;

	/**
	 * 查询所有
	 * @return 结果集合
	 */
	@ApiOperation(value = "queryList",notes = "查询列表")
	@GetMapping("/v1/queryList")
	public List<Account> queryList() {
		return accountServiceImpl.queryList();
	}


}
