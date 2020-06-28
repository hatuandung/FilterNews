package com.example.ex7t3hhomework.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.ex7t3hhomework.R;

public class SavedAdapter extends BaseAdapter {

    SavedListener listener;

    public void setListener(SavedListener listener) {
        this.listener = listener;
    }

    public SavedAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected void setListenerFragment(BaseHolder holder, int position) {
        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemSavedClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(holder.itemView.getContext(), holder.itemView);
                    menu.inflate(R.menu.saved_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                case R.id.menu_saved_delete:
                                    listener.onItemDeleteLongClicked(position);
                                    break;
                                case R.id.menu_saved_favorite:
                                    listener.inItemFavoriteLongClick(position);
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

    public interface SavedListener {
        void onItemSavedClicked(int position);

        void onItemDeleteLongClicked(int position);

        void inItemFavoriteLongClick(int position);
    }
}
