package com.example.logintodo.modul.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.logintodo.data.model.User;
import com.example.logintodo.utils.provider.UtilProvider;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View view;
    private final Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void start() {
        UtilProvider.initUserSession(context);

        if(UtilProvider.getUserSessionUtil().getSession() != null){                                             //new
            view.redirectToList();                                                               //jika sudah login langsung masuk profile
        }
    }

    public void handleSignInResult(Activity activity, GoogleSignInResult result, FirebaseAuth firebaseAuth){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            User user = new User();
            user.setId(account.getIdToken());
            user.setEmail(account.getEmail());

            //saving to Shared Preference
            UtilProvider.getUserSessionUtil().setSession(user);

            AuthCredential credential = GoogleAuthProvider.getCredential(user.getId(), null);
            firebaseAuthWithGoogle(activity, credential, firebaseAuth);
        }else{
            String messageToDisplay = "Authentication failed.";
            switch (result.getStatus().getStatusCode()) {
                case CommonStatusCodes.API_NOT_CONNECTED: //17
                    messageToDisplay += "The client attempted to call a method from an API that failed to connect.";
                    break;

                case CommonStatusCodes.DEVELOPER_ERROR: //10
                    messageToDisplay += "The application is misconfigured.";
                    break;

                case CommonStatusCodes.ERROR: //13
                    messageToDisplay += "The operation failed with no more detailed information.";
                    break;

                case CommonStatusCodes.INTERNAL_ERROR: //8
                    messageToDisplay += "An internal error occurred.";
                    break;

                case CommonStatusCodes.INVALID_ACCOUNT: //8
                    messageToDisplay += "Invalid account name specified.";
                    break;

                case CommonStatusCodes.SIGN_IN_REQUIRED: //8
                    messageToDisplay += "Please Sign In to continue.";
                    break;
            }
            Toast.makeText(activity, messageToDisplay, Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(final Activity activity, AuthCredential credential, FirebaseAuth firebaseAuth){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
                            view.redirectToList();
                        }else{
                            Toast.makeText(activity, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void performLogin(String email, String password){
        //proses login

        //if login success
        UtilProvider.getUserSessionUtil().setSession(new User(email, password));                                               //new

        //then call redirect to profile
        view.redirectToList();
    }
}
