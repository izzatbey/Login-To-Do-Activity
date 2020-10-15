package com.example.logintodo.modul.ToDo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.R;
import com.example.logintodo.modul.listTask.ListActivity;

public class ToDoFragment extends BaseFragment<ToDoActivity, ToDoContract.Presenter> implements ToDoContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.new_task, container, false);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void addTask() {
        final Button taskAddButton = fragmentView.findViewById(R.id.newTaskButton);
        taskAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ListActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });
        taskAddButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ToDoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
