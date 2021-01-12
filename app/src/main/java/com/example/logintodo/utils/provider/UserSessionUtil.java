package com.example.logintodo.utils.provider;

import com.example.logintodo.data.model.User;
import com.example.logintodo.data.source.session.UserSessionRepository;

public class UserSessionUtil {
    private UserSessionRepository userSessionRepository;

    public UserSessionUtil(UserSessionRepository userSessionRepository){
        this.userSessionRepository = userSessionRepository;
    }

    public void setSession(User user){
        userSessionRepository.setSessionData(user);
    }

    public User getSession(){
        return userSessionRepository.getSessionData();
    }

    public void updateSession(User user){
        userSessionRepository.update(user);
    }

    public void logout(){
        userSessionRepository.destroy();
    }
}
