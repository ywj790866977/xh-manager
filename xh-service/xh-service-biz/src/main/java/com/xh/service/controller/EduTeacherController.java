package com.xh.service.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.common.core.response.ResponseHelper;
import com.xh.common.core.response.ResponseVO;
import com.xh.service.entity.EduTeacher;
import com.xh.service.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author rubyle
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/teacher")
@Api(tags = "教师")
public class EduTeacherController {
	@Autowired
	private EduTeacherService eduTeacherService;

	@ApiOperation(value = "list",notes = "查询列表")
	@GetMapping("/v1/list")
	public List<EduTeacher> queryList() {
		return eduTeacherService.list();
	}

	@ApiOperation(value = "get",notes = "根据id查询")
	@GetMapping("/v1/get/{id}")
	public EduTeacher queryById(@PathVariable("id") Long id){
		return eduTeacherService.getById(id);
	}

	@ApiOperation(value = "update",notes = "更新")
	@GetMapping("/v1/update")
	public ResponseVO<Void> queryById(@RequestBody EduTeacher eduTeacher){
		eduTeacherService.updateById(eduTeacher);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "delete",notes = "根据id删除")
	@GetMapping("/v1/delete/{id}")
	public ResponseVO<Void> deleteById(@PathVariable("id") Long id){
		eduTeacherService.removeById(id);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "pageList",notes = "分页查询")
	@GetMapping("/v1/list/{page}/{size}")
	public IPage<EduTeacher> queryListByPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		 return eduTeacherService.queryListByPage(page,size);
	}

	@ApiOperation(value = "add",notes = "添加")
	@GetMapping("/v1/add")
	public ResponseVO<Void> add(@RequestBody EduTeacher eduTeacher){
		 eduTeacherService.save(eduTeacher);
		 return ResponseHelper.success();
	}


}

