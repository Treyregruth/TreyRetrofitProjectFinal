package com.nkujosephregruth.treysretrofitproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Comparator;
import java.util.LinkedList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private final LinkedList<Post> mPostList;
    private LayoutInflater mInflator;

    public PostListAdapter(Context context, LinkedList<Post> posts) {
        mInflator= LayoutInflater.from(context);
        posts.sort(Comparator.comparingInt(Post::getId));
        mPostList = posts;
    }

    @NonNull
    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.recycle_contact_row_layout, parent, false);
        return new PostViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.PostViewHolder holder, int position) {
        Post mCurrent = mPostList.get(position);
        holder.postItemView.setText(mCurrent.getTitle());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public final TextView postItemView;
        final PostListAdapter mAdapter;

        public PostViewHolder(View itemView, PostListAdapter adapter) {
            super(itemView);
            postItemView = itemView.findViewById(R.id.txtName);
            this.mAdapter = adapter;
        }

        /*@SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Snackbar.make(view, "You clicked item " + mPosition, Snackbar.LENGTH_LONG).show();
        }*/
    }
}

