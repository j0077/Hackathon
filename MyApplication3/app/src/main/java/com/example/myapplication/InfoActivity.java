package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String restaurantName = intent.getExtras().getString("rName");
        TextView rNameText = (TextView)findViewById(R.id.idrName);
        rNameText.setText(restaurantName);

        //평균 평점 계산
        double traveler_score = 0.0;
        double locals_score = 0.0;

        String t_Score = new Double(traveler_score).toString();
        String l_Score = new Double(locals_score).toString();
        TextView tScoreText = (TextView)findViewById(R.id.tScore);
        tScoreText.setText(t_Score);
        TextView lScoreText = (TextView)findViewById(R.id.lScore);
        lScoreText.setText(l_Score);

        //사진 받아오기
//        LinearLayout layout = (LinearLayout) layout.findViewById(R.id.linearLayout);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT);
//
//
//        ImageView iv = new ImageView(this);
//        iv.setImageResource(R.drawable.image);
////        iv.setLayoutParams(textParams);
//
//        layout.addView(iv);
    }

}
