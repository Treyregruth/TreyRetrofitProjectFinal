package com.nkujosephregruth.treysretrofitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PostTextActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.nkujosephregruth.treysretrofitproject.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_text);
        TextView textViewTitle = findViewById(R.id.postTitleTextView);
        TextView textView = findViewById(R.id.postTextView);
        if(getIntent().hasExtra(EXTRA_REPLY)) {
            ArrayList<String> strings = getIntent().getStringArrayListExtra(EXTRA_REPLY);
            textViewTitle.setText(strings.get(0));
            textView.setText(strings.get(1));
        }
    }

    public void returnBack(View view) {
        String reply = "";
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}