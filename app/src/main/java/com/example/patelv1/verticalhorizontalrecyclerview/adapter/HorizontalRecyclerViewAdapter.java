package com.example.patelv1.verticalhorizontalrecyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patelv1.verticalhorizontalrecyclerview.R;
import com.example.patelv1.verticalhorizontalrecyclerview.model.HorizontalListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Set horizontal list data in vertical list
 * Created by patelv1 on 11/12/17.
 */

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HorizontalListItem> mHorizontalListItemList = new ArrayList<>();
    private int rowIndex = -1;

    public HorizontalRecyclerViewAdapter() {
    }

    public void setData(@NonNull List<HorizontalListItem> horizontalListItemList) {
        this.mHorizontalListItemList = horizontalListItemList;
        notifyDataSetChanged();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_horizontal_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        HorizontalListItem horizontalListItem = mHorizontalListItemList.get(position);
        itemViewHolder.btnTitle.setText(horizontalListItem.getTitle());
        itemViewHolder.btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(itemViewHolder.itemView, "Clicked on pos : " + getRowIndex() + " : " + holder.getAdapterPosition(), Snackbar.LENGTH_SHORT).show();
                HorizontalListItem horizontalListItem = mHorizontalListItemList.get(holder.getAdapterPosition());
                horizontalListItem.setDisplayProgress(!horizontalListItem.isDisplayProgress());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        if(horizontalListItem.isDisplayProgress()){
            Toast.makeText(itemViewHolder.itemView.getContext(), "Update on column pos : " + position, Toast.LENGTH_SHORT).show();
            itemViewHolder.pbView.setVisibility(View.VISIBLE);
            itemViewHolder.ivView.setVisibility(View.GONE);
        } else {
            itemViewHolder.pbView.setVisibility(View.GONE);
            itemViewHolder.ivView.setVisibility(View.VISIBLE);
        }
    }

    public void updateViewAtPosition(int columnPosition){
        if(mHorizontalListItemList.isEmpty() || columnPosition == -1 || columnPosition > mHorizontalListItemList.size()){
            return;
        }
        HorizontalListItem horizontalListItem = mHorizontalListItemList.get(columnPosition);
        horizontalListItem.setDisplayProgress(true);
        notifyItemChanged(columnPosition);
    }

    @Override
    public int getItemCount() {
        return mHorizontalListItemList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{

        private Button btnTitle;
        private ImageView ivView;
        private ProgressBar pbView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            btnTitle = itemView.findViewById(R.id.layout_recycler_horizontal_list_item_btn_title);
            ivView = itemView.findViewById(R.id.layout_recycler_horizontal_list_item_iv_view);
            pbView = itemView.findViewById(R.id.layout_recycler_horizontal_list_item_pb_view);
        }
    }
}
