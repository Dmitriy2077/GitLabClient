package com.lubinskiy.app01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import com.lubinskiy.app01.gitAPI.GitLabAPI;
import com.lubinskiy.app01.R;
import com.lubinskiy.app01.adapter.RecyclerViewAdapter;
import com.lubinskiy.app01.gitProjects.GitLabProject;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectsActivity extends AppCompatActivity {

    private String mUsername;
    private String mToken;
    private List<GitLabProject> mGitLabProject;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerViewAdapter.OnItemClickListener mOnItemClickListener;

    private GitLabAPI mGitLabAPI;

    final static String PROJECT_NAME = "project_name";
    final static String CREATED_AT = "created_at";
    final static String USER_LOGIN = "user_login";
    final static String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_layout);
        initialization();

        mUsername = getIntent().getStringExtra(MainActivity.USERNAME);
        mToken = getIntent().getStringExtra(MainActivity.TOKEN);

        createGitLabApi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String userInput = s.toLowerCase(Locale.getDefault());
                boolean result = false;

                if (!userInput.isEmpty()) {
                    List<GitLabProject> newList = new ArrayList<>();

                    for (GitLabProject name : mGitLabProject) {
                        if (name.getName().toLowerCase(Locale.getDefault()).contains(userInput)) {
                            newList.add(name);
                        }
                    }

                    mAdapter.changeList(newList, mOnItemClickListener);
                    result = true;

                } else mAdapter.changeList(mGitLabProject, mOnItemClickListener);

                return result;
            }
        });
        return true;
    }

    public void onClick(View v) {
        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case (R.id.private_btn):
                mGitLabAPI.getPrivateProjects(mUsername, mToken).enqueue(callbackGetPrivateProjects);
                break;
            case (R.id.public_btn):
                mGitLabAPI.getPublicProjects(mToken).enqueue(callbackGetPublicProjects);
                break;
            default:
                Toast.makeText(this, "Something go wrong...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void initialization() {
        Toolbar toolbar = findViewById(R.id.projects_toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RecyclerViewAdapter(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.createList();

        mOnItemClickListener = (GitLabProject gitLabProject) -> {
            Intent intent = new Intent(this, InfoProjectActivity.class);
            intent.putExtra(PROJECT_NAME, gitLabProject.getName());
            intent.putExtra(CREATED_AT, gitLabProject.getCreatedAt());
            intent.putExtra(USER_LOGIN, gitLabProject.getNamespace().getName());
            intent.putExtra(USER_NAME, gitLabProject.getNameWithNamespace());
            startActivity(intent);
        };
    }

    private void createGitLabApi() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(25, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitLabAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        mGitLabAPI = retrofit.create(GitLabAPI.class);
    }

    Callback<List<GitLabProject>> callbackGetPrivateProjects =
            new Callback<List<GitLabProject>>() {
                @Override
                public void onResponse(Call<List<GitLabProject>> call,
                                       Response<List<GitLabProject>> response) {
                    if (response.isSuccessful()) {
                        mGitLabProject = response.body();
                        mAdapter.changeList(mGitLabProject, mOnItemClickListener);
                    } else {
                        Toast.makeText(ProjectsActivity.this, "Code: " +
                                        response.code() + " Message: " + response.message(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<GitLabProject>> call, Throwable t) {
                    Toast.makeText(ProjectsActivity.this, " Message: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            };

    Callback<List<GitLabProject>> callbackGetPublicProjects =
            new Callback<List<GitLabProject>>() {
                @Override
                public void onResponse(Call<List<GitLabProject>> call,
                                       Response<List<GitLabProject>> response) {
                    if (response.isSuccessful()) {
                        mGitLabProject = response.body();
                        mAdapter.changeList(mGitLabProject, mOnItemClickListener);
                    } else {
                        Toast.makeText(ProjectsActivity.this, "Code: " +
                                        response.code() + " Message: " + response.message(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<GitLabProject>> call, Throwable t) {
                    Toast.makeText(ProjectsActivity.this, " Message: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            };
}
