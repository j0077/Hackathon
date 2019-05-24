package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

        restaurantAdapter = new RestaurantAdapter();
        restaurantsView.setAdapter(restaurantAdapter);

        fetchAll();
    }

    public void search(final String text) {
        restaurantAdapter.clear();

        FirebaseFirestore store = FirebaseFirestore.getInstance();
        store.collection("restaurants")
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
        store.collection("restaurants")
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