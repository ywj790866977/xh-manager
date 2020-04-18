package com.xh.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.service.entity.EduTeacher;
import com.xh.service.mapper.EduTeacherMapper;
import com.xh.service.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-18
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements
		EduTeacherService {
	@Resource
	private EduTeacherMapper eduTeacherMapper;

	@Override
	public IPage<EduTeacher> queryListByPage(Integer page, Integer size) {
		Page<EduTeacher> eduTeacherPage = new Page<>(page,size);
		return eduTeacherMapper.selectPage(eduTeacherPage, null);
	}
}
