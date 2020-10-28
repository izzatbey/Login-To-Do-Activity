package com.example.logintodo.modul.ToDo;

import com.example.logintodo.data.model.Task;

public class ToDoPresenter implements ToDoContract.Presenter {

    private final ToDoContract.View view;

    public ToDoPresenter(ToDoContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(String title, String description) {
        Task newTask = new Task("3", title, description);
        //save new task
        //then go back to task list
        view.redirectToListTask();
    }
}
