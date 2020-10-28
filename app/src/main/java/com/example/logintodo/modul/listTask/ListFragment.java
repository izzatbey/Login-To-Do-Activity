package com.example.logintodo.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logintodo.R;
import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.data.model.Task;
import com.example.logintodo.modul.ToDo.ToDoActivity;
import com.example.logintodo.modul.ToDo.ToDoFragment;
import com.example.logintodo.modul.editToDo.EditToDoActivity;
import com.example.logintodo.utils.RecyclerViewAdapterTodolist;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.logintodo.modul.Constants.*;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton btnAdd;

    public ListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.todo_activity, container, false);
        mPresenter = new ListPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.tasksRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");

        btnAdd = fragmentView.findViewById(R.id.fab);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToNewTask();
            }
        });

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                Log.d("To Do List App",">>>>>"+ position);
                editTask(id);
            }
        });

        return fragmentView;
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void ToNewTask() {
        Intent intent = new Intent(activity, ToDoActivity.class);
        startActivity(intent);
    }

    public void editTask(String id) {
        Intent intent = new Intent(activity, EditToDoActivity.class);
        intent.putExtra("TaskId", id);
        startActivity(intent);
    }
}
