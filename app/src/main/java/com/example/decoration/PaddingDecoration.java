package com.example.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaddingDecoration extends RecyclerView.ItemDecoration {
    private int leftPadding;
    private int rightPadding;
    private int topPadding;
    private int bottomPadding;

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }
    public void setRightPadding(int rightPadding) {
        this.rightPadding = rightPadding;
    }
    public void setTopPadding(int topPadding) {
        this.topPadding = topPadding;
    }
    public void setBottomPadding(int bottomPadding) {
        this.bottomPadding = bottomPadding;
    }
    public void setPadding(int padding) {
        this.leftPadding = padding;
        this.rightPadding=padding;
        this.topPadding=padding;
        this.bottomPadding=padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(leftPadding,topPadding,rightPadding,bottomPadding);
    }
}
