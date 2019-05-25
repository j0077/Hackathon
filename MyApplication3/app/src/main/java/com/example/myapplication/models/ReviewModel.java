package com.example.myapplication.models;

import android.util.Log;

import com.example.myapplication.adapters.ReviewAdapter;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class ReviewModel {
    public String id;
    public String name;
    public ArrayList<String> images;
    public String restarunantId;
    public String text;
    public long rate;
    public boolean vip;

    public ReviewModel() {
        images = new ArrayList<>();
    }

    public ReviewModel(DocumentSnapshot document) {
        id = document.getId();
        name = (String)document.get("name");
        images = (ArrayList<String>)document.get("images");
        restarunantId = (String)document.get("restaruantId");
        text = (String)document.get("text");
        rate = (long)document.get("rate");
        vip = (boolean)document.get("vip");
    }
}
