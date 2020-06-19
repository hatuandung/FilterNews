package com.example.ex7t3hhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex7t3hhomework.adapter.NewsAdapter;
import com.example.ex7t3hhomework.adapter.NewsPagerAdapter;
import com.example.ex7t3hhomework.api.ApiBuilder;
import com.example.ex7t3hhomework.fragment.FavoriteFragment;
import com.example.ex7t3hhomework.fragment.NewsFragment;
import com.example.ex7t3hhomework.fragment.SavedFragment;
import com.example.ex7t3hhomework.model.News;
import com.example.ex7t3hhomework.model.NewsResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<NewsResponse>, SearchView.OnQueryTextListener, ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private TabLayout tab;
    private NewsPagerAdapter adpter;
    private NewsFragment newsFragment = new NewsFragment();
    private FavoriteFragment favoriteFragment = new FavoriteFragment();
    private SavedFragment savedFragment = new SavedFragment();
    //private TextView txtNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


    }

    private void initViews() {
        //txtNoData = findViewById(R.id.txt_nodata);
        pager = findViewById(R.id.pager);
        tab = findViewById(R.id.tab);
        tab.setupWithViewPager(pager);
        adpter = new NewsPagerAdapter(getSupportFragmentManager(), newsFragment, favoriteFragment, savedFragment);
        pager.setAdapter(adpter);
        pager.addOnPageChangeListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView search = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        search.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        NewsResponse newsResponse = response.body();
        ArrayList<News> data = newsResponse.getArrNews();

        newsFragment.setData(data);
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        Toast.makeText(this, "Search fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        pager.setCurrentItem(0);
        ApiBuilder.getInstance().getNews(query, "e09adc94a8c84d32bac60c934df8169c").enqueue(this);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}