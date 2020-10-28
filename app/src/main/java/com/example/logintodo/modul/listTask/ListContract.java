package com.example.logintodo.modul.listTask;

import java.util.ArrayList;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;
import com.example.logintodo.data.model.Task;

public interface ListContract {
    interface View extends BaseView<Presenter>{
        void ToNewTask();
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
    }
}
