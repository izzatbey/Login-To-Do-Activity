package com.example.logintodo.modul.listTask;

import com.example.logintodo.data.model.Task;

import java.util.ArrayList;

public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View view;

    public ListPresenter(ListContract.View view) {

        this.view = view;
    }

    @Override
    public void start() {

    }


    @Override
    public ArrayList<Task> getDataSet() {
        ArrayList<Task> data = new ArrayList<Task> ();
        data.add(new Task("1","Task 1", "Kerjakan task satu"));
        data.add(new Task("2", "Task 2", "Kerjakan task dua"));
        return data;
    }
}
