//package com.xh.core.component.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//
///**
// * @description: kafka配置
// * @author: js-reke
// * @create: 2019-11-12 13:43
// */
//@Configuration
//@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
//public class KafkaTemplateConfig {
//
//    /******************************   生产者配置   **********************************/
//    @Value("${spring.kafka.client-id:}")
//    private String clientId;
//    /**
//     * 节点ip端口地址
//     */
//    @Value("${spring.kafka.bootstrap-servers:}")
//    private String brokerList;
//    /**
//     * 重试次数
//     */
//    @Value("${spring.kafka.producer.retries:0}")
//    private int retries;
//    /**
//     * 缓存容量
//     */
//    @Value("${spring.kafka.producer.buffer-memory:33554432}")
//    private int bufferMemory;
//    /**
//     * 批处理大小
//     */
//    @Value("${spring.kafka.producer.batch-size:307200}")
//    private int batchSize;
//    /**
//     * 指定消息key 编码方式
//     */
//    @Value("${spring.kafka.producer.key-serializer:org.apache.kafka.common.serialization.StringSerializer}")
//    private String keySerializer;
//    /**
//     * 指定消息value 编码方式
//     */
//    @Value("${spring.kafka.producer.value-serializer:org.apache.kafka.common.serialization.StringSerializer}")
//    private String valueSerializer;
//
//    @Value("@{spring.kafka.producer.acks:all}")
//    private String acks;
//
//    /******************************   身产者配置   **********************************/
//
//    /******************************   消费者配置   **********************************/
//    /**
//     * 消费组ID
//     */
//    @Value("${spring.kafka.consumer.group-id}")
//    private String groupId;
//    /**
//     * 是否自动提交偏移量
//     */
//    @Value("${spring.kafka.enable-auto-commit:true}")
//    private boolean ifAutoCommit;
//    /**
//     * 自动提交偏移量方式
//     */
//    @Value("${spring.kafka.auto-offset-reset:earliest}")
//    private String autoOffSetReset;
//    /**
//     * 消费者key 编码方式
//     */
//    @Value("${spring.kafka.consumer.key-deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
//    private String keyDeserializer;
//    /**
//     * 消费者value 编码方式
//     */
//    @Value("${spring.kafka.consumer.value-deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
//    private String valueDeserializer;
//    /**
//     * 最大接收条数
//     */
//    @Value("${spring.kafka.consumer.max-poll-records:10}")
//    private int maxPollRecords;
//    /**
//     * 自动提交时间间隔
//     */
//    @Value("${spring.kafka.consumer.auto-commit-interval:100}")
//    private int autoCommitInterval;
//    /**
//     * 监听器连接超时时间
//     */
//    @Value("${spring.kafka.listener.poll-timeout:10000}")
//    private int pollTimeOut;
//    /******************************   身产者配置   **********************************/
//    /**
//     * 监听器连接线程数量
//     */
//    @Value("${spring.kafka.listener.concurrency:5}")
//    private int concurrency;
//
//    /**
//     * kafkaTemplate 覆盖默认配置类中的kafkaTemplate
//     * @return kafka 生产者封装器
//     */
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    /**
//     * 生产者创建工厂
//     * @return 默认生产者工厂
//     */
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    /**
//     * 初始配置信息类
//     * @return 整体配置map
//     */
//    private Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>(10);
//        // 集群的服务器地址
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
//        // 消息缓存
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
//        // 生产者空间不足时，send()被阻塞的时间，默认60s
//        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);
//        // 生产者重试次数
//        props.put(ProducerConfig.RETRIES_CONFIG, retries);
//        // 指定ProducerBatch（消息累加器中BufferPool中的）可复用大小
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
//        // 生产者会在ProducerBatch被填满或者等待超过LINGER_MS_CONFIG时发送
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        // key 和 value 的序列化
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
//        // 客户端id
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
//        return props;
//    }
//
//    /**
//     * 消费者创建工厂
//     * @return 默认消费者工厂
//     */
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//
//    /**
//     * @return KafkaListenerContainerFactory<ConcurrentMessageListenerContainer < String, String>>
//     * @author js-reke
//     * @date 2019-11-11 18:07
//     */
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        // 设置消费者工厂
//        factory.setConsumerFactory(consumerFactory());
//        // 要创建的消费者数量(5 个线程并发处理)
//        factory.setConcurrency(concurrency);
//        return factory;
//    }
//
//    /**
//     * 消费者相关配置
//     * @return 消费者配置属性
//     */
//    private Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>(10);
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
//        // 手动位移提交
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ifAutoCommit);
//        // 消费组id
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        // 自动位移提交间隔时间
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
//        // 消费组失效超时时间
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
//        // 位移丢失和位移越界后的恢复起始位置
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffSetReset);
//        // 键序列化方式
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
//        // 值序列化方式
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
//        // 最大接收消息条数
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
//        return props;
//    }
//}
