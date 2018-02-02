package com.example.dront.testvk.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dront.testvk.R;
import com.example.dront.testvk.response.Items;

public class ViewHolderUserVk extends RecyclerView.ViewHolder {

    private TextView textName;
    private ImageView imagePhoto;

    public ViewHolderUserVk(View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.first_and_last_name);
        imagePhoto = itemView.findViewById(R.id.image_photo);
    }

    public void setView(Items listed) {
        textName.setText(listed.first_name + " " + listed.last_name);

        Uri uri = Uri.parse(listed.photo);
        Glide.with(itemView.getContext())
                .load(uri)
                .into(imagePhoto);
    }
}
