package mwsu.springframework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * Created by bingyang.wei on 5/17/2017.
 */
@Service
public class SendTextMessageServiceImpl implements SendTextMessageService{

    private Queue textMessageQueue;
    private JmsTemplate jmsTemplate;

    @Autowired
    public void setTextMessageQueue(Queue textMessageQueue) {
        this.textMessageQueue = textMessageQueue;
    }
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendTextMessage(String msg) {
        this.jmsTemplate.convertAndSend(this.textMessageQueue, msg);
    }
}
