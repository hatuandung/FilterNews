package com.example.ex7t3hhomework.adapter;

import android.view.LayoutInflater;
import android.view.View;

public class NewsAdapter extends BaseAdapter {

    private NewsListener listener;


    public void setListener(NewsListener listener) {
        this.listener = listener;
    }

    public NewsAdapter(LayoutInflater inflater) {
        super(inflater);
    }




    @Override
    protected void setListenerFragment(BaseHolder holder,final int position) {

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemNewsClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemNewsLongClicked(position);
                    return true;
                }
            });
        }
    }



    public interface NewsListener {
        void onItemNewsClicked(int position);

        void onItemNewsLongClicked(int position);
    }
}
