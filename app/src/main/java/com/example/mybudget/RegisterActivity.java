package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText username = findViewById(R.id.usernameEditText);
        EditText password = findViewById(R.id.passwordEditText);
        EditText confirmPassword = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.submitSignUpButton);

        registerButton.setOnClickListener(view -> {
            String enteredUsername = username.getText().toString().trim();
            String enteredPassword = password.getText().toString();
            String enteredConfirmation = confirmPassword.getText().toString();

            if (enteredUsername.isEmpty()) {
                username.setError("Enter a username");
                return;
            }

            if (enteredPassword.length() < 6) {
                password.setError("Password must be at least 6 characters");
                return;
            }

            if (!enteredPassword.equals(enteredConfirmation)) {
                confirmPassword.setError("Passwords do not match");
                return;
            }

            Intent result = new Intent();
            result.putExtra("username", enteredUsername);
            result.putExtra("password", enteredPassword);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}