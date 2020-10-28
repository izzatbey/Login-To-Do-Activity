package com.example.logintodo.modul.ToDo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.R;
import com.example.logintodo.modul.listTask.ListActivity;

public class ToDoFragment extends BaseFragment<ToDoActivity, ToDoContract.Presenter> implements ToDoContract.View {

    EditText etToDoTitle;
    EditText etToDoDescription;
    Button btnSave;

    public ToDoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.new_task, container, false);
        mPresenter = new ToDoPresenter(this);
        mPresenter.start();

        etToDoTitle = fragmentView.findViewById(R.id.newTitleText);
        etToDoDescription = fragmentView.findViewById(R.id.newDescText);
        btnSave = fragmentView.findViewById(R.id.newTaskButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("Add New Task");

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etToDoTitle.getText().toString();
        String description = etToDoDescription.getText().toString();
        mPresenter.saveData(title,description);
    }


    @Override
    public void setPresenter(ToDoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void redirectToListTask() {
        Intent intent = new Intent(activity, ListActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
