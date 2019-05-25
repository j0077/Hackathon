package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.ReviewModel;

public class ReviewAdapter extends BaseAdapter<ReviewModel, ReviewAdapter.ViewHolder> {

    ClickListener<ReviewModel> clickListener;

    public ReviewAdapter(ClickListener<ReviewModel> clickListener) {
        super();
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_review_card, viewGroup, false) ;
        ReviewAdapter.ViewHolder viewHolder = new ReviewAdapter.ViewHolder(view, clickListener);

        return viewHolder;
    }

    class ViewHolder extends BaseViewHolder<ReviewModel> {
        ClickListener<ReviewModel> clickListener;
        ImageView imageView;

        public ViewHolder(View view, ClickListener<ReviewModel> clickListener) {
            super(view);
            this.clickListener = clickListener;
            imageView = view.findViewById(R.id.review_card_image);
        }

        @Override
        public void bind(final ReviewModel model) {
            if (model.images != null && model.images.size() > 0) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(model);
                    }
                });
                Glide.with(itemView).load(model.images.get(0)).centerCrop().into(imageView);
            }
        }
    }

}
