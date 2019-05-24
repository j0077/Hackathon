package com.example.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(M model);
}
