//package com.xh.core.user;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Thad
// */
//public class SessionUser implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * 用户id
//	 */
//	private long id;
//
//	/**
//	 * 用户登录账号
//	 */
//	private String userName;
//
//	/**
//	 * 用户头像
//	 */
//	private String avatar;
//
//	/**
//	 * 用户授权码
//	 */
//	private String authCode;
//
//	/**
//	 * 是否管理员
//	 */
//	private boolean isAdmin;
//
//	/**
//	 * 登录IP
//	 */
//	private String loginIp;
//
//	private String snno;
//
//
//	/**
//	 * 用户角色列表
//	 */
//	private List<SessionRole> roleList = new ArrayList<>();
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getAvatar() {
//		return avatar;
//	}
//
//	public void setAvatar(String avatar) {
//		this.avatar = avatar;
//	}
//
//	public String getAuthCode() {
//		return authCode;
//	}
//
//	public void setAuthCode(String authCode) {
//		this.authCode = authCode;
//	}
//
//	public boolean isAdmin() {
//		return isAdmin;
//	}
//
//	public void setIsAdmin(boolean isAdmin) {
//		this.isAdmin = isAdmin;
//	}
//
//	public String getLoginIp() {
//		return loginIp;
//	}
//
//	public void setLoginIp(String loginIp) {
//		this.loginIp = loginIp;
//	}
//
//	public List<SessionRole> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<SessionRole> roleList) {
//		this.roleList = roleList;
//	}
//
//	public String getSnno() {
//		return snno;
//	}
//
//	public void setSnno(String snno) {
//		this.snno = snno;
//	}
//}
