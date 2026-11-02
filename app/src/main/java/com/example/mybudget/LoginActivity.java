package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int REGISTER_REQUEST = 1;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private String registeredUsername;
    private String registeredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        registeredUsername = getIntent().getStringExtra("username");
        registeredPassword = getIntent().getStringExtra("password");

        if (registeredUsername != null) {
            usernameEditText.setText(registeredUsername);
        }
        if (registeredPassword != null) {
            passwordEditText.setText(registeredPassword);
        }

        Button loginButton = findViewById(R.id.submitSignInButton);
        Button registerButton = findViewById(R.id.openRegisterButton);

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, REGISTER_REQUEST);
        });

        loginButton.setOnClickListener(view -> checkLogin());
    }

    private void checkLogin() {
        String enteredUsername = usernameEditText.getText().toString().trim();
        String enteredPassword = passwordEditText.getText().toString();

        if (enteredUsername.isEmpty()) {
            usernameEditText.setError("Enter your username");
            return;
        }

        if (enteredPassword.isEmpty()) {
            passwordEditText.setError("Enter your password");
            return;
        }

        if (registeredUsername == null || registeredPassword == null) {
            Toast.makeText(this, "Please register first", Toast.LENGTH_SHORT).show();
            return;
        }

        if (enteredUsername.equals(registeredUsername)
                && enteredPassword.equals(registeredPassword)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER_REQUEST
                && resultCode == RESULT_OK
                && data != null) {
            registeredUsername = data.getStringExtra("username");
            registeredPassword = data.getStringExtra("password");

            usernameEditText.setText(registeredUsername);
            passwordEditText.setText(registeredPassword);
        }
    }
}