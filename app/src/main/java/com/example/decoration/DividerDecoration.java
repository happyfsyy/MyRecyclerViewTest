package com.example.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import com.example.myrecyclerviewtest.R;
import com.example.utils.LogUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 参考：https://blog.csdn.net/say_from_wen/article/details/77184666
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int mDividerHeight;

    public DividerDecoration(Context context) {
        mPaint=new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        mDividerHeight=context.getResources().getDimensionPixelSize(R.dimen.divider_height);
    }
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount=parent.getChildCount();
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
//        LogUtil.e("childCount",childCount+"");
        for(int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
            int top=child.getBottom()+params.bottomMargin;
            int bottom=top+mDividerHeight;
            c.drawRect(left,top,right,bottom,mPaint);
        }
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,mDividerHeight);
    }
}
