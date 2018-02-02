package com.example.dront.testvk.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dront.testvk.R;
import com.example.dront.testvk.response.MainResponseVk;

import java.util.List;

public class RecyclerViewAdapterUserVk extends RecyclerView.Adapter<ViewHolderUserVk>{
    private MainResponseVk mainResponseVk;

    public void addView(MainResponseVk listeds) {
        this.mainResponseVk = listeds;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderUserVk onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolderUserVk(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderUserVk holder, int position) {
        holder.setView(mainResponseVk.response.items.get(position));
    }

    @Override
    public int getItemCount() {
        return mainResponseVk.response.items.size();
    }
}
