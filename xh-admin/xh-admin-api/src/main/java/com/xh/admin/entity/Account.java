package com.xh.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 账目表
 * @author rubyle
 */
@Data
@TableName("account")
@ApiModel(value = "Account对象", description="账目表")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "序号")
	private Long billNo;
	@ApiModelProperty(value = "摘要")
	private String summary;
	@ApiModelProperty(value = "对方科目")
	private String subject;
	@ApiModelProperty(value = "金额")
	private BigDecimal money;
	@ApiModelProperty(value = "账目类型: 1 入, 2 出")
	private Integer accountType;
	@ApiModelProperty(value = "结余")
	private BigDecimal balance;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "申请人")
	private String applicantUser;
	@ApiModelProperty(value = "审核人")
	private String checkUser;
	@ApiModelProperty(value = "状态 ")
	private Integer billStatus;
	@ApiModelProperty(value = "删除: 0 否, 1 是")
	private Integer deleted;
	@ApiModelProperty(value = "创建时间")
	private Date createdBy;
	@ApiModelProperty(value = "更新时间")
	private Date updatedBy;

}
