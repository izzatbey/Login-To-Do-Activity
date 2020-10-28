package com.example.logintodo.modul.ToDo;

import android.content.Intent;
import android.view.View;

import com.example.logintodo.base.BaseFragmentHolderActivity;

import static com.example.logintodo.modul.Constants.TASK_TYPE;

public class ToDoActivity extends BaseFragmentHolderActivity {

    ToDoFragment todoFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        todoFragment = new ToDoFragment();
        setCurrentFragment(todoFragment, true);

    }
}
