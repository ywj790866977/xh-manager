package com.xh.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.admin.entity.AclRole;
import com.xh.admin.mapper.AclRoleMapper;
import com.xh.admin.service.ACLROLEervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements ACLROLEervice {
	@Resource
	private AclRoleMapper aclRoleMapper;

	@Override
	public IPage<AclRole> queryListByPage(Integer page, Integer size) {
		Page<AclRole> eduTeacherPage = new Page<>(page,size);
		return aclRoleMapper.selectPage(eduTeacherPage, null);
	}
}
