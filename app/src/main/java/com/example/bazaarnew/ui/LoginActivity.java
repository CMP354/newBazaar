package com.example.bazaarnew.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bazaarnew.MainActivity;
import com.example.bazaarnew.R;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "";
    private EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    SignInButton button;
    private final static int RC_SIGN_IN = 123;

    FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //check the current user
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        Button ahlogin = (Button) findViewById(R.id.login_in_button);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button btnSignIn = (Button) findViewById(R.id.sign_up_button);
//        button = (SignInButton) findViewById(R.id.sign_in_google);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();
        // Checking the email id and password is Empty
        ahlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
              //  progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // there was an error
                                    Log.d(TAG, "signInWithEmail:success");
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.d(TAG, "singInWithEmail:Fail");
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };
    }
}
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    }
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//                // ...
//            }
//        }
//    }
//    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            //updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(singin_activity.this, "Aut Fail", Toast.LENGTH_SHORT).show();
//                            //updateUI(null);
//                        }
//                        // ...
//                    }
//                });
//    }
//}
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.bazaarnew.MainActivity;
//import com.example.bazaarnew.R;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private AutoCompleteTextView mEmailView;
//    private EditText mPasswordView;
//
//    // The validator for the email input field.
//    private EmailValidator mEmailValidator;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//
//
//        mEmailView = findViewById(R.id.email);
//        mPasswordView = findViewById(R.id.password);
//
//
//        final Toast loginError= Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT);
//        final Toast emptyFields= Toast.makeText(this, "Username or Password Fields Missing ", Toast.LENGTH_SHORT);
//
//        Button mUserSignInButton = findViewById(R.id.user_login_in_button);
//        mUserSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String email = mEmailView.getText().toString();
//                String password = mPasswordView.getText().toString();
//                // Setup field validators.
//                mEmailValidator = new EmailValidator();
//                mEmailView.addTextChangedListener(mEmailValidator);
//                if (password.equals("")) {
//                    mPasswordView.setError("Password field can not be blank");
//                    emptyFields.show();
//                }
//                if (email.equals("")) {
//                    mEmailView.setError("Password field can not be blank");
//                    emptyFields.show();
//                    if (!mEmailValidator.isValid()) {
//                        mEmailView.setError("Invalid email");
//                    }
//                }
//                login("user", "123456","Sakhnini ", "Dara ", "dara@aus.edu", "123456789");
//            }
//
//        });
//
//
//
///*        Button mAdminSignInButton = findViewById(R.id.admin_login_in_button);
//        mAdminSignInButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String email = mEmailView.getText().toString();
//                String password = mPasswordView.getText().toString();
//
//                if (password.equals("")) {
//                    mPasswordView.setError("Password field can not be blank");
//                    emptyFields.show();
//                }
//                if (email.equals("")) {
//                    mEmailView.setError("Email field can not be blank");
//                    emptyFields.show();
//                    if (!mEmailValidator.isValid()) {
//                        mEmailView.setError("Invalid email");
//                    }
//                }
//                login("admin", "123456","Sakhnini ", "Dara ", "dara@aus.edu", "123456789");
//
//            }
//            });*/
//
//
//        mEmailValidator = new EmailValidator();
//        mEmailView.addTextChangedListener(mEmailValidator);
//
//
//        Button mSignUpButton = findViewById(R.id.email_sign_up_button);
//        mSignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    public void login(String type, String id, String fname, String lname, String email, String num){
//
//        Intent intent=null;
//
//        intent = new Intent(LoginActivity.this, MainActivity.class);
//
//        intent.putExtra("User_ID", id);
//        intent.putExtra("User_FName", fname);
//        intent.putExtra("User_LName", lname);
//        intent.putExtra("User_Email", email);
//        intent.putExtra("User_Num", num);
//        startActivity(intent);
//    }
//}