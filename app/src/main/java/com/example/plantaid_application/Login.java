package com.example.plantaid_application;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

import java.util.Objects;

public class Login extends Fragment {

    private FirebaseAuth mAuth;

    private NavController navController;
    private NavOptions navOptions;

    private EditText editTxtEmail;
    private EditText editTxtPassword;
    private TextView forgotPass;
    private Button btnlogin;
    private Button btnSignup;
    LoadingDialog loadingDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTxtEmail = view.findViewById(R.id.login_email);
        editTxtPassword = view.findViewById(R.id.login_password);
        forgotPass = view.findViewById(R.id.txtForgotPas);
        btnlogin = view.findViewById(R.id.btnLogin);
        btnSignup = view.findViewById(R.id.btnSignUp);
        navController = Navigation.findNavController(view);

        navOptions = new NavOptions.Builder().setPopUpTo(R.id.signUp,true)
                .setEnterAnim(R.anim.slide_in)
                .setExitAnim(R.anim.slide_out)
                .setPopEnterAnim(R.anim.fade_in)
                .setPopExitAnim(R.anim.fade_out)
                .build();
        loadingDialog = new LoadingDialog(getActivity());

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_login_to_signUp,null,navOptions);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void userLogin() {
        String email = editTxtEmail.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTxtEmail.setError("Email is required");
            editTxtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTxtEmail.setError("Please enter a valid email");
            editTxtEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTxtPassword.setError("Password is required");
            editTxtPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTxtPassword.setError("Password should be at least 6 characters");
            editTxtPassword.requestFocus();
            return;
        }

        //add progressbar here

        //authenticate user login
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getActivity(), MainHome.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(), "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openDialog() {
        try{
            Dialog dialog = new Dialog(getActivity());
            //We have added a title in the custom layout. So let's disable the default title.
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
            dialog.setCancelable(true);
            //Mention the name of the layout of your custom dialog.
            dialog.setContentView(R.layout.reset_password);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            EditText resetEmail = dialog.findViewById(R.id.txtEmailReset);
            Button buttonSend = dialog.findViewById(R.id.button3);

            resetEmail.setText(resetEmail.getText().toString());
            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editTxtEmail.getText().toString().isEmpty()){
                        editTxtEmail.setError("Required");
                    } else {
                        dialog.dismiss();
                        //loadingDialog.startLoading("Please wait");
                        mAuth.sendPasswordResetEmail(resetEmail.getText().toString()).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            //loadingDialog.stopLoading();
                                            toast("Please check your mail box");
                                        } else {
                                            toast("Invalid Email");
                                            //loadingDialog.stopLoading();
                                            dialog.show();
                                            editTxtEmail.setText(resetEmail.getText().toString());
                                        }
                                    }
                                });
                    }
                }
            });

            dialog.show();
        } catch (Exception e){
            toast("Something went wrong, Please try again");
            Log.e("Forgot Pass Error", "exception", e);
        }
    }


    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}