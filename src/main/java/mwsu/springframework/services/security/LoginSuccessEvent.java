package mwsu.springframework.services.security;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bingyang.wei on 5/16/2017.
 */
public class LoginSuccessEvent extends ApplicationEvent{
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
