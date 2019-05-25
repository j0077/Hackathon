package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.ImageModel;

public class ImageAdapter extends BaseAdapter<ImageModel, ImageAdapter.ViewHolder> {

    public ImageAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_image_card, viewGroup, false) ;
        ImageAdapter.ViewHolder viewHolder = new ImageAdapter.ViewHolder(view);

        return viewHolder;
    }

    class ViewHolder extends BaseViewHolder<ImageModel> {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_card_image);
        }

        @Override
        public void bind(ImageModel model) {
            Glide.with(itemView).load(model.imageUri).centerCrop().into(imageView);
        }
    }
}
