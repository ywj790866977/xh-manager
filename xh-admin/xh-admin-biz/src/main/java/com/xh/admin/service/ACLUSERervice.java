package com.xh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.admin.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
public interface ACLUSERervice extends IService<AclUser> {
	IPage<AclUser> queryListByPage(Integer page, Integer size);
}
