//package com.xh.core.config;
//
//import com.alibaba.fastjson.JSON;
//import com.xh.core.config.support.DynamicDataSource;
//import com.xh.core.user.SessionUser;
//import com.xh.core.user.UserContext;
//import com.xh.core.utils.HttpHeaderUtil;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.util.AntPathMatcher;
//
//public class PermissionFilter implements Filter {
//
//	private static final Logger logger = LoggerFactory.getLogger(PermissionFilter.class);
//
//	private String[] exclusions = {};
//
//	private String[] allExclusions = {};
//
//	private UserContext userContext;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		if(userContext.siteIsClosed()){
//			interceptReturn(CommonStatusCode.SITE_CLOSE.getCode(), CommonStatusCode.SITE_CLOSE.getMsg(), (HttpServletResponse) response);
//			return;
//		}
//		HttpServletRequest req = (HttpServletRequest) request;
//		logger.info("当前请求uri={},TB-UUID={},ip地址={},请求用户={}",((HttpServletRequest) request).getRequestURI(),
//				req.getHeader("TB-UUID"), HttpHeaderUtil.getHeader(req).get("x-forwarded-for"),
//				userContext.getUser()==null?"用户不存在": JSON.toJSONString(userContext.getUser()));
//		//检查站点是否停用
//		// 处理只跟应用有关跟数据源无关的过滤 后期处理
//		if (isAllExclusion(req)) {
//			DynamicDataSource.setSiteDB(0);// 将跟数据源无关的都切到数据库0
//			chain.doFilter(request, response);
//			return;
//		}
//
//		//清理线程变量
//		KkCloudFeignConfig.dataToken.remove();
//		DynamicDataSource.removeSite();
//		if (isExclusion(req)) {
//			Integer siteId = userContext.getSiteId(true);
//			if (null == siteId || 0== siteId) {
//				String hSiteId = req.getHeader(UserContext.REQ_HEADER_SITE_ID_NAME);
//				try {
//					siteId = Integer.parseInt(hSiteId);
//				} catch (Exception ex) {
//					logger.error(ex.getMessage());
//					siteId = 0;
//					//logger.error(" set siteId is defualt!");
//				}
//			}
//			DynamicDataSource.setSiteDB(siteId==null?0:siteId);
//
//			String path = req.getRequestURI();
//			if (path.indexOf("/internal/") >= 0) {
//				path = path.replace("/internal/", "/");
//				request.getRequestDispatcher(path).forward(request, response);
//				return;
//			}
//			chain.doFilter(request, response);
//			return;
//		} else {
//			Integer siteId = this.userContext.getSiteId();
//			siteId = siteId==null?0:siteId;
//			DynamicDataSource.setSiteDB(siteId);
//		}
//
//		//logger.info("当前请求URI={}", ((HttpServletRequest) request).getRequestURI());
//		SessionUser user = userContext.getUser();
//		if (null == user) {
//			logger.info(" - 当前用户为空 .");
//			interceptReturn(CommonStatusCode.UNAUTHORIZED.getCode(), CommonStatusCode.UNAUTHORIZED.getMsg(),
//					(HttpServletResponse) response);
//
//			return;
//		}
//		logger.info("当前从缓存中取出的用户信息sessionUser={}",JSON.toJSONString(user));
//		// 检查当前用户权限是否已发生变更，状态是否被禁用，是否被删除，角色是否已经发生变更
//		int result = userContext.userInfoChangeVali();
//		if (result != 0) {
//			// 登出
//			switch (result) {
//			case CommonStatusCode.USER_DELETE_CODE:
//				interceptReturn(CommonStatusCode.USER_DELETE.getCode(), CommonStatusCode.USER_DELETE.getMsg(), (HttpServletResponse) response);
//				return;
//			case CommonStatusCode.AUTHORITY_UPDATE_CODE:
//				interceptReturn(CommonStatusCode.AUTHORITY_UPDATE.getCode(), CommonStatusCode.AUTHORITY_UPDATE.getMsg(), (HttpServletResponse) response);
//				return;
//			case CommonStatusCode.USER_FORBID_CODE:
//				interceptReturn(CommonStatusCode.USER_FORBID.getCode(), CommonStatusCode.USER_FORBID.getMsg(), (HttpServletResponse) response);
//				return;
//			case CommonStatusCode.USER_INFO_UPDATE_CODE:
//				interceptReturn(CommonStatusCode.USER_INFO_UPDATE.getCode(), CommonStatusCode.USER_INFO_UPDATE.getMsg(), (HttpServletResponse) response);
//				return;
//			case CommonStatusCode.NEW_CLIENT_LOGIN_CODE:
//				interceptReturn(CommonStatusCode.NEW_CLIENT_LOGIN.getCode(), CommonStatusCode.NEW_CLIENT_LOGIN.getMsg(), (HttpServletResponse) response);
//				return;
//			default:
//				break;
//			}
//		}
//		//检查ip是否被禁用
//		boolean forbidIp=userContext.checkIpForbid();
//		if(forbidIp){
//			interceptReturn(CommonStatusCode.FROBID_IP.getCode(), CommonStatusCode.FROBID_IP.getMsg(), (HttpServletResponse) response);
//			return;
//		}
//		logger.info("用户调用基本信息->[ id:{}, name:{} ]", user.getId(), user.getUserName());
//		String uri = req.getRequestURI();
//		if (!userContext.hasPermission(uri) && !user.isAdmin()) {
//			logger.warn("当前用户无权限且不是超级管理员，鉴权失败");
//			interceptReturn(CommonStatusCode.NO_PERMISSION.getCode(), CommonStatusCode.NO_PERMISSION.getMsg(), (HttpServletResponse) response);
//			return;
//		}
//		userContext.refreshUser();
//		chain.doFilter(request, response);
//	}
//
//	public PermissionFilter(String[] exclusions, UserContext userContext) {
//		this.exclusions = exclusions;
//		this.userContext = userContext;
//	}
//
//	private boolean isExclusion(HttpServletRequest request) {
//		String url = request.getRequestURI();
//		AntPathMatcher matcher = new AntPathMatcher();
//		for (int i = 0; i < this.exclusions.length; ++i) {
//			if (matcher.match(this.exclusions[i], url)) {
//				//logger.info(" The request URI exclusion ->>" + url);
//				return true;
//			}
//		}
//		//logger.info(" The request URI inclusion ->>" + url);
//		return false;
//	}
//
//	private boolean isAllExclusion(HttpServletRequest request) {
//		String url = request.getRequestURI();
//		AntPathMatcher matcher = new AntPathMatcher();
//		for (int i = 0; i < this.allExclusions.length; ++i) {
//			if (matcher.match(this.allExclusions[i], url)) {
//				// logger.info(" The request URI exclusion ->> {}" + url);
//				return true;
//			}
//		}
//		//logger.info(" The request URI inclusion ->> {}" + url);
//		return false;
//	}
//
//	private void interceptReturn(int status, String message, HttpServletResponse resp) throws IOException {
//		resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//		ResponseVO<String> resp_vo = new ResponseVO<String>(status, message);
//		PrintWriter resp_write = resp.getWriter();
//		resp_write.println(resp_vo.toString());
//		resp_write.close();
//	}
//}