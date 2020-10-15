package com.example.logintodo.modul.listTask;

import android.view.View;

import com.example.logintodo.base.BaseFragmentHolderActivity;

public class ListActivity extends BaseFragmentHolderActivity {

    ListFragment listFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        listFragment = new ListFragment();
        ListPresenter presenter = new ListPresenter(listFragment);
        listFragment.setPresenter(presenter);
        setCurrentFragment(listFragment, false);
    }
}
