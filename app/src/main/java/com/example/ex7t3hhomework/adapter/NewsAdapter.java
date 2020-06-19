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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>  {

    private ArrayList<News> arrNews;
    private LayoutInflater inflater;

    public NewsAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public ArrayList<News> getArrNews() {
        return arrNews;
    }

    public void setArrNews(ArrayList<News> arrNews) {
        this.arrNews = arrNews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.item,parent,false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bindView(arrNews.get(position));
    }

    @Override
    public int getItemCount() {
        return arrNews == null ? 0 : arrNews.size();
    }


    public class NewsHolder extends RecyclerView.ViewHolder{
        ImageView imgNews;
        TextView txtTitle;
        TextView txtPubDate;
        TextView getTxtDesc;



        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.img_news);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtPubDate = itemView.findViewById(R.id.txt_pubdate);
            getTxtDesc = itemView.findViewById(R.id.txt_desc);


        }

        public void bindView(News item){
            txtTitle.setText(item.getTitle());
            txtPubDate.setText(item.getPubDate());
            getTxtDesc.setText(item.getDesc());

            String imgUrl = item.getImage();
            Glide.with(imgNews).load(imgUrl).centerCrop().into(imgNews);
        }
    }
}
