package com.example.logintodo.modul.listTask;

import android.view.View;

import com.example.logintodo.base.BaseFragmentHolderActivity;

public class ListActivity extends BaseFragmentHolderActivity {

    ListFragment listFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        listFragment = new ListFragment();
        listFragment.setBtBack(btBack);
        setCurrentFragment(listFragment, false);
    }
}
