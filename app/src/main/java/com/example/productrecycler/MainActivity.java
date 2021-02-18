package com.example.productrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    TextView click_to_register;
    Button login_button;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //check if user sign in
        if (firebaseUser != null)
        {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        connectxml();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString().trim();
                String password_text = password.getText().toString().trim();

                if (TextUtils.isEmpty(email_text))
                {
                    Toast.makeText(MainActivity.this, "please enter the email", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password_text)){
                    Toast.makeText(MainActivity.this, "please enter the password", Toast.LENGTH_SHORT).show();
                } else{
                    loginuser(email_text,password_text);
                }

            }
        });

        click_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registerr.class));
            }
        });




    }

    private void loginuser(String email_text, String password_text) {

        firebaseAuth.signInWithEmailAndPassword(email_text,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "email or password incorrect or may be not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void connectxml() {
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_user);
        click_to_register = findViewById(R.id.tv_register);
    }
}
