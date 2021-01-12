package com.example.logintodo.modul.listTask;

import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TableHandler;

import java.util.ArrayList;

public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View view;
    private final TableHandler tableHandler;

    public ListPresenter(ListContract.View view, TableHandler tableHandler) {

        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {

    }


    @Override
    public ArrayList<Task> getDataSet() {
        ArrayList<Task> data = tableHandler.readAll();
        return data;
    }
}
