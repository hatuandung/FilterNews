package com.example.ex7t3hhomework.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex7t3hhomework.R;
import com.example.ex7t3hhomework.activity.MainActivity;
import com.example.ex7t3hhomework.activity.WebViewActivity;
import com.example.ex7t3hhomework.adapter.BaseAdapter;
import com.example.ex7t3hhomework.adapter.SavedAdapter;
import com.example.ex7t3hhomework.dao.AppDatabase;
import com.example.ex7t3hhomework.model.News;

import java.util.ArrayList;

public class SavedFragment extends BaseFragment implements SavedAdapter.SavedListener {
    private ArrayList<News> data = new ArrayList<>();
    private RecyclerView rvSaved;
    private SavedAdapter adapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvSaved = findViewByID(R.id.rv_saved);
        adapter = new SavedAdapter(getLayoutInflater());

        rvSaved.setAdapter(adapter);
        adapter.setArrNews(data);
        adapter.setListener(this);
        getData();
    }

    public void getData(){
        data.clear();
        data.addAll(AppDatabase.getInstance(getContext()).getNewsDao().getAll());
        if (adapter != null) {
            adapter.setArrNews(data);
        }
    }

    @Override
    public void onItemSavedClicked(int position) {
        Intent intent = WebViewActivity.getInstance(getContext(), data.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void onItemDeleteLongClicked(int position) {
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

                        AppDatabase.getInstance(getContext()).getNewsDao().delete(data.get(position));

                        getData();
                        MainActivity act = (MainActivity)getActivity();
                        act.getFavoriteFragment().getData();
                        Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public void inItemFavoriteLongClick(int position) {
        try {
            News news = data.get(position);
            news.setFavorite(1);

            AppDatabase.getInstance(getContext()).getNewsDao().update(news);
            MainActivity act = (MainActivity) getActivity();
            act.getFavoriteFragment().getData();
            Toast.makeText(getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(getContext(), "Đã tồn tại", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_saved;
    }

    @Override
    public String getTitle() {
        return "SAVED";
    }
}
