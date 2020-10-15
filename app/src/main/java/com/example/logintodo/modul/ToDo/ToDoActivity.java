package com.example.logintodo.modul.ToDo;

import android.content.Intent;

import com.example.logintodo.base.BaseFragmentHolderActivity;

import static com.example.logintodo.modul.Constants.TASK_TYPE;

public class ToDoActivity extends BaseFragmentHolderActivity {

    ToDoFragment todoFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        todoFragment = new ToDoFragment();
        ToDoPresenter mPresent = new ToDoPresenter(todoFragment);
        todoFragment.setPresenter(mPresent);

        Intent intent = getIntent();
        mPresent.setType(intent.getStringExtra(TASK_TYPE));
        setCurrentFragment(todoFragment, true);

    }
}
