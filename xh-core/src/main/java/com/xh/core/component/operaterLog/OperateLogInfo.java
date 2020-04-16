package com.xh.core.component.operaterLog;


/**
 * @ClassName: OperateLogInfo
 * @Description:
 * @Author: Idy
 * @Date: 2020/2/8 20:02
 **/
public class OperateLogInfo {

    /**
     * 登录时间
     */
    private String createdAt;

    /**
     * 登录账号
     */
    private String createdBy;

    /**
     * 操作内容
     */
    private String operater;

    /**
     * 菜单名称
     */
    private String module;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 响应参数
     */
    private String results;

    /**
     * IP
     */
    private String ip;

    /**
     * 站点
     */
    private String site;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
