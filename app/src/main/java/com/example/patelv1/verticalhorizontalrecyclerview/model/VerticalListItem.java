package com.example.patelv1.verticalhorizontalrecyclerview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertical list item
 * Created by patelv1 on 11/12/17.
 */

public class VerticalListItem {

    private String title;
    private boolean isItemUpdated = false;
    private List<HorizontalListItem> horizontalListItemList = new ArrayList<>();

    public VerticalListItem() {
    }

    public VerticalListItem(String title, List<HorizontalListItem> horizontalListItemList) {
        this.title = title;
        this.horizontalListItemList = horizontalListItemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalListItem> getHorizontalListItemList() {
        return horizontalListItemList;
    }

    public void setHorizontalListItemList(List<HorizontalListItem> horizontalListItemList) {
        this.horizontalListItemList = horizontalListItemList;
    }

    public boolean isItemUpdated() {
        return isItemUpdated;
    }

    public void setItemUpdated(boolean itemUpdated) {
        isItemUpdated = itemUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerticalListItem)) return false;

        VerticalListItem that = (VerticalListItem) o;

        return getTitle().equals(that.getTitle());

    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }
}
