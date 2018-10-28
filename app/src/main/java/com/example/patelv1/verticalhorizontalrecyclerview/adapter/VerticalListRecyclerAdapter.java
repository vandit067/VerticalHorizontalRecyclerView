package com.example.patelv1.verticalhorizontalrecyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patelv1.verticalhorizontalrecyclerview.R;
import com.example.patelv1.verticalhorizontalrecyclerview.model.VerticalListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Vertical recycler adapter to set vertical list item.
 * Created by patelv1 on 11/12/17.
 */

public class VerticalListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VerticalListItem> mVerticalListItemList = new ArrayList<>();

    public VerticalListRecyclerAdapter(@NonNull List<VerticalListItem> verticalListItemList) {
        this.mVerticalListItemList = verticalListItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        VerticalListItem verticalListItem = mVerticalListItemList.get(position);
        itemViewHolder.tvTitle.setText(verticalListItem.getTitle());
        ((ItemViewHolder) holder).horizontalRecyclerViewAdapter.setData(verticalListItem.getHorizontalListItemList());
        ((ItemViewHolder) holder).horizontalRecyclerViewAdapter.setRowIndex(position);
        if(verticalListItem.isItemUpdated()){
            Toast.makeText(itemViewHolder.itemView.getContext(), "Update item in year : " + verticalListItem.getTitle(), Toast.LENGTH_SHORT).show();
            Random random = new Random();
            int columnPosition = random.nextInt(verticalListItem.getHorizontalListItemList().size());
            ((ItemViewHolder) holder).horizontalRecyclerViewAdapter.updateViewAtPosition(columnPosition);
        }
    }

    @Override
    public int getItemCount() {
        return mVerticalListItemList.size();
    }

    public void updateViewAtPosition(int rowPosition){
        if(mVerticalListItemList.isEmpty() || rowPosition == -1 || rowPosition > mVerticalListItemList.size()){
            return;
        }
        VerticalListItem verticalListItem = mVerticalListItemList.get(rowPosition);
        verticalListItem.setItemUpdated(true);
        notifyItemChanged(rowPosition);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private RecyclerView rvHorizontalList;
        private HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.layout_recycler_item_tv_title);
            rvHorizontalList = itemView.findViewById(R.id.layout_recycler_item_rv_horizontal_list);
            rvHorizontalList.setHasFixedSize(false);
            rvHorizontalList.setItemAnimator(new DefaultItemAnimator());
            rvHorizontalList.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter();
            rvHorizontalList.setAdapter(horizontalRecyclerViewAdapter);
        }
    }
}
