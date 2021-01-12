package com.example.logintodo.modul.editToDo;

import com.example.logintodo.base.BasePresenter;
import com.example.logintodo.base.BaseView;
import com.example.logintodo.data.model.Task;

public interface EditToDoContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData(Task task);
        void setId(String id);

    }

    interface Presenter extends BasePresenter {
        void saveData(Task task);
        void loadData(String id);
        void deleteData(int id);
    }
}
