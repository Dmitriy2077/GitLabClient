package com.lubinskiy.app01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.lubinskiy.app01.R;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mTokenEditText;
    final static String USERNAME = "username";
    final static String TOKEN = "private_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initialization();
    }

    private void initialization() {
        mUsernameEditText = findViewById(R.id.usernameEditText);
        mTokenEditText = findViewById(R.id.tokenEditText);

        Intent intent = new Intent(this, ProjectsActivity.class);
        intent.putExtra("username", mUsernameEditText.getText().toString());
        intent.putExtra("private_token", mTokenEditText.getText().toString());
        startActivity(intent);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ProjectsActivity.class);
        intent.putExtra("username", mUsernameEditText.getText().toString());
        intent.putExtra("private_token", mTokenEditText.getText().toString());
        startActivity(intent);
    }
}
