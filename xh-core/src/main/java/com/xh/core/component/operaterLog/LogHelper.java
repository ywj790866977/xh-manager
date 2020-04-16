package com.xh.core.component.operaterLog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xh.core.config.support.DynamicDataSource;
import com.xh.core.user.UserContext;
import com.xh.core.utils.HttpIpUtil;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogHelper
 * @Description:
 * @Author: Idy
 * @Date: 2020/2/8 18:53
 **/
@Aspect
@Component
public class LogHelper {

    @Resource
    private UserContext userContext;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static Logger logger = LoggerFactory.getLogger(LogHelper.class);

    /**
     * 没有单独定义切点，直接在环绕方法里面处理[包com.oubao.activityManage下面的所有类下面的所有方法，同时包含LogEnable注解的将被监听]
     * @param point
     * @throws Throwable
     */
    @Around("execution(* com.kkcloud..*.*(..)) && @annotation(OperateLog)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();

        /************************ 获取操作日志信息 *************************/
        MethodSignature signature = (MethodSignature) point.getSignature();
        String params = JSONObject.toJSONString(
                Stream.of(point.getArgs()).collect(Collectors.toList()).get(0));                // 参数
        OperateLog operateLog = signature.getMethod().getAnnotation(OperateLog.class);
        String operater = operateLog.operater();                                                // 操作内容
        String module = operateLog.module();                                                    // 菜单名称
        String ip = HttpIpUtil.getIpAddress(RequestHolder.getHttpServletRequest());                             // IP
        String createdBy = "";
        if (null != userContext) {
            if (null != userContext.getUser()) {
                createdBy = userContext.getUser().getUserName();
            }
        }

        /************************ 组装操作日志信息 *************************/
        OperateLogInfo log = new OperateLogInfo();
        log.setCreatedAt(String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        log.setCreatedBy(createdBy);
        log.setIp(ip);
        log.setModule(module);
        log.setOperater(operater);
        log.setParams(params);
        log.setResults(JSONObject.toJSONString(result));
        log.setSite(String.valueOf(DynamicDataSource.getSite()==null?userContext.getSiteId():DynamicDataSource.getSite()));

        /************************ 发送Kafka信息保存日志 *************************/
        try {
            //kafkaService.sendByTopic("kkcould_core_operater_log", JSON.toJSONString(log));
            kafkaTemplate.send("kkcould_core_operater_log", JSON.toJSONString(log));
            logger.info("操作日志发送kafka成功：{}", JSON.toJSONString(log));
        } catch (Exception e) {
            logger.error("操作日志发送kafka异常：{}", e);
        }

        return result;
    }

    /**
     * 异常处理
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut="execution(* com.kkcloud..*.*(..)) && @annotation(OperateLog)", throwing="e")
    public void afterThrowable(JoinPoint joinPoint, Throwable e) {
        /************************ 获取操作日志信息 *************************/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String params = JSONObject.toJSONString(
                Stream.of(joinPoint.getArgs()).collect(Collectors.toList()).get(0));              // 参数
        OperateLog operateLog = signature.getMethod().getAnnotation(OperateLog.class);
        String operater = operateLog.operater();                                                // 操作内容
        String module = operateLog.module();                                                    // 菜单名称
        String ip = HttpIpUtil.getIpAddress(RequestHolder.getHttpServletRequest());                             // IP
        String createdBy = "";
        if (null != userContext) {
            if (null != userContext.getUser()) {
                createdBy = userContext.getUser().getUserName();
            }
        }

        /************************ 组装操作日志信息 *************************/
        OperateLogInfo log = new OperateLogInfo();
        log.setCreatedAt(String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        log.setCreatedBy(createdBy);
        log.setIp(ip);
        log.setModule(module);
        log.setOperater(operater);
        log.setParams(params);
        log.setResults("接口异常，异常信息:" + e.getMessage());
        log.setSite(String.valueOf(DynamicDataSource.getSite()==null?userContext.getSiteId():DynamicDataSource.getSite()));

        /************************ 发送Kafka信息保存日志 *************************/
        try {
            //kafkaService.sendByTopic("kkcould_core_operater_log", JSON.toJSONString(log));
            kafkaTemplate.send("kkcould_core_operater_log", JSON.toJSONString(log));
            logger.info("操作日志发送kafka成功：{}", JSON.toJSONString(log));
        } catch (Exception e1) {
            logger.error("操作日志发送kafka异常：{}", e1);
        }
    }
}
