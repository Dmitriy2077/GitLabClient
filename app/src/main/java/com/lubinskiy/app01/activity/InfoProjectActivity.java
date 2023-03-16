package com.lubinskiy.app01.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.lubinskiy.app01.R;

import static com.lubinskiy.app01.activity.ProjectsActivity.*;

public class InfoProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project_layout);

        initialisation();
    }

    private void initialisation() {
        final String PROJECT = "Unknown";
        final String TIME = "YYYY-MM-DD HH:MM:SS";
        final String LOGIN = "Login";
        final String NAME = "Nameless";

        String projectName = getIntent().getStringExtra(PROJECT_NAME);
        String createdAt = getIntent().getStringExtra(CREATED_AT);
        String userLogin = getIntent().getStringExtra(USER_LOGIN);
        String userName = getIntent().getStringExtra(USER_NAME);

        Toolbar projectNameToolbar = findViewById(R.id.header_toolbar);
        setSupportActionBar(projectNameToolbar);
        getSupportActionBar().setTitle(String.format("%s", projectName == null ? PROJECT : projectName));

        TextView createdAtTV = findViewById(R.id.createdTextView);
        TextView userLoginTV = findViewById(R.id.user_loginTextView);
        TextView usernameTV = findViewById(R.id.usernameTextView);

        createdAtTV.setText(String.format("Created at: %s", createdAt == null ? TIME : createdAt));
        userLoginTV.setText(String.format("User login: %s", userLogin == null ? LOGIN : userLogin));
        usernameTV.setText(String.format("Username: %s", userName == null ? NAME : userName));
    }
}
