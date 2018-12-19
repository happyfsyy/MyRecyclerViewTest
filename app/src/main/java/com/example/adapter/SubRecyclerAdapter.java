package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnHeaderClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubRecyclerAdapter extends BaseRecyclerAdapter<Day>{
    private OnClickListener onClickListener;
    private OnHeaderClickListener onHeaderClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener){
        this.onHeaderClickListener=onHeaderClickListener;
    }
    public SubRecyclerAdapter(List<Day> list) {
        super(list);
    }

    class HeaderViewHolder extends BaseViewHolder{
        ImageView headerImageView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class SubViewHolder extends BaseViewHolder{
        ImageView icon;
        TextView name;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.normal_day_img);
            name=itemView.findViewById(R.id.normal_day_name);
        }
    }

    @Override
    public BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_recycler_view,parent,false);
        return new SubViewHolder(itemView);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, final int position) {
        int viewType=getItemViewType(position);
        if(viewType==BaseRecyclerAdapter.HEADER_TYPE){
        }else{

        }
        SubViewHolder viewHolder=(SubViewHolder)holder;
        viewHolder.icon.setImageResource(list.get(position).getImgId());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v,position);
            }
        });
    }
}