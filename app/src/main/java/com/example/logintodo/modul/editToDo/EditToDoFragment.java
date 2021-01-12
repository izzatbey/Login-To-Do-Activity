package com.example.logintodo.modul.editToDo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logintodo.R;
import com.example.logintodo.base.BaseFragment;
import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TaskTableHandler;
import com.example.logintodo.data.source.session.TaskSessionRepository;
import com.example.logintodo.modul.listTask.ListActivity;

public class EditToDoFragment extends BaseFragment<EditToDoActivity, EditToDoContract.Presenter> implements EditToDoContract.View {

    EditText etToDoTitle;
    EditText etToDoDescription;
    CheckBox textBoxComplete;
    Button btnEdit;
    Button btnDelete;
    String id;

    public EditToDoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.edit_task, container, false);
        mPresenter = new EditToDoPresenter(this, new TaskTableHandler(getActivity()), new TaskSessionRepository(getActivity()));
        mPresenter.start();

        etToDoTitle = fragmentView.findViewById(R.id.newTitleText);
        etToDoDescription = fragmentView.findViewById(R.id.newDescText);
        textBoxComplete = fragmentView.findViewById(R.id.todoCheckBox);
        btnEdit = fragmentView.findViewById(R.id.btn_edit);
        btnDelete = fragmentView.findViewById(R.id.btn_delete);

        btnEdit.setText("Update");

        setTitle("Edit Task");
        mPresenter.loadData(this.id);


        return fragmentView;
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
        Log.d("tag",task.getDescription());
        this.etToDoDescription.setText(task.getDescription());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etToDoTitle.getText().toString();
                String description = etToDoDescription.getText().toString();
                Integer isComplete = textBoxComplete.isChecked() ? 1 : 0;

                task.setTitle(title);
                task.setDescription(description);
                mPresenter.saveData(task);
                //Toast.makeText(activity, title + " " + description, Toast.LENGTH_LONG).show();
                redirectToTaskList();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.deleteData(Integer.parseInt(task.getId()));
                redirectToTaskList();
            }
        });
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }
}