package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.myapplication.adapters.RestaurantAdapter;
import com.example.myapplication.models.RestaurantModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView restaurantsView = findViewById(R.id.rv_restaurant);
        restaurantsView.setLayoutManager(new LinearLayoutManager(this));

        final RestaurantAdapter adapter = new RestaurantAdapter();
        restaurantsView.setAdapter(adapter);

        FirebaseFirestore store = FirebaseFirestore.getInstance();
        store.collection("restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document: task.getResult().getDocuments()) {
                    RestaurantModel model = new RestaurantModel();
                    model.name = (String)document.get("name");
                    model.address = (String)document.get("address");
                    model.imageUri = (String)document.get("imageUri");
                    model.rateLocal = (long)document.get("rateLocal");
                    model.rateTraveler = (long)document.get("rateTraveler");
                    model.numOfReviews = (long)document.get("numOfReviews");

                    adapter.addItem(model);
                }
            }
        });
    }
}