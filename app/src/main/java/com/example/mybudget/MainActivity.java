package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTER_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInButton = findViewById(R.id.signInButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivityForResult(intent, REGISTER_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER_REQUEST
                && resultCode == RESULT_OK
                && data != null) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginIntent.putExtra("username", data.getStringExtra("username"));
            loginIntent.putExtra("password", data.getStringExtra("password"));
            startActivity(loginIntent);
        }
    }
}