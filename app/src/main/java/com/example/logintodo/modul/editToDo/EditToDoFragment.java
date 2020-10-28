package com.example.logintodo.modul.editToDo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;

import com.example.logintodo.R;
import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.data.model.Task;
import com.example.logintodo.modul.listTask.ListActivity;

public class EditToDoFragment extends BaseFragment<EditToDoActivity, EditToDoContract.Presenter> implements EditToDoContract.View {

    EditText etToDoTitle;
    EditText etToDoDescription;
    Button btnSave;
    String id;

    public EditToDoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_todolist, container, false);
        mPresenter = new EditToDoPresenter(this);
        mPresenter.start();

        etToDoTitle = fragmentView.findViewById(R.id.newTitleText);
        etToDoDescription = fragmentView.findViewById(R.id.newDescText);
        btnSave = fragmentView.findViewById(R.id.newTaskButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtSaveClick();
            }
        });

        setTitle("Add New Task");
        mPresenter.loadData(this.id);

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etToDoTitle.getText().toString();
        String description = etToDoDescription.getText().toString();
        mPresenter.saveData(title,description);
    }

    @Override
    public void setPresenter(EditToDoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, ListActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showData(Task task) {
        this.etToDoTitle.setText(task.getTitle());
        this.etToDoDescription.setText(task.getDescription());
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

}

