package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.RestaurantModel;

public class RestaurantAdapter extends BaseAdapter<RestaurantModel, RestaurantAdapter.ViewHolder> {
    public RestaurantAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_restaurant_card, viewGroup, false) ;
        ViewHolder viewHolder = new ViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(this.dataList.get(i));
    }

    public class ViewHolder extends BaseViewHolder<RestaurantModel> {
        ImageView imageView;
        TextView titleView;
        TextView addressView;
        TextView distanceView;
        TextView numOfReviewsView;
        TextView localRateView;
        TextView travelerRateView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.restaurant_card_image_view);
            titleView = view.findViewById(R.id.restaurant_card_title);
            addressView = view.findViewById(R.id.restaurant_card_address);
            distanceView = view.findViewById(R.id.restaurant_card_distance);
            numOfReviewsView = view.findViewById(R.id.restaurant_card_review);
            localRateView = view.findViewById(R.id.restaurant_card_local_rate);
            travelerRateView = view.findViewById(R.id.restaurant_card_traveler_rate);
        }

        @Override
        public void bind(RestaurantModel model) {
            titleView.setText(model.name);
            addressView.setText(model.address);
            distanceView.setText("0.1km 이내");
            numOfReviewsView.setText(String.valueOf(model.numOfReviews));
            localRateView.setText(String.valueOf(model.rateLocal));
            travelerRateView.setText(String.valueOf(model.rateTraveler));

            Glide.with(itemView)
                    .load(model.imageUri)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
