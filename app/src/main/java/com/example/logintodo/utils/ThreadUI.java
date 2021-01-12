package com.example.logintodo.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUI {
    public static Handler UIHandler;

    static{
        UIHandler = new Handler(Looper.getMainLooper());
    }

    public static void runOnUI(Runnable runnable){
        UIHandler.post(runnable);
    }
}
