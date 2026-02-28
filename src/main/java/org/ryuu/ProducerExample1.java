package org.ryuu;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ProducerExample1 {
    private static final Logger logger = LoggerFactory.getLogger(ProducerExample1.class);

    static void main() {
        // Endpoint address, set to the Proxy address and port list, usually xxx:8080;xxx:8081
        String endpoint = "localhost:8081"; // 请确保此端口为 RocketMQ Proxy
        String topic = "TestTopic";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        // 构建消息时，使用 apache.rocketmq.v2.Message
        try (
                Producer producer = provider.newProducerBuilder()
                        .setTopics(topic)
                        .setClientConfiguration(configuration)
                        .build()
        ) {
            Message message = provider.newMessageBuilder()
                    .setTopic(topic)
                    .setKeys("messageKey")
                    .setTag("messageTag")
                    .setBody("messageBody".getBytes())
                    .build();
            SendReceipt sendReceipt = producer.send(message);
            logger.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
        } catch (ClientException e) {
            logger.error("Failed to send message", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}