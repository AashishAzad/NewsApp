package com.vidyakalkendra.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.http.Url;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,image,url;
    private TextView titleTV,subdescTV,contentTV;
    private Button btnNews;
    private ImageView newsPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        image = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV = findViewById(R.id.NewsTitle);
        titleTV.setText(title);
        subdescTV = findViewById(R.id.SubDescription);
        subdescTV.setText(desc);
        contentTV = findViewById(R.id.News);
        contentTV.setText(content);
        newsPic = findViewById(R.id.NewsImage);
        Picasso.get().load(image).into(newsPic);

        btnNews = findViewById(R.id.BtnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}