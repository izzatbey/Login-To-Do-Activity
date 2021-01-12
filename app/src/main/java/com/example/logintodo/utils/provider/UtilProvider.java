package com.example.logintodo.utils.provider;


import android.content.Context;

import com.example.logintodo.data.source.session.UserSessionRepository;

public class UtilProvider {
    private static UserSessionUtil userSessionUtil;

    public static void initUserSession(Context context){
        UserSessionRepository userSession = new UserSessionRepository(context);
        userSessionUtil = new UserSessionUtil(userSession);
    }

    public static UserSessionUtil getUserSessionUtil(){
        return userSessionUtil;
    }
}
