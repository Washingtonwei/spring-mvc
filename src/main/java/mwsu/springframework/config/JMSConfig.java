package mwsu.springframework.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * Created by bingyang.wei on 5/17/2017.
 */
@Configuration
public class JMSConfig {
    public static final String textMessage = "text.messagequeue";

    @Bean
    public Queue textMessageQueue(){
        return new ActiveMQQueue(textMessage);
    }
}
