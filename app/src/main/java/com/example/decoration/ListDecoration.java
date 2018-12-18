package com.example.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS=new int[]{android.R.attr.listDivider};
    public static final int VERTICAL_LIST=RecyclerView.VERTICAL;
    public static final int HORIZONTAL_LIST=RecyclerView.HORIZONTAL;
    private Drawable mDivider;
    private int orientation;

    public ListDecoration(Context context, int orientation){
        TypedArray array=context.obtainStyledAttributes(ATTRS);
        mDivider=array.getDrawable(0);
        array.recycle();
        this.orientation=orientation;
    }

    private void drawVerticalList(Canvas c,RecyclerView parent){
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        int childCount=parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View view=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)view.getLayoutParams();
            int top=view.getBottom()+layoutParams.bottomMargin;
            int bottom=top+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
    private void drawHorizontalList(Canvas c,RecyclerView parent){
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount=parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
            int left=child.getRight()+params.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(orientation==VERTICAL_LIST){
            drawVerticalList(c,parent);
        }else{
            drawHorizontalList(c,parent);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(orientation==VERTICAL_LIST){
            outRect.set(0,0,0,mDivider.getIntrinsicWidth());
        }else{
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
