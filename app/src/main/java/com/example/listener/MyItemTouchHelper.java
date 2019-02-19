package com.example.listener;

import android.graphics.Color;

import com.example.adapter.SimpleAdapter;
import com.example.utils.LogUtil;

import java.lang.reflect.WildcardType;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private SimpleAdapter adapter;

    public MyItemTouchHelper(SimpleAdapter adapter){
        this.adapter=adapter;
    }


     //通过返回值来设置是否处理某次拖拽或者滑动事件
     //dragFlags是拖拽标志
     //swipeFlags是滑动标志
     //swipeFlags设置为0，是暂时不考虑滑动相关操作
     //getMovementFlags()用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向，有两种情况：
     //如果是列表类型的RecyclerView，拖拽只有UP、Down两个方向
     //如果是网格类型的则有UP、DOWN、LEFT、RIGHT四个方向
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
            int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            int swipeFlags=0;
            //todo 这里可以改成makeFlag(idle,dragFlags)|makeFlag(drag,dragFlags)
            return makeMovementFlags(dragFlags,swipeFlags);
        }else{
            int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            int swipeFlags=ItemTouchHelper.START|ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    //当长按并且进入拖拽状态时，拖拽的过程中不断的回调此方法
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int fromPosition=viewHolder.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        LogUtil.e("fromPosition:"+fromPosition+"\ttoPosition:"+toPosition);
        //对应某些需求，某一个item不能拖拽
        if(toPosition==0){
            return false;
        }
        if(fromPosition<toPosition){
            for(int i=fromPosition;i<toPosition;i++){
                //通过你传入的adapter得到你的数据，并进行交换
                Collections.swap(adapter.getDataList(),i,i+1);
            }
        }else{
            for(int i=fromPosition;i>toPosition;i--){
                Collections.swap(adapter.getDataList(),i,i-1);
            }
        }
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    //滑动删除的回调
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int adapterPosition=viewHolder.getAdapterPosition();
        adapter.notifyItemRemoved(adapterPosition);
        adapter.getDataList().remove(adapterPosition);
    }

    //当长按item刚开始拖拽的时候调用
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    //当完成拖拽手指松开的时候调用
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //给已经完成拖拽的item恢复开始的背景
        //这里我们设置的颜色尽量和你item在xml中设置的颜色保持一致
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean isLongPressDragEnabled() {
//        return false;
        return super.isLongPressDragEnabled();
    }
}
