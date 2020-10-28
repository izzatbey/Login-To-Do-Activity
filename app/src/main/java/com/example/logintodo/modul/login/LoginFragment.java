package com.example.logintodo.modul.login;

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
import com.example.logintodo.modul.listTask.ListActivity;


public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;

    public LoginFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, requireContext());
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this::setBtLoginClick);

        setTitle("Login Site");

        return fragmentView;
    }

    private void setBtLoginClick(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email,password);
    }

    @Override
    public void redirectToList() {
            Intent intent = new Intent(activity, ListActivity.class);
            startActivity(intent);
            activity.finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

        this.mPresenter = presenter;
    }


}
