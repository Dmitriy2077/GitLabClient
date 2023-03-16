package com.lubinskiy.app01.gitAPI;

import java.util.List;

import com.lubinskiy.app01.gitProjects.GitLabProject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitLabAPI {
    String ENDPOINT = "https://gitlab.com";

    @GET("/api/v4/users/{user_id}/projects?simple=true")
    Call<List<GitLabProject>> getPrivateProjects(@Path("user_id") String user_id,
                                                 @Query("private_token") String private_token);

    @GET("/api/v4/projects?simple=true")
    Call<List<GitLabProject>> getPublicProjects(@Query("private_token") String private_token);
}
