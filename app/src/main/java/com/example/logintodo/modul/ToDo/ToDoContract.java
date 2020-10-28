package com.example.logintodo.modul.ToDo;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;

public interface ToDoContract {
    interface View extends BaseView<Presenter> {
        void redirectToListTask();
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
    }
}
