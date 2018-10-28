package com.example.patelv1.verticalhorizontalrecyclerview.model;

import android.widget.ImageView;

/**
 * Horizontal list item
 * Created by patelv1 on 11/12/17.
 */

public class HorizontalListItem {
    private String title;
    private boolean isDisplayProgress = false;

    public HorizontalListItem() {
    }

    public HorizontalListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDisplayProgress() {
        return isDisplayProgress;
    }

    public void setDisplayProgress(boolean displayProgress) {
        isDisplayProgress = displayProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorizontalListItem)) return false;

        HorizontalListItem that = (HorizontalListItem) o;

        return getTitle().equals(that.getTitle());

    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }
}
