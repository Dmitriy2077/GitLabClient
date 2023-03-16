package com.lubinskiy.app01.gitProjects;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class GitLabProject{

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("name_with_namespace")
    private String nameWithNamespace;

    @SerializedName("path")
    private String path;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("ssh_url_to_repo")
    private String sshUrlToRepo;

    @SerializedName("web_url")
    private String webUrl;

    @SerializedName("last_activity_at")
    private String lastActivityAt;

    @SerializedName("namespace")
    private Namespace namespace;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameWithNamespace() {
        return nameWithNamespace.contains("/") ?
                nameWithNamespace.split("/")[0] : nameWithNamespace;
    }

    public String getCreatedAt() {
        if (createdAt.contains("T")) {
            String day = createdAt.split("T")[0];
            String time = createdAt.substring(createdAt.indexOf('T') + 1, createdAt.indexOf('.'));
            createdAt = day + " " + time;
        }
        return createdAt;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getLastActivityAt() {
        return lastActivityAt;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public String toString() {
        return "GitLabProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameWithNamespace='" + nameWithNamespace + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", lastActivityAt='" + lastActivityAt + '\'' +
                ", namespace=" + namespace +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitLabProject that = (GitLabProject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameWithNamespace, that.nameWithNamespace) &&
                Objects.equals(path, that.path) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(sshUrlToRepo, that.sshUrlToRepo) &&
                Objects.equals(webUrl, that.webUrl) &&
                Objects.equals(lastActivityAt, that.lastActivityAt) &&
                Objects.equals(namespace, that.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameWithNamespace, path, createdAt, sshUrlToRepo, webUrl, lastActivityAt, namespace);
    }
}
