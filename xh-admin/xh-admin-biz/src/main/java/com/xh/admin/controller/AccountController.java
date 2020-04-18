package com.xh.admin.controller;

import com.xh.admin.entity.Account;
import com.xh.admin.service.impl.AccountServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * account controller
 *
 * @author rubyle
 */
@RestController
@RequestMapping("/account")
@Api(tags = "账目")
public class AccountController {

	@Resource
	private AccountServiceImpl accountServiceImpl;


	@ApiOperation(value = "list",notes = "查询列表")
	@GetMapping("/v1/list")
	public List<Account> queryList() {
		return accountServiceImpl.queryList();
	}

	@ApiOperation(value = "get",notes = "根据id查询")
	@GetMapping("/v1/get/{id}")
	public Account queryById(@PathVariable("id") Long id){
		return accountServiceImpl.get(id);
	}

	@ApiOperation(value = "update",notes = "更新")
	@GetMapping("/v1/update")
	public void queryById(@RequestBody Account account){
		 accountServiceImpl.update(account);
	}

	@ApiOperation(value = "delete",notes = "根据id删除")
	@GetMapping("/v1/delete/{id}")
	public void deleteById(@PathVariable("id") Long id){
		accountServiceImpl.delete(id);
	}
}
