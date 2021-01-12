package com.example.logintodo.modul.login;

import android.app.Activity;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
        void handleSignInResult(Activity activity, GoogleSignInResult result, FirebaseAuth firebaseAuth);
    }
}
