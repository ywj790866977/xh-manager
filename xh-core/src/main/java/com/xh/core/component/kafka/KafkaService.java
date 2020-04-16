//package com.xh.core.component.kafka;
//
//import com.xh.core.component.ApplicationKeyGenerate;
//import javax.annotation.Resource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
///**
// * @author js-reke
// * @project kkcloud-core
// * @description kafka 消息发送封装类
// * @create 2019-11-18 16:11
// */
//@Component
//@ConditionalOnBean(KafkaTemplateConfig.class)
//public class KafkaService {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
//
//    @Resource
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Resource
//    private ApplicationKeyGenerate appKeyGenerate;
//
//    /**
//     * 统一索引封装
//     * @param topic 自定有索引
//     * @return 封装后索引
//     */
//    private String setTopic(String topic) {
//        return appKeyGenerate.getTopic(topic);
//    }
//
//    /**
//     * 统一索引封装(无站点)
//     * @param topic 自定有索引
//     * @return 封装后索引
//     */
//    private String setAppTopic(String topic) {
//        return appKeyGenerate.getAppTopic(topic);
//    }
//
//    private String setToFinanceTopic(String topic) {
//	    return appKeyGenerate.getFinanceTopic(topic);
//	}
//
//    /**
//     * 单条日志数据存储
//     * @param topicName 通道topic名称
//     * @param logType   日志保存类型
//     * @param entity    存储数据封装类
//     */
//    public void sendLog(String topicName, String logType, Object entity) {
//        try {
//            String topic = setTopic(topicName);
//            ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, logType, entity);
//            sendExecute(send, topic);
//        } catch (Exception e) {
//            logger.error("向kafka推送日志数据失败，发生异常：[{}]", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 普通数据推送
//     * @param topicName 通道topic名称
//     * @param entity    存储数据封装类
//     */
//    public void send(String topicName, Object entity) {
//        try {
//            String topic = setTopic(topicName);
//            ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, entity);
//            sendExecute(send, null);
//        } catch (Exception e) {
//            logger.error("向kafka推送数据失败，发生异常：[{}]", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 普通数据推送
//     * @param topicName 通道topic名称
//     * @param entity    存储数据封装类
//     */
//    public void sendApp(String topicName, Object entity) {
//        try {
//            String topic = setAppTopic(topicName);
//            ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, entity);
//            sendExecute(send, null);
//        } catch (Exception e) {
//            logger.error("向kafka推送数据失败，发生异常：[{}]", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void sendToFinance(String topicName, Object entity) {
//
////    	logger.debug("********sendToFinance********");
////		logger.debug("*topicName:{}",topicName);
////		logger.debug("*entity:{}",entity);
////		logger.debug("****************");
//
//        try {
//            String topic = setToFinanceTopic(topicName);
//            ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, entity);
//            sendExecute(send, null);
//        } catch (Exception e) {
//            logger.error("向kafka推送数据失败，发生异常：[{}]", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void sendExecute(ListenableFuture<SendResult<String, Object>> sendFuture, String topic) {
//        sendFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                logger.info("向kafka推送[{}]数据,失败！----> 原因：[{}]", topic, throwable.getMessage());
//            }
//            @Override
//            public void onSuccess(SendResult<String, Object> sendResult) {
//                logger.info("向kafka推送[{}]数据,成功！返回信息：[{}]", topic, sendResult.toString());
//            }
//        });
//    }
//}
