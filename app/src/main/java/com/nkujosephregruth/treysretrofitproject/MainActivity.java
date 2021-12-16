package com.nkujosephregruth.treysretrofitproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinkedList<Post> mPosts;

    public static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Post>> call = jsonPlaceholderAPI.getPosts();

        mPosts = new LinkedList<>();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                List<Post> posts = response.body();
                for(Post post: posts) {
                    mPosts.addLast(post);
                    Log.d(TAG, "onResponse: " + post);
                }
                recyclerView = findViewById(R.id.recyclerPosts);
                PostListAdapter postListAdapter = new PostListAdapter(MainActivity.this, mPosts);
                recyclerView.setAdapter(postListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(MainActivity.this, recyclerView, new RecyclerViewItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        MainActivity.this.launchSecondActivity(view, mPosts.get(position));
                        Snackbar.make(view, "onClick at position : " + position, Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Snackbar.make(view, "onLongClick at position : " + position, Snackbar.LENGTH_LONG).show();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + call.toString() + t.toString());
            }
        });
    }

    public void launchSecondActivity(View view, Post post) {
        Intent intent = new Intent(this, PostTextActivity.class);
        String[] strings = new String[]{post.getTitle(), post.getBody()};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strings));
        intent.putExtra(PostTextActivity.EXTRA_REPLY, arrayList);
        startActivity(intent);
    }
}