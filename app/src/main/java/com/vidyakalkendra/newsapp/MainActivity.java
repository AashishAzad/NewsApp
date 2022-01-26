package com.vidyakalkendra.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsTypesAdapter.NewsTypeClickInterface{

    private RecyclerView news, newsType;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<NewsTypesModal> newsTypesModals;
    private NewsTypesAdapter newsTypesAdapter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsType = findViewById(R.id.NewsTypes);
        news = findViewById(R.id.NewsData);
        loadingPB = findViewById(R.id.PBLoading);
        articlesArrayList = new ArrayList<>();
        newsTypesModals = new ArrayList<>();
        newsAdapter = new NewsAdapter(articlesArrayList,this);
        newsTypesAdapter = new NewsTypesAdapter(newsTypesModals,this,this::onNewsTypeClick);
        news.setLayoutManager(new LinearLayoutManager(this));
        news.setAdapter(newsAdapter);
        newsType.setAdapter(newsTypesAdapter);
        getNewsType();
        getNews("All");
        newsAdapter.notifyDataSetChanged();

    }

    private void getNewsType(){
        newsTypesModals.add(new NewsTypesModal("All","https://media.istockphoto.com/photos/blue-world-map-background-global-business-news-and-media-finance-and-picture-id1300113567?b=1&k=20&m=1300113567&s=170667a&w=0&h=Rz604QF4tSjJUGQJTh_rwNOeBi2Ob9vpzSzpNKIkUtY="));
        newsTypesModals.add(new NewsTypesModal("Technology","https://media.istockphoto.com/photos/data-scientists-male-programmer-using-laptop-analyzing-and-developing-picture-id1295900106?b=1&k=20&m=1295900106&s=170667a&w=0&h=kQ2UWilU4Bild5aP03CaF65gMbSI-chG--7dd2oS8GM="));
        newsTypesModals.add(new NewsTypesModal("Science","https://media.istockphoto.com/photos/interior-of-chemistry-classroom-picture-id947838384?b=1&k=20&m=947838384&s=170667a&w=0&h=yEQ2NfAmLtdofD4KPHe972F_bGn1ch_5zWNjnQTifEI="));
        newsTypesModals.add(new NewsTypesModal("Sports","https://images.unsplash.com/photo-1593111774642-a746f5006b7b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8c3BvcnRzfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        newsTypesModals.add(new NewsTypesModal("General","https://media.istockphoto.com/photos/cryptoart-display-in-art-gallery-picture-id1307112137?b=1&k=20&m=1307112137&s=170667a&w=0&h=mOYzSSKNchyiGXN0p8opkHezYiNtwXm8Y1Bdk3pWIGA="));
        newsTypesModals.add(new NewsTypesModal("Business","https://media.istockphoto.com/photos/cheerful-woman-and-her-business-plan-picture-id1282110628?b=1&k=20&m=1282110628&s=170667a&w=0&h=NDDPlHUlIblxtIa6J0NIv81PGbG-G6GMPTirGuKEV7I="));
        newsTypesModals.add(new NewsTypesModal("Entertainment","https://media.istockphoto.com/photos/stage-for-live-concert-online-transmission-business-concept-for-a-picture-id1227545308?b=1&k=20&m=1227545308&s=170667a&w=0&h=mPwXII61ZZ7GrAmy-KLcfV7FX2NufADB2iUk1kGHBo4="));
        newsTypesModals.add(new NewsTypesModal("Health","https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        newsTypesAdapter.notifyDataSetChanged();
    }

    private void getNews(String newsTypes){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String newsTypeUrl = "https://newsapi.org/v2/top-headlines?country=in&category="+newsTypes+"&apikey=109358310bf14ce5b0ec3cf60aa41a61";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=109358310bf14ce5b0ec3cf60aa41a61";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        Call<NewsModal> call;
        if(newsTypes.equals("All")){
             call = newsAPI.getAllNews(url);
        }
        else{
             call = newsAPI.getNewsByCategory(newsTypeUrl);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for (int i = 0; i < articles.size() ; i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to access News", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onNewsTypeClick(int position) {
        String newsType = newsTypesModals.get(position).getNewsTypes();
        getNews(newsType);

    }
}