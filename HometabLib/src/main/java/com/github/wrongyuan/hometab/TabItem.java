package com.github.wrongyuan.hometab;

import android.graphics.drawable.Drawable;

/**
 * Created by wrongyuan on 2017/3/3
 */

public class TabItem {
    private CharSequence text;
    private Drawable selectedDrawable;
    private Drawable unselectedDrawable;

    public TabItem(CharSequence text, Drawable selectedDrawable, Drawable unselectedDrawable) {
        this.text = text;
        this.selectedDrawable = selectedDrawable;
        this.unselectedDrawable = unselectedDrawable;
    }

    public CharSequence getText() {
        return text;
    }

    public Drawable getSelectedDrawable() {
        return selectedDrawable;
    }

    public Drawable getUnselectedDrawable() {
        return unselectedDrawable;
    }
}
