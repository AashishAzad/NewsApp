package com.vidyakalkendra.newsapp;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class NewsTypesAdapter extends RecyclerView.Adapter<NewsTypesAdapter.ViewHolder> {
    private ArrayList<NewsTypesModal> newsTypesModals;
    private Context context;
    private NewsTypeClickInterface ntci;

    public NewsTypesAdapter(ArrayList<NewsTypesModal> newsTypesModals, Context context, NewsTypeClickInterface ntci) {
        this.newsTypesModals = newsTypesModals;
        this.context = context;
        this.ntci = ntci;
    }

    @NonNull
    @Override
    public NewsTypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_types,parent,false);
        return new NewsTypesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsTypesAdapter.ViewHolder holder, int position) {
        NewsTypesModal newsTypesModal = newsTypesModals.get(position);
        holder.newsTypesHeading.setText(newsTypesModal.getNewsTypes());
        Picasso.get().load(newsTypesModal.getNewsTypesImageUrl()).into(holder.newsTypesImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntci.onNewsTypeClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsTypesModals.size();
    }

    public interface NewsTypeClickInterface{
        void onNewsTypeClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newsTypesHeading;
        private ImageView newsTypesImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTypesHeading = itemView.findViewById(R.id.NewsTypeHeading);
            newsTypesImage = itemView.findViewById(R.id.newsPics);
        }
    }
}
