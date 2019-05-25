package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.example.myapplication.adapters.ClickListener;
import com.example.myapplication.adapters.RestaurantAdapter;
import com.example.myapplication.models.RestaurantModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        RecyclerView restaurantsView = findViewById(R.id.rv_restaurant);
        restaurantsView.setLayoutManager(new LinearLayoutManager(this));

        restaurantAdapter = new RestaurantAdapter(new ClickListener<RestaurantModel>() {
            @Override
            public void onClick(RestaurantModel restaurantModel) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("id", restaurantModel.id);
                intent.putExtra("name", restaurantModel.name);
                intent.putExtra("rateLocal", restaurantModel.rateLocal);
                intent.putExtra("rateTraveler", restaurantModel.rateTraveler);
                startActivity(intent);
            }
        });
        restaurantsView.setAdapter(restaurantAdapter);

        fetchAll();
    }

    public void search(final String text) {
        restaurantAdapter.clear();

        FirebaseFirestore store = FirebaseFirestore.getInstance();
        store.collection("restaurants-new")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document: task.getResult().getDocuments()) {
                    RestaurantModel model = new RestaurantModel(document);
                    if (model.name.contains(text) || model.address.contains(text)) {
                        restaurantAdapter.addItem(model);
                    }
                }
            }
        });
    }

    public void fetchAll() {
        restaurantAdapter.clear();

        FirebaseFirestore store = FirebaseFirestore.getInstance();
        store.collection("restaurants-new")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document: task.getResult().getDocuments()) {
                    RestaurantModel model = new RestaurantModel(document);
                    restaurantAdapter.addItem(model);
                }
            }
        });
    }
}