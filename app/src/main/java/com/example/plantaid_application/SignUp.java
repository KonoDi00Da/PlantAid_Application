package com.example.plantaid_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class SignUp extends Fragment {

    private FirebaseAuth mAuth;
    LoadingDialog loadingDialog;
    private NavController navController;
    private NavOptions navOptions;

    private EditText editTextFirstName, editTextLastName, editTextPassword, editTextEmail;
    private Button btnSignup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            editTextFirstName = view.findViewById(R.id.userFirstName);
            editTextLastName = view.findViewById(R.id.userLastName);
            editTextEmail = view.findViewById(R.id.email);
            editTextPassword = view.findViewById(R.id.user_pass);
            btnSignup = view.findViewById(R.id.btnRegister);
            navController = Navigation.findNavController(view);
            loadingDialog = new LoadingDialog(getActivity());

            navOptions = new NavOptions.Builder().setPopUpTo(R.id.signUp, true)
                    .setEnterAnim(R.anim.slide_in)
                    .setExitAnim(R.anim.slide_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .setPopExitAnim(R.anim.fade_out)
                    .build();
            loadingDialog = new LoadingDialog(getActivity());
            mAuth = FirebaseAuth.getInstance();

            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerUser();
                }
            });

        } catch (NullPointerException e) {
            Log.v("TEST", "Error: " + e.toString());
        }
    }

    private void registerUser() {

        try {

            //loadingDialog.startLoading("Signing Up");
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String firstName = editTextFirstName.getText().toString().trim();
            String lastName = editTextLastName.getText().toString().trim();

            if (firstName.isEmpty()) {
                editTextFirstName.setError("First name is required");
                editTextFirstName.requestFocus();
                return;
            }
            if (lastName.isEmpty()) {
                editTextLastName.setError("Last name is required");
                editTextLastName.requestFocus();
                return;
            }
            if (email.isEmpty()) {
                editTextEmail.setError("Email is required");
                editTextEmail.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                editTextPassword.setError("Password is required");
                editTextPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                editTextPassword.setError("Password should be at least 6 characters");
                editTextPassword.requestFocus();
                return;
            }

            //put data into database
            mAuth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                            //Log.e("TAG", "Is New User!");
                            if (isNewUser) {
                               mAuth.createUserWithEmailAndPassword(email,password)
                                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               if (task.isSuccessful()){
                                                   mAuth.getCurrentUser().sendEmailVerification()
                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                               @Override
                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                   if(task.isSuccessful()){
                                                                       User user = new User(firstName,lastName,email);
                                                                       FirebaseDatabase.getInstance().getReference("Users")
                                                                               .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                               .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                           @Override
                                                                           public void onComplete(@NonNull Task<Void> task) {
                                                                              toast("Please check your email for verification");
                                                                               navController.navigate(R.id.action_signUp_to_login, null, navOptions);                                                                           }
                                                                       });
                                                                   }
                                                                   else{
                                                                       toast(task.getException().getMessage().toString());
                                                                   }
                                                               }
                                                           });
                                               }
                                               else{
                                                   Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                                   //loadingDialog.stopLoading();
                                                   toast("Please try again.");
                                               }
                                           }
                                       });
                            } else {
                                //Log.e("TAG", "Is Old User!");
                                //loadingDialog.stopLoading();
                                editTextEmail.setError("The email address is already in use by another account.");
                            }
                        }
                    });
        } catch (Exception e){
        toast("Submission Error, Please try again");
        Log.e("Submit Error", "exception", e);
    }
}
    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

