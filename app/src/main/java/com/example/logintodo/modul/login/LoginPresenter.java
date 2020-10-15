package com.example.logintodo.modul.login;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.logintodo.modul.Constants.EMAIL_KEY;
import static com.example.logintodo.modul.Constants.PASSWORD_KEY;
import static com.example.logintodo.modul.Constants.PREFERENCE_KEY;

public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View view;
    private final SharedPreferences sharedPreferences;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        this.view = view;
    }

    @Override
    public void performLogin(String email, String password){
        //proses login
        saveUser(email, password);
        view.redirectToList();
    }

    @Override
    public void start() {

    }

    private void saveUser(String email, String password) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWORD_KEY, password);
        editor.commit();
    }
}
