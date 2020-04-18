package com.xh.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.admin.entity.Account;
import java.util.List;

/**
 * AccountService
 * @author rubyle
 */
public interface AccountService extends IService<Account> {

	/**
	 * 查询所有
	 * @return 集合
	 */
	List<Account> queryList();

	/**
	 * 根据id查询
	 * @param id id
	 * @return 实体
	 */
	Account get(Long id);

	/**
	 * 更新
	 * @param account 跟新实体
	 */
	void update(Account account);

	/**
	 * 根据id 删除
	 * @param id id
	 */
	void delete(Long id);
}
