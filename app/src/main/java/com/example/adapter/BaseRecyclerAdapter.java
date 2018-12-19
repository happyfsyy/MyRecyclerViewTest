package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;

import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER_TYPE=1;
    public static final int NORMAL_TYPE=2;
    protected List<T> list;
    private View mHeaderView;

    private OnItemClickListener onItemClickListener;



    class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            if(itemView==mHeaderView) return;
        }
    }
    public BaseRecyclerAdapter(List<T> list){
        this.list=list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void setHeaderView(View mHeaderView){
        this.mHeaderView=mHeaderView;
        notifyItemInserted(0);
    }

    public View getHeaderView(){
        return mHeaderView;
    }
    @Override
    public int getItemViewType(int position) {
        if(mHeaderView==null) return NORMAL_TYPE;
        if(position==0) return HEADER_TYPE;
        return NORMAL_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==HEADER_TYPE) return new BaseViewHolder(mHeaderView);
        return onCreate(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
//        if (getItemViewType(position) == HEADER_TYPE){
//            //这里写OnClickListener
//            View view=mHeaderView.findViewById(R.id.normal_recycler_view);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }else{
//
//        }
//        int dataPos=getDataPos(holder);
//        onBind(holder,dataPos,list.get(dataPos));
        onBind(holder,position);
        if(onItemClickListener==null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, getDataPos(holder));
            }
        });
    }

    private int getDataPos(RecyclerView.ViewHolder viewHolder){
        return mHeaderView==null?viewHolder.getLayoutPosition():viewHolder.getLayoutPosition()-1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView==null?list.size():list.size()+1;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position)==HEADER_TYPE?((GridLayoutManager) layoutManager).getSpanCount():1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams=holder.itemView.getLayoutParams();
        if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(holder.getLayoutPosition()==0);
        }
    }

    public abstract BaseViewHolder onCreate(ViewGroup parent, int viewType);

    public abstract void onBind(RecyclerView.ViewHolder holder,int position);
}
