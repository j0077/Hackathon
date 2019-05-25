package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.adapters.ClickListener;
import com.example.myapplication.adapters.ReviewAdapter;
import com.example.myapplication.models.RestaurantModel;
import com.example.myapplication.models.ReviewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class InfoActivity extends AppCompatActivity {

    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        double rateLocal = intent.getDoubleExtra("rateLocal", 4.0);
        double rateTraveler = intent.getDoubleExtra("rateTraveler", 3.5);

        FloatingActionButton button = findViewById(R.id.btn_write);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent writeIntent = new Intent(getApplicationContext(), WriteActivity.class);
                writeIntent.putExtra("id", id);
                startActivity(writeIntent);
            }
        });

        TextView titleView = findViewById(R.id.info_title);
        TextView rateLocalView = findViewById(R.id.local_rate);
        TextView rateTravelerView = findViewById(R.id.traveler_rate);

        titleView.setText(name);
        rateLocalView.setText(String.format("%.1f", rateLocal));
        rateTravelerView.setText(String.format("%.1f", rateTraveler));

        reviewAdapter = new ReviewAdapter(new ClickListener<ReviewModel>() {
            @Override
            public void onClick(ReviewModel reviewModel) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                intent.putExtra("id", reviewModel.id);
                intent.putExtra("name", reviewModel.name);
                intent.putExtra("text", reviewModel.text);
                intent.putExtra("rate", reviewModel.rate);
                intent.putExtra("vip", reviewModel.vip);
                intent.putExtra("images", reviewModel.images);
                startActivity(intent);
            }
        });

        RecyclerView reviewList = findViewById(R.id.rv_reviews);
        reviewList.setAdapter(reviewAdapter);
        reviewList.setLayoutManager(new GridLayoutManager(this, 4));

        FirebaseFirestore store = FirebaseFirestore.getInstance();
        store.collection("reviews-new").whereEqualTo("restarunantId", id).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document: task.getResult().getDocuments()) {
                            ReviewModel reviewModel = new ReviewModel(document);
                            reviewAdapter.addItem(reviewModel);
                        }
                    }
                });
    }

}
