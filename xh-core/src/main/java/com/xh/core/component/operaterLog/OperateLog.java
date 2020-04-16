package com.xh.core.component.operaterLog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: operateLog
 * @Description: 监听接口调用
 * @Author: Idy
 * @Date: 2020/2/8 18:48
 **/
@Target(ElementType.METHOD)//表示标识方法
@Retention(RetentionPolicy.RUNTIME)//表示运行时保留
public @interface OperateLog {
    /**
     * @Description: 主要是标志日志的操作信息
     * @Author:      Idy
     * @Date:        2020/3/10
     * @param:       
     * @return:      
     */
    String operater() default "";

    /**
     * @Description: 主要是标志日志的菜单名称
     * @Author:      Idy
     * @Date:        2020/3/10
     * @param:
     * @return:
     */
    String module() default "";
}
