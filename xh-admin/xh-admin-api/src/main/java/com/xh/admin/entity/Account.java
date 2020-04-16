package com.xh.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账目表
 * @author rubyle
 */
@Data
@TableName("account")
@ApiModel(value = "Account对象", description="账目表")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	private Long id;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private LocalDateTime createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updatedBy;

}
