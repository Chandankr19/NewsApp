package com.tecdroid.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", (CharSequence) modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.time.setText("Published At:- " +modelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(modelClassArrayList.get(position).getAuthor());
        holder.heading.setText(modelClassArrayList.get(position).getTitle());
        holder.content.setText(modelClassArrayList.get(position).getDescription());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, content, author, time, link;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.mainHeading);
            content = itemView.findViewById(R.id.content);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageView);
            link = itemView.findViewById(R.id.link);
        }
    }
}
