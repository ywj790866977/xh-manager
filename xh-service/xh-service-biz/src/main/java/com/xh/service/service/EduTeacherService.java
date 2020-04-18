package com.xh.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.service.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author rubyle
 * @since 2020-04-18
 */
public interface EduTeacherService extends IService<EduTeacher> {

	IPage<EduTeacher> queryListByPage(Integer page, Integer size);
}
