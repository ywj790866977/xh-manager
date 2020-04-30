package com.xh.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xh.admin.dto.LoginDto;
import com.xh.admin.entity.AclUser;
import com.xh.admin.service.impl.AclUserServiceImpl;
import com.xh.admin.vo.LoginVo;
import com.xh.common.core.response.ResponseHelper;
import com.xh.common.core.response.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author rubyle
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class AclUserController {
	@Autowired
	private AclUserServiceImpl aclUserServiceImpl;

	@ApiOperation(value = "list",notes = "查询列表")
	@GetMapping("/v1/list")
	public List<AclUser> queryList() {
		return aclUserServiceImpl.list();
	}

	@ApiOperation(value = "get",notes = "根据id查询")
	@GetMapping("/v1/get/{id}")
	public AclUser queryById(@PathVariable("id") Long id){
		return aclUserServiceImpl.getById(id);
	}

	@ApiOperation(value = "update",notes = "更新")
	@GetMapping("/v1/update")
	public ResponseVO<Void> queryById(@RequestBody AclUser aclUser){
		aclUserServiceImpl.updateById(aclUser);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "delete",notes = "根据id删除")
	@GetMapping("/v1/delete/{id}")
	public ResponseVO<Void> deleteById(@PathVariable("id") Long id){
		aclUserServiceImpl.removeById(id);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "add",notes = "添加")
	@GetMapping("/v1/add")
	public ResponseVO<Void> add(@RequestBody AclUser aclUser){
		aclUserServiceImpl.save(aclUser);
		return ResponseHelper.success();
	}

	@ApiOperation(value = "pageList",notes = "分页查询")
	@GetMapping("/v1/list/{page}/{size}")
	public IPage<AclUser> queryListByPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		return aclUserServiceImpl.queryListByPage(page,size);
	}

	@PostMapping("/v1/login")
	public LoginVo login(@RequestBody LoginDto loginDto){
		return LoginVo.builder()
				.currentAuthority("admin")
				.status("ok")
				.type("account").build();
	}
}

