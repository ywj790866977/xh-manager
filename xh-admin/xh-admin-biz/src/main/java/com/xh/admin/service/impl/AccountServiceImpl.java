package com.xh.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

	@Resource
	private AccountMapper accountMapper;

	@Override
	public List<Account> queryList() {
		return accountMapper.selectList(null);
	}

	@Override
	public Account get(Long id) {
		return accountMapper.selectById(id);
	}

	@Override
	public void update(Account account) {
		accountMapper.update(account,null);
	}

	@Override
	public void delete(Long id) {
		accountMapper.deleteById(id);
	}
}
