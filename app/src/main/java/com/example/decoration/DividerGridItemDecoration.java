package com.example.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.utils.LogUtil;

import java.util.MissingFormatArgumentException;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS=new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    public DividerGridItemDecoration(Context context){
        TypedArray array=context.obtainStyledAttributes(ATTRS);
        mDivider=array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontalLines(c,parent);
        drawVerticalLines(c,parent);
    }

    private void drawHorizontalLines(Canvas c,RecyclerView parent){
        int childCount=parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
            int left=child.getLeft()-params.leftMargin;
            int right=child.getRight()+params.rightMargin;
            int top=child.getBottom()+params.bottomMargin;
            int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
    private void drawVerticalLines(Canvas c,RecyclerView parent){
        int childCount=parent.getChildCount();
        //todo 在水平的瀑布流中不行
        for(int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
            int left=child.getRight()+params.rightMargin;
            int right=left+mDivider.getIntrinsicHeight();
            int top=child.getTop()-params.topMargin;
            int bottom=child.getBottom()+params.bottomMargin;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
    private int getSpanCount(RecyclerView parent){
        int spanCount=-1;
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            spanCount=((GridLayoutManager) layoutManager).getSpanCount();
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            spanCount=((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
    private boolean isLastColumn(RecyclerView parent,int pos,int spanCount,int childCount){
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            if((pos+1)%spanCount==0){
                return true;
            }
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            int orientation=((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation==StaggeredGridLayoutManager.VERTICAL){
                if((pos+1)%spanCount==0){
                    return true;
                }
            }else{
                //水平的瀑布流
                childCount=childCount-childCount%spanCount;
                if(pos>=childCount){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isLastRaw(RecyclerView parent,int pos,int spanCount,int childCount){
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            childCount=childCount-childCount%spanCount;
            if(pos>=childCount){
                return true;
            }
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            int orientation=((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation==StaggeredGridLayoutManager.VERTICAL){
                childCount=childCount-childCount%spanCount;
                if(pos>=childCount){
                    return true;
                }
            }else{
                if((pos+1)%spanCount==0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int spanCount=getSpanCount(parent);
        int itemPosition=((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        int itemPosition2=((RecyclerView.LayoutParams)view.getLayoutParams()).getViewAdapterPosition();
        int childCount1=parent.getChildCount();
        int childCount2=parent.getAdapter().getItemCount();
        LogUtil.e("childCount1: "+childCount1+"\tchildCount2: "+childCount2);
        LogUtil.e("itemPosition: "+itemPosition+"\titemPosition2: "+itemPosition2);
        if(isLastRaw(parent,itemPosition,spanCount,childCount2)){
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }else if(isLastColumn(parent,itemPosition,spanCount,childCount2)){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else{
            outRect.set(0,0,mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
        }

    }

}