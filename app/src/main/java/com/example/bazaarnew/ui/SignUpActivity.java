package com.example.bazaarnew.ui;


import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bazaarnew.MainActivity;
import com.example.bazaarnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private AutoCompleteTextView name, email_id, passwordcheck;
    private FirebaseAuth mAuth;
    private static final String TAG = "";
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        Button btnSignUp = (TextView) findViewById(R.id.login_page);
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignUpActivity.this, singin_activity.class);
//                startActivity(intent);
//            }
//        });
        mAuth = FirebaseAuth.getInstance();
        email_id = (AutoCompleteTextView) findViewById(R.id.signup_Email);
       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        passwordcheck = (AutoCompleteTextView) findViewById(R.id.signup_Password);
        Button ahsignup = (Button) findViewById(R.id.email_sign_up_button);
        ahsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_id.getText().toString();
                String password = passwordcheck.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Eamil Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
               // progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}





//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.example.bazaarnew.R;
//
//public class SignUpActivity extends AppCompatActivity {
//
//
//    // The validator for the email input field.
//    private EmailValidator mEmailValidator;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        final Toast SignupCancel= Toast.makeText(this, "Sign Up Cancelled", Toast.LENGTH_SHORT);
//
//
//
//        final AutoCompleteTextView Fname=(AutoCompleteTextView)findViewById(R.id.signup_FName);
//        final AutoCompleteTextView Lname=(AutoCompleteTextView)findViewById(R.id.signup_LName);
//        //   final AutoCompleteTextView ID=(AutoCompleteTextView)findViewById(R.id.signup_ID);
//        final AutoCompleteTextView Number=(AutoCompleteTextView)findViewById(R.id.signup_phoneNum);
//        final AutoCompleteTextView Email=(AutoCompleteTextView)findViewById(R.id.signup_Email);
//        final AutoCompleteTextView pass=(AutoCompleteTextView)findViewById(R.id.signup_Password);
//
//        final Toast SignupError= Toast.makeText(this, "Sign Up Was Unsuccessful", Toast.LENGTH_SHORT);
//        final Toast SuccessfulSignUp= Toast.makeText(this, "Successful Sign Up!", Toast.LENGTH_SHORT);
//
//
//        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_up_button);
//        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                String fname = (Fname.getText().toString());
//                String lname = (Lname.getText().toString());
//                //    String id = (ID.getText().toString());
//                String num = (Number.getText().toString());
//                String email = (Email.getText().toString());
//                String password = (pass.getText().toString());
//
//
//                mEmailValidator = new EmailValidator();
//                Email.addTextChangedListener(mEmailValidator);
//
//                if (password.equals("")) {
//                    pass.setError("Password field can not be blank");
//                }
//                if (email.equals("")) {
//                    Email.setError("Password field can not be blank");
//                    if (!mEmailValidator.isValid()) {
//                        Email.setError("Invalid email");
//                    }
//                }
//                if (num.equals("")) {
//                    Number.setError("Number can not be blank");
//                }
//                if (fname.equals("")) {
//                    Fname.setError("First Name can not be blank");
//                }
//                if (lname.equals("")) {
//                    Lname.setError("Number can not be blank");
//                }
//            }
//        });
//
//
//        Button mCancelButton = (Button) findViewById(R.id.cancelBtn);
//        mCancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SignupCancel.show();
//                finish();
//            }
//        });
//    }
//}
