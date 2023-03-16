package com.lubinskiy.app01.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lubinskiy.app01.gitProjects.GitLabProject;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<GitLabProject> mGitLabProjects;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v;
        v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.mText.setText(mGitLabProjects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mGitLabProjects.size();
    }

    public void changeList(List<GitLabProject> newList, OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        mGitLabProjects.clear();
        mGitLabProjects.addAll(newList);
        notifyDataSetChanged();
    }

    public void createList() {
        mGitLabProjects = new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(GitLabProject gitLabProject);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        private MyViewHolder(View v) {
            super(v);
            mText = v.findViewById(android.R.id.text1);
            v.setOnClickListener(view -> {
                GitLabProject gitLabProject = mGitLabProjects.get(getAdapterPosition());
                mOnItemClickListener.onItemClick(gitLabProject);
            });
        }
    }
}
