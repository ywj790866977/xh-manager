package com.xh.common.core.response;

/**
 * 框架定义状态码
 * 
 * @author xxx
 */
public class CommonStatusCode {
	public static final ServiceStatusCode SUCCESS = new ServiceStatusCode(0, "操作成功");
	public static final ServiceStatusCode FAILURE = new ServiceStatusCode(10001, "操作失败，请重试！");
	public static final ServiceStatusCode UNAUTHORIZED = new ServiceStatusCode(10401, "登录信息已失效");
	public static final ServiceStatusCode NO_PERMISSION = new ServiceStatusCode(10403, "无权限访问此功能");
	public static final ServiceStatusCode CONFLICT = new ServiceStatusCode(10409, "校验数据失败");
	public static final ServiceStatusCode IDEMPOTENT = new ServiceStatusCode(10509, "重复执行操作");
	public static final ServiceStatusCode USER_FORBID = new ServiceStatusCode(1000101, "当前用户被禁用");
	public static final ServiceStatusCode USER_DELETE = new ServiceStatusCode(1000102, "当前用户被删除");
	public static final ServiceStatusCode USER_INFO_UPDATE = new ServiceStatusCode(1000103, "当前用户信息已发生变更");
	public static final ServiceStatusCode AUTHORITY_UPDATE = new ServiceStatusCode(1000104, "当前用户资源权限已经发生修改");
	public static final ServiceStatusCode NEW_CLIENT_LOGIN = new ServiceStatusCode(1000105, "当前账号已在其它地方登录");
	public static final ServiceStatusCode FROBID_IP = new ServiceStatusCode(1000106, "您的IP未经授权不可访问，如有疑问请联系管理员");
	public static final ServiceStatusCode SITE_CLOSE = new ServiceStatusCode(1000107, "当前站点已经停用，请联系客服");
	public static final ServiceStatusCode UKEY_VALIDATE_CODE_NOT_NULL = new ServiceStatusCode(1000108, "U盾验证码不能为空");
	public static final ServiceStatusCode UKEY_VALIDATE_CODE_NOT_EQUALS= new ServiceStatusCode(1000109, "U盾验证码错误");
	public static final ServiceStatusCode UKEY_VALIDATE_CODE_LAPSE= new ServiceStatusCode(1000110, "U盾验证码已失效请重新输入");
	public static final ServiceStatusCode UKEY_NOT_NULL= new ServiceStatusCode(1000111, "该操作必须验证U盾，请先绑定U盾");
}