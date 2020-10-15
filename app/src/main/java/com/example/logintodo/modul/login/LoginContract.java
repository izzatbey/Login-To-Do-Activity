package com.example.logintodo.modul.login;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
