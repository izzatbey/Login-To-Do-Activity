package com.example.logintodo.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logintodo.R;
import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TaskTableHandler;
import com.example.logintodo.modul.ToDo.ToDoActivity;
import com.example.logintodo.modul.ToDo.ToDoFragment;
import com.example.logintodo.modul.editToDo.EditToDoActivity;
import com.example.logintodo.modul.login.LoginActivity;
import com.example.logintodo.utils.RecyclerViewAdapterTodolist;
import com.example.logintodo.utils.provider.UtilProvider;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.logintodo.modul.Constants.*;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View, GoogleApiClient.OnConnectionFailedListener {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    FloatingActionButton btnAdd;
    ImageButton btBack;

    public ListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.todo_activity, container, false);
        mPresenter = new ListPresenter(this, new TaskTableHandler(getActivity()));
        mPresenter.start();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        mRecyclerView = fragmentView.findViewById(R.id.tasksRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data, new TaskTableHandler(getActivity()), activity);
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

    public void setBtBack(ImageButton btBack){
        this.btBack = btBack;

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if(status.isSuccess()){
                                    UtilProvider.getUserSessionUtil().logout();
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(),"Logout Successful", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getActivity(),"Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){

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
