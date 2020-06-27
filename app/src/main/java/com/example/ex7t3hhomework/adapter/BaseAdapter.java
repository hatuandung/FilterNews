package com.example.ex7t3hhomework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ex7t3hhomework.R;
import com.example.ex7t3hhomework.model.News;

import java.util.ArrayList;
import java.util.zip.Inflater;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {
    private LayoutInflater inflater;
    private ArrayList<News> arrNews;

    public void setArrNews(ArrayList<News> arrNews) {
        this.arrNews = arrNews;
        notifyDataSetChanged();
    }

    public BaseAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    protected abstract void setListenerFragment(BaseHolder holder, int position);

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item, parent, false);
        return new BaseHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bindView(arrNews.get(position));
        setListenerFragment(holder, position);
    }

    @Override
    public int getItemCount() {
        return arrNews == null ? 0 : arrNews.size();
    }


    class BaseHolder extends RecyclerView.ViewHolder{

        private ImageView imgNews;
        private TextView txtTitle;
        private TextView txtPubDate;
        private TextView txtDesc;

        public BaseHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.img_news);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtPubDate = itemView.findViewById(R.id.txt_pubdate);
            txtDesc = itemView.findViewById(R.id.txt_desc);

        }

        public void bindView(News item){
            txtTitle.setText(item.getTitle());
            txtPubDate.setText(item.getTitle());
            txtDesc.setText(item.getDesc());
            String imgUrl = item.getImage();
            Glide.with(imgNews).load(imgUrl).centerCrop().into(imgNews);
        }
    }
}
