package com.xh.admin.service.impl;

import com.xh.admin.entity.Account;
import com.xh.admin.mapper.AccountMapper;
import com.xh.admin.service.AccountService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * AccountServiceImpl
 * @author Lenovo
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	private AccountMapper accountMapper;

	@Override
	public List<Account> queryList() {
		return accountMapper.selectList(null);

	}
}
