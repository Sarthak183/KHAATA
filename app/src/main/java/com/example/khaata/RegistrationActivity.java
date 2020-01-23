package com.example.khaata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private Button btnSignup;
    private Button btnSignin;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog( this);
        email = findViewById(R.id.email_signp);
        password = findViewById(R.id.password_signup);

        btnSignup = findViewById(R.id.btn_signup);
        btnSignin = findViewById(R.id.btn_signin_up);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memail = email.getText().toString().trim();
                String mpassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(memail)) {
                    email.setError("Required Field..");
                    return;
                }

                if (TextUtils.isEmpty(mpassword)) {
                    password.setError("Required Field..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

//                mAuth.createUserWithEmailAndPassword(memail,mpassword)
//                        .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(getApplicationContext(),"Registration Complete..",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                            mDialog.dismiss();
//                        }
//                        else
//                        {
//                            Toast.makeText(getApplicationContext(),"Registration Error..",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                mAuth.createUserWithEmailAndPassword(memail, mpassword)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(), "Registration Complete..", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                        mDialog.dismiss();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration Error..", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                });
//
        }
    });
}
}
