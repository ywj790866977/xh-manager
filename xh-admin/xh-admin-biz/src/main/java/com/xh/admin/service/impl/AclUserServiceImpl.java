package com.xh.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.admin.entity.AclUser;
import com.xh.admin.mapper.AclUserMapper;
import com.xh.admin.service.ACLUSERervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements ACLUSERervice {
	@Resource
	private AclUserMapper aclUserMapper;

	@Override
	public IPage<AclUser> queryListByPage(Integer page, Integer size) {
		Page<AclUser> eduTeacherPage = new Page<>(page,size);
		return aclUserMapper.selectPage(eduTeacherPage, null);
	}
}
