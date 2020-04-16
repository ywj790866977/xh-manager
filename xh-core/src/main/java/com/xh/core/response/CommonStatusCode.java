package com.xh.core.response;

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

	public static final String SITE_TYPE_ZK = "ZK"; 	//站点类型 - 总控
	public static final String SITE_TYPE_ZD = "ZD";		//站点类型 - 站点
	public static final String SITE_TYPE_DL = "DL";     //站点类型 - 代理
	public static final String SITE_TYPE_HY = "HY";     //站点类型 - 会员
	
	//获取站点ID前缀   kc:site:ZK:ZK_alsfkjas08asdf0as8dfasd0f943h4k2jh42k3
    public static final String REDIS_KEY_SITE_TYPE = "kc:site:";

    public static final String REDIS_KEY_GLOBAL = "kc:global:";

	public static final String USER_LOGIN_TIME_OUT_KEY = "kkcloud:sso:login:timeout:admin:";

	/**
	 * 用户登入token前缀
	 */
	public static final String USER_LOGIN_TOKEN_KEY_PREFIX = "kkcloud:sso:login:token:admin:";

	/**
	 * 用户强制登出前缀
	 */
	public static final String USER_FORCE_LOGOUT_KEY_PREFIX = "kkcloud:sso:force:logout:admin:";

	/**
	 * 存取用户token的key前缀
	 */
	public static final String USER_LOGIN_TOKEN_VALUE_KEY_PREFIX="kkcloud:sso:login:token:value:";
	/**
	 * 资源列表
	 */
	public static final String USER_LOGIN_RESOURCE_URL="kkcloud:sso:login:resource:url:";
	/**
	 * 脱敏字段key前缀
	 */
	public static final String DESENSITIZATION_FIELD_PREFIX="kkcloud:sso:login:desensitization:";
	/**
	 * 自己及下级角色列表
	 */
	public static final String CHILD_ROLELIST_PREFIX="kkcloud:sso:login:childrolelist:";
	/**
	 * 自己及其下级角色用户列表
	 */
	public static final String CHILD_USERLIST_PREFIX="kkcloud:sso:login:childuserlist:";
	/**
	 * 可编辑字段列表
	 */
	public static final String EDIT_FIELD_LIST="kkcloud:sso:login:editfield:";
	/**
	 * 禁用ip前缀
	 */
	public static  final String FORBID_IP_PREFIX="kkcloud:sso:login:forbid:ip:";

	/**
	 * 总控关闭站点rediskey
	 */
	public static  final String SITE_CLOSE_OF_CONTROL_PREFX="KK:SITE_CLOSE";
	/**
	 * 业务功能u盾验证随机验证码存rediskey前缀
	 */
	public static  final String UKEY_RANDOM_VALIDATE_CODE="kkcloud:sso:ukey:validate:code:";

	/**
	 * U盾校验配置数据前缀
	 */
	public static  final String UKEY_CONFIG_PREF="map-common-config-key:";
	
	
	/**
	 * 用户被禁用
	 */
	public static final int USER_FORBID_CODE = 1000101;
	/**
	 * 用户被删除
	 */
	public static final int USER_DELETE_CODE = 1000102;
	/**
	 * 用户角色发生修改
	 */
	public static final int USER_INFO_UPDATE_CODE = 1000103;

	/**
	 * 用户资源权限变更
	 */
	public static final int AUTHORITY_UPDATE_CODE = 1000104;

	/**
	 * 新客户端登录
	 */
	public static final int NEW_CLIENT_LOGIN_CODE = 1000105;



}