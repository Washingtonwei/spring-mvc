package mwsu.springframework.services.security;

import mwsu.springframework.domain.User;
import mwsu.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by bingyang.wei on 5/16/2017.
 */
@Component
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent>{

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent loginSuccessEvent) {
        Authentication authentication = (Authentication) loginSuccessEvent.getSource();
        System.out.println("LoginEvent Success for: " + authentication.getPrincipal());
        updateUserAccount(authentication);
    }

    public void updateUserAccount(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if(user != null){//user found
            user.setFailedLoginAttemptes(0);
            System.out.println("Good login!");
            userService.saveOrUpdate(user);
        }
    }
}
