package com.example.logintodo.modul.editToDo;

import android.util.Log;

import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TableHandler;
import com.example.logintodo.data.source.session.SessionRepository;

public class EditToDoPresenter implements EditToDoContract.Presenter {
    private final EditToDoContract.View view;
    TableHandler tableHandler;
    SessionRepository sessionRepository;

    public EditToDoPresenter(EditToDoContract.View view, TableHandler tableHandler, SessionRepository sessionRepository) {
        this.view = view;
        this.tableHandler = tableHandler;
        this.sessionRepository = sessionRepository;

    }

    @Override
    public void start() {
    }

    public void saveData(Task task){
        tableHandler.update(task);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

    public void loadData(String id) {
        //load data task by id
        //then send data to fragment
        Task task = (Task) tableHandler.readById(id);
        Log.d("To Do List App",">>>>>"+ task.getId());
        view.showData(task);
    }

    public void deleteData(int id){
        tableHandler.delete(id);
    }

}