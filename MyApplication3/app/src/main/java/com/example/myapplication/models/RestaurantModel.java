package com.example.myapplication.models;

import com.google.firebase.firestore.DocumentSnapshot;

public class RestaurantModel {
    public String id;
    public String name;
    public String address;
    public String imageUri;
    public long numOfReviews;
    public double rateLocal;
    public double rateTraveler;

    public RestaurantModel() {
    }

    public RestaurantModel(DocumentSnapshot document) {
        id = document.getId();
        name = (String)document.get("name");
        address = (String)document.get("address");
        imageUri = (String)document.get("imageUri");
        rateLocal = (double)document.get("rateLocal");
        rateTraveler = (double)document.get("rateTraveler");
        numOfReviews = (long)document.get("numOfReviews");
    }
}
