package com.example.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.myrecyclerviewtest.R;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectionDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SelectionDecoration";
    private List<String> dataList;
    private DecorationCallback callback;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int alignBottom;
    private Paint.FontMetrics fontMetrics;

    public SelectionDecoration(List<String> dataList, Context context,DecorationCallback callback){
        Resources resources=context.getResources();
        this.dataList=dataList;
        this.callback=callback;

        paint=new Paint();
        paint.setColor(Color.RED);

        textPaint=new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DisplayUtil.sp2px(14));
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics=textPaint.getFontMetrics();

        topGap=resources.getDimensionPixelSize(R.dimen.selection_top);
        alignBottom=resources.getDimensionPixelSize(R.dimen.selection_alignBottom);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LogUtil.e(TAG,"onDraw()");
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        int childCount=parent.getChildCount();
        LogUtil.e(TAG,"childCount:"+childCount);
        for(int i=0;i<childCount;i++){
            View view=parent.getChildAt(i);
//            int position1=((RecyclerView.LayoutParams)view.getLayoutParams()).getViewAdapterPosition();
            int position=parent.getChildAdapterPosition(view);
//            LogUtil.e("pos="+position+"\tpos1="+position1);
            String groupId=callback.getGroupId(position);
            if(groupId.equals("-1")) return;
            String textLine=callback.getGroupFirstLine(position);
            if(textLine.equals("")){
                float top=view.getTop();
                float bottom=view.getTop();
                c.drawRect(left,top,right,bottom,paint);
                return;
            }else{
                //todo 这里的position==0条件可以省略
                if(isFirstInGroup(position)){
                    float top=view.getTop()-topGap;
                    float bottom=view.getTop();
//                    c.drawRect(left,top,right,bottom,paint);
//                    c.drawText(textLine,left,bottom-fontMetrics.descent,textPaint);
                }
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int pos=parent.getChildAdapterPosition(view);
        String groupId=callback.getGroupId(pos);
        if(groupId.equals("-1")) return;
        if(pos==0||isFirstInGroup(pos)){
            outRect.top=topGap;
//            if(dataList.get(pos).equals("")){
//                //todo 这个if条件有什么用，删掉试试
//                outRect.top=0;
//            }
        }else{
            outRect.top=0;
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        LogUtil.e(TAG,"onDrawOver()");
        int itemCount=state.getItemCount();
        int childCount=parent.getChildCount();
//        LogUtil.e("itemCount="+itemCount+"\tchildCount="+childCount);
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        String preGroupId="";
        String groupId="-1";
        for(int i=0;i<childCount;i++){
            View view=parent.getChildAt(i);
            int position=parent.getChildAdapterPosition(view);
            if(i==0){
//                LogUtil.e("i="+i+"\tposition="+position);
//                LogUtil.e("i="+i+"\tview.getTop="+view.getTop());
            }

            preGroupId=groupId;
            groupId=callback.getGroupId(position);

            if(groupId.equals(preGroupId))
                continue;
            String textLine=callback.getGroupFirstLine(position);
            int viewBottom=view.getBottom();
            LogUtil.e("i="+i+"\tviewBottom="+viewBottom);
            float textY=Math.max(topGap,view.getTop());

            if(position+1<itemCount){
                String nextGroupId=callback.getGroupId(position+1);
                if(!nextGroupId.equals(groupId)&&viewBottom<textY){
//                    LogUtil.e("position="+position+"\ti="+i);
                    textY=viewBottom;
//                    LogUtil.e("textY="+textY+"\talignBottom="+alignBottom+"\ttextY-alignBottom="+(textY-alignBottom));
                }
            }
            c.drawRect(left,textY-topGap,right,textY,paint);
            c.drawText(textLine,left,textY-alignBottom,textPaint);
        }
    }

    private boolean isFirstInGroup(int pos){
        if(pos==0) return true;
        String preGroupId=callback.getGroupId(pos-1);
        String groupID=callback.getGroupId(pos);
        if(preGroupId.equals(groupID)){
            return false;
        }else{
            return true;
        }
    }

    public interface DecorationCallback{
        String getGroupId(int pos);
        String getGroupFirstLine(int pos);
    }
}
