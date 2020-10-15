package com.example.logintodo.modul.listTask;

public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View view;

    public ListPresenter(ListContract.View view) {

        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void addTask() {
        view.ToAddTask();
    }
}
