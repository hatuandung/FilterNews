package com.example.ex7t3hhomework.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.ex7t3hhomework.R;

public class FavoriteAdapter extends BaseAdapter {

    private FavoriteListener listener;

    public void setListener(FavoriteListener listener) {
        this.listener = listener;
    }

    public FavoriteAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected void setListenerFragment(final BaseHolder holder, final int position) {
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemFavoriteClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(holder.itemView.getContext(), holder.itemView);
                    menu.inflate(R.menu.favorite_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_favorite_delete:
                                    listener.onItemFavoriteLongClicked(position);
                                    break;
                            }
                            return true;
                        }
                    });
                    menu.show();
                    return true;
                }
            });
        }


    }

    public interface FavoriteListener {
        void onItemFavoriteClicked(int position);

        void onItemFavoriteLongClicked(int position);
    }
}
