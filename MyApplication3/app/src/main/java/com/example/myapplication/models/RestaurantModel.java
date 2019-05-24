package com.example.myapplication.models;

import com.google.firebase.firestore.DocumentSnapshot;

public class RestaurantModel {
    public String id;
    public String name;
    public String address;
    public String imageUri;
    public long numOfReviews;
    public long rateLocal;
    public long rateTraveler;

    public RestaurantModel() {
    }

    public RestaurantModel(DocumentSnapshot document) {
        id = document.getId();
        name = (String)document.get("name");
        address = (String)document.get("address");
        imageUri = (String)document.get("imageUri");
        rateLocal = (long)document.get("rateLocal");
        rateTraveler = (long)document.get("rateTraveler");
        numOfReviews = (long)document.get("numOfReviews");
    }
}
