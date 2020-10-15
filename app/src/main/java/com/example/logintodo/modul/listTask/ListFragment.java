package com.example.logintodo.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.logintodo.R;
import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.modul.ToDo.ToDoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.logintodo.modul.Constants.*;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View {

    private FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.todo_activity, container, false);

        btnAdd = fragmentView.findViewById(R.id.fab);
        btnAdd.setOnClickListener(view -> mPresenter.addTask());

        setTitle(getResources().getString(R.string.app_name));

        return fragmentView;
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void ToAddTask() {
        Intent intent = new Intent(activity, ToDoActivity.class);
        intent.putExtra(TASK_TYPE, "ADD");
        startActivity(intent);
    }
}
