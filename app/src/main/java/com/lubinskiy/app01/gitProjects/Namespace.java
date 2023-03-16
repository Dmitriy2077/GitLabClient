package com.lubinskiy.app01.gitProjects;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Namespace {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("path")
    private String path;

    @SerializedName("kind")
    private String kind;

    @SerializedName("full_path")
    private String fullPath;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Namespace{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Namespace namespace = (Namespace) o;
        return Objects.equals(id, namespace.id) &&
                Objects.equals(name, namespace.name) &&
                Objects.equals(path, namespace.path) &&
                Objects.equals(kind, namespace.kind) &&
                Objects.equals(fullPath, namespace.fullPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, kind, fullPath);
    }
}
