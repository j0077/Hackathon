package com.example.myapplication;

public class ReviewRecyclerItem {

    private int image;
    private String review_id;

    public ReviewRecyclerItem(int Image, String review_id){
        this.image = image;
        this.review_id = review_id;
    }

    public int getImage(){
        return image;
    }

    public String getReview_id(){
        return review_id;
    }
}
