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
    View.OnClickListener clickListener;

    public RestaurantAdapter(View.OnClickListener clickListener) {
        super();
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_restaurant_card, viewGroup, false) ;
        ViewHolder viewHolder = new ViewHolder(view, clickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(this.dataList.get(i));
    }

    public class ViewHolder extends BaseViewHolder<RestaurantModel> {
        View root;
        ImageView imageView;
        TextView titleView;
        TextView addressView;
        TextView distanceView;
        TextView numOfReviewsView;
        TextView localRateView;
        TextView travelerRateView;

        View.OnClickListener clickListener;

        public ViewHolder(View view, View.OnClickListener clickListener) {
            super(view);
            root = view.findViewById(R.id.restaurant_card_root);
            imageView = view.findViewById(R.id.restaurant_card_image_view);
            titleView = view.findViewById(R.id.restaurant_card_title);
            addressView = view.findViewById(R.id.restaurant_card_address);
            distanceView = view.findViewById(R.id.restaurant_card_distance);
            numOfReviewsView = view.findViewById(R.id.restaurant_card_review);
            localRateView = view.findViewById(R.id.restaurant_card_local_rate);
            travelerRateView = view.findViewById(R.id.restaurant_card_traveler_rate);

            this.clickListener = clickListener;
        }

        @Override
        public void bind(RestaurantModel model) {
            root.setOnClickListener(clickListener);

            titleView.setText(model.name);
            addressView.setText(model.address);
            distanceView.setText("0.1km 이내");

            numOfReviewsView.setText(String.valueOf(model.numOfReviews));
            localRateView.setText(String.format("%.1f", model.rateLocal));
            travelerRateView.setText(String.format("%.1f", model.rateTraveler));

            Glide.with(itemView)
                    .load(model.imageUri)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
