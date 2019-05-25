package com.example.myapplication;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.adapters.SliderAdapter;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String text = intent.getStringExtra("text");
        long rate = intent.getLongExtra("rate", 0);
        boolean vip = intent.getBooleanExtra("vip", false);
        ArrayList<String> images = intent.getStringArrayListExtra("images");

        ViewPager viewPager = findViewById(R.id.slider);
        SliderAdapter adapter = new SliderAdapter(this, images);
        viewPager.setAdapter(adapter);

        TextView nameView = findViewById(R.id.user_name);
        TextView textView = findViewById(R.id.review_text);
        TextView rateView = findViewById(R.id.user_rate);

        ImageView vipIcon = findViewById(R.id.auth_logo);
        TextView vipLabel = findViewById(R.id.auth_text);
        if (!vip) {
            vipIcon.setVisibility(View.GONE);
            vipLabel.setVisibility(View.GONE);
        }

        nameView.setText(name);
        textView.setText(text);
        rateView.setText(String.valueOf(rate) + "점");
    }
}
