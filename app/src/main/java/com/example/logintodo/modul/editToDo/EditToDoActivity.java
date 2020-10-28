package com.example.logintodo.modul.editToDo;

import androidx.fragment.app.FragmentActivity;
import android.view.View;
import com.example.logintodo.base.BaseFragmentHolderActivity;

public class EditToDoActivity extends BaseFragmentHolderActivity {
    EditToDoFragment editToDoFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        editToDoFragment = new EditToDoFragment();
        String id = getIntent().getExtras().getString("TaskId");
        editToDoFragment.setId(id);
        setCurrentFragment(editToDoFragment, false);

    }
}
