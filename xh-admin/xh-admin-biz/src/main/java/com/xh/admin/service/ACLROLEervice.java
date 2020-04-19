package com.xh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.admin.entity.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
public interface ACLROLEervice extends IService<AclRole> {
	IPage<AclRole> queryListByPage(Integer page, Integer size);
}
