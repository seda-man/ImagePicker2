package com.example.seda.scrolling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CustomScrollingView customScrollingView;
    private TextView text;
    private ArrayList<String> contents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        customScrollingView = (CustomScrollingView) findViewById(R.id.scrollView);
        for(int count = 0; count < 100; count++) {
            contents.add(String.valueOf(count));
        }
        customScrollingView.setPreviewText(text);
        customScrollingView.setContents(contents);

    }

}
