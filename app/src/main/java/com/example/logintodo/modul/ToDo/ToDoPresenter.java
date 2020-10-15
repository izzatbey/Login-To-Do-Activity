package com.example.logintodo.modul.ToDo;

public class ToDoPresenter implements ToDoContract.Presenter {

    private final ToDoContract.View view;
    private String todoType;

    public ToDoPresenter(ToDoContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
            view.addTask();
    }

    @Override
    public void setType(String todoType) {
        this.todoType = todoType;
    }
}
