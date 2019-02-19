package com.example.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
    private RecyclerView recyclerView;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    public ItemTouchHelperGestureListener(RecyclerView recyclerView,OnRecyclerItemClickListener onRecyclerItemClickListener){
        this.recyclerView=recyclerView;
        this.onRecyclerItemClickListener=onRecyclerItemClickListener;
    }

    //一次单独的轻触抬起手指操作，就是普通的点击事件
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        View childViewUnder=recyclerView.findChildViewUnder(e.getX(),e.getY());
        if(childViewUnder!=null){
            RecyclerView.ViewHolder childViewHolder=recyclerView.getChildViewHolder(childViewUnder);
            onRecyclerItemClickListener.OnItemClick(childViewHolder);
        }
        return true;
    }

    //长按屏幕超过一定时长就会触发，就是长按事件
    @Override
    public void onLongPress(MotionEvent e) {
        View childViewUnder=recyclerView.findChildViewUnder(e.getX(),e.getY());
        if(childViewUnder!=null){
            RecyclerView.ViewHolder childViewHolder=recyclerView.getChildViewHolder(childViewUnder);
            onRecyclerItemClickListener.onLongClick(childViewHolder);
        }
    }
}
