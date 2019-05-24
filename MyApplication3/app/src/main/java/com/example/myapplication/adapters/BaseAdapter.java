package com.example.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<M, VH extends BaseViewHolder<M>> extends RecyclerView.Adapter<VH> {
    protected ArrayList<M> dataList;

    public BaseAdapter() {
        dataList = new ArrayList<>();
    }

    public BaseAdapter(ArrayList<M> initialList) {
        dataList = initialList;
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void addItem(M item) {
        dataList.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.bind(dataList.get(i));
    }
}
