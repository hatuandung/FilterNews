package com.example.ex7t3hhomework.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex7t3hhomework.R;
import com.example.ex7t3hhomework.activity.MainActivity;
import com.example.ex7t3hhomework.activity.WebViewActivity;
import com.example.ex7t3hhomework.adapter.NewsAdapter;
import com.example.ex7t3hhomework.dao.AppDatabase;
import com.example.ex7t3hhomework.file.DownloadAsync;
import com.example.ex7t3hhomework.file.FileManager;
import com.example.ex7t3hhomework.model.News;
import com.example.ex7t3hhomework.utils.PermissionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment implements NewsAdapter.NewsListener{
    TextView txtNoData;
    private RecyclerView lvNews;
    private NewsAdapter adapter;
    private ArrayList<News> data = new ArrayList<>();
    private int idUrl;

    FileManager fileManager;
    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private  File[] files;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    public String getTitle() {
        return "NEWS";
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lvNews = findViewByID(R.id.lv_news);
        txtNoData = findViewByID(R.id.txt_nodata);

        adapter = new NewsAdapter(getLayoutInflater());
        lvNews.setAdapter(adapter);
        adapter.setArrNews(data);

        adapter.setListener(this);
    }

    public void setData(List<News> data) {
        this.data.clear();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setArrNews(this.data);
            txtNoData.setVisibility(View.GONE);

        }
    }

    @Override
    public void onItemNewsClicked(int posititon) {
        Intent intent = WebViewActivity.getInstance(getContext(), data.get(posititon).getUrl());
        Log.e( "Click: ", data.get(posititon).getUrl());
        startActivity(intent);
    }

    @Override
    public void onItemNewsLongClicked(int position) {
        idUrl = position;
        try {
            AppDatabase.getInstance(getContext()).getNewsDao().insert(data.get(position));
            MainActivity act = (MainActivity) getActivity();
            act.getSavedFragment().getData();
            download(position);
            Toast.makeText(getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
        } catch (Exception ex){
            Toast.makeText(getContext(), "Đã tồn tại", Toast.LENGTH_SHORT).show();
        }

    }

    public void download (int position){
        if (PermissionUtils.checkPermissions(getContext(), PERMISSIONS)){
            fileManager = new FileManager();
            files = fileManager.getFile(fileManager.getRootPath());
            Log.e("download: ", String.valueOf(files));
            String link = data.get(position).getUrl();
            new DownloadAsync(getContext()).execute(link);
        }else {
            PermissionUtils.requestPermissons(getActivity(),PERMISSIONS,0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.checkPermissions(getContext(), permissions)) {
            download(idUrl);
        } else {
            PermissionUtils.requestPermissons(getActivity(), PERMISSIONS, 0);
        }
    }
}