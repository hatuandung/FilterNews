package com.example.ex7t3hhomework.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ex7t3hhomework.R;
import com.example.ex7t3hhomework.adapter.NewsAdapter;
import com.example.ex7t3hhomework.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    TextView txtNoData;
    private RecyclerView lvNews;
    private NewsAdapter adapter;
    private ArrayList<News> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news, container, false);
        lvNews = view.findViewById(R.id.lv_news);
        txtNoData = view.findViewById(R.id.txt_nodata);
        adapter = new NewsAdapter(getLayoutInflater());
        adapter.setArrNews(data);
        lvNews.setAdapter(adapter);

        return view;
    }

    public void setData(List<News> data) {
        this.data.clear();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setArrNews(this.data);
            txtNoData.setVisibility(View.INVISIBLE);
            txtNoData.setVisibility(View.GONE);
        }
    }

}