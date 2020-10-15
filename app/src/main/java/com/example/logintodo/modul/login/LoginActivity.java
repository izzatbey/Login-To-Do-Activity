package com.example.logintodo.modul.login;

import android.view.View;

import com.example.logintodo.base.BaseFragmentHolderActivity;


public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;


    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }
}
