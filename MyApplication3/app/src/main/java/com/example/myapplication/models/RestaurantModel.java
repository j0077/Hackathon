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

        if (document.contains("name")) {
            name = (String)document.get("name");
        }
        if (document.contains("address")) {
            address = (String)document.get("address");
        }
        if (document.contains("imageUri")) {
            imageUri = (String)document.get("imageUri");
        }
        if (document.contains("rateLocal")) {
            rateLocal = (double)document.get("rateLocal");
        }
        if (document.contains("rateTraveler")) {
            rateTraveler = (double)document.get("rateTraveler");
        }
        if (document.contains("numofReviews")) {
            numOfReviews = (long)document.get("numofReviews");
        }

    }
}
