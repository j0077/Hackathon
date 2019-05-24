package com.example.myapplication;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //식당 이름 받아오기
        String restaurantName = "rName";
        TextView rNameText = (TextView)findViewById(R.id.idrName);
        rNameText.setText(restaurantName);

        //평점 계산
        double traveler_score = 0.0;
        double locals_score = 0.0;

        String t_Score = new Double(traveler_score).toString();
        String l_Score = new Double(locals_score).toString();
        TextView tScoreText = (TextView)findViewById(R.id.tScore);
        tScoreText.setText(t_Score);
        TextView lScoreText = (TextView)findViewById(R.id.lScore);
        lScoreText.setText(l_Score);

        //recyclerview 설정
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //이미지, 리뷰아이디 받아오기
        ArrayList<ReviewRecyclerItem> items = new ArrayList<>();
        for(ReviewRecyclerItem i : items){
//            items.add(new ReviewRecyclerItem( , ));
        }

        mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(items, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

}
