package com.example.logintodo.modul.ToDo;

import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TableHandler;
import com.example.logintodo.data.source.session.SessionRepository;

public class ToDoPresenter implements ToDoContract.Presenter {

    private final ToDoContract.View view;
    private final TableHandler tableHandler;
    private final SessionRepository sessionRepository;

    public ToDoPresenter(ToDoContract.View view,  SessionRepository sessionRepository,TableHandler tableHandler) {

        this.view = view;
        this.tableHandler = tableHandler;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(Task task) {
        tableHandler.create(task);
        //save new task
        //then go back to task list
        view.redirectToListTask();
    }

}
