package com.xh.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.admin.entity.AclRole;
import com.xh.admin.service.impl.AclRoleServiceImpl;
import com.xh.common.core.response.ResponseHelper;
import com.xh.common.core.response.ResponseVO;
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
 *  前端控制器
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/role")
@Api(tags = "权限")
public class AclRoleController {
	@Autowired
	private AclRoleServiceImpl aclRoleServiceImpl;

	@ApiOperation(value = "list",notes = "查询列表")
	@GetMapping("/v1/list")
	public List<AclRole> queryList() {
		return aclRoleServiceImpl.list();
	}

	@ApiOperation(value = "get",notes = "根据id查询")
	@GetMapping("/v1/get/{id}")
	public AclRole queryById(@PathVariable("id") Long id){
		return aclRoleServiceImpl.getById(id);
	}

	@ApiOperation(value = "update",notes = "更新")
	@GetMapping("/v1/update")
	public ResponseVO<Void> queryById(@RequestBody AclRole aclRole){
		aclRoleServiceImpl.updateById(aclRole);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "delete",notes = "根据id删除")
	@GetMapping("/v1/delete/{id}")
	public ResponseVO<Void> deleteById(@PathVariable("id") Long id){
		aclRoleServiceImpl.removeById(id);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "add",notes = "添加")
	@GetMapping("/v1/add")
	public ResponseVO<Void> add(@RequestBody AclRole aclRole){
		aclRoleServiceImpl.save(aclRole);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "pageList",notes = "分页查询")
	@GetMapping("/v1/list/{page}/{size}")
	public IPage<AclRole> queryListByPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		return aclRoleServiceImpl.queryListByPage(page,size);
	}
}

