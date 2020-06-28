package com.example.ex7t3hhomework.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex7t3hhomework.R;
import com.example.ex7t3hhomework.activity.WebViewActivity;
import com.example.ex7t3hhomework.adapter.FavoriteAdapter;
import com.example.ex7t3hhomework.dao.AppDatabase;
import com.example.ex7t3hhomework.model.News;

import java.util.ArrayList;

public class FavoriteFragment extends BaseFragment implements FavoriteAdapter.FavoriteListener {

    private ArrayList<News> data = new ArrayList<>();
    private RecyclerView rvFavorite;
    private FavoriteAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvFavorite = findViewByID(R.id.rv_favorite);
        adapter = new FavoriteAdapter(getLayoutInflater());

        rvFavorite.setAdapter(adapter);
        adapter.setArrNews(data);
        adapter.setListener(this);
        getData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorite;
    }

    @Override
    public String getTitle() {
        return "FAVORITE";
    }

    public void getData(){
        data.clear();
        //data.addAll(AppDatabase.getInstance(getContext()).getNewsDao().getAll());
        data.addAll(AppDatabase.getInstance(getContext()).getNewsDao().getFavorite());
        if (adapter != null){
            adapter.setArrNews(data);
        }
    }

    @Override
    public void onItemFavoriteClicked(int position) {
        Intent intent = WebViewActivity.getInstance(getContext(), data.get(position).getUrl());
        Log.e( "Click: ", data.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void onItemFavoriteLongClicked(int position) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setMessage("Bạn có chắn chắn muốn xóa")
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        News news = data.get(position);
                        news.setFavorite(0);
                        //AppDatabase.getInstance(getContext()).getNewsDao().delete(data.get(position));
                        AppDatabase.getInstance(getContext()).getNewsDao().update(news);
                        getData();
                        Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
    }
}
