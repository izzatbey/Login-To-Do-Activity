package com.example.logintodo.modul.listTask;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;

public interface ListContract {
    interface View extends BaseView<Presenter>{
        void ToAddTask();
    }

    interface Presenter extends BasePresenter {
        void addTask();
    }
}
