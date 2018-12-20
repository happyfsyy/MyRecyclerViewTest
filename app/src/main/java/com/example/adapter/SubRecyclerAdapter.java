package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubRecyclerAdapter extends BaseRecyclerAdapter<Day>{
    private OnClickListener onClickListener;
    private OnClickListener onHeaderClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public void setOnHeaderClickListener(OnClickListener onHeaderClickListener){
        this.onHeaderClickListener=onHeaderClickListener;
    }
    public SubRecyclerAdapter(List<Day> list) {
        super(list);
    }

    class HeaderViewHolder extends BaseViewHolder{
        ImageView headerImageView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerImageView=itemView.findViewById(R.id.header_imageView);
        }
    }
    class SubViewHolder extends BaseViewHolder{
        ImageView icon;
        TextView name;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.multi_day_icon);
            name=itemView.findViewById(R.id.multi_day_name);
        }
    }

    @Override
    public BaseViewHolder onCreate(ViewGroup parent, int viewType) {
        if(viewType==HEADER_TYPE){
            return new HeaderViewHolder(getHeaderView());
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multi_layout, parent, false);
            return new SubViewHolder(itemView);
        }
    }

    @Override
    public void onBind(final RecyclerView.ViewHolder holder,  int position) {
        int viewType=getItemViewType(position);
        final int dataPos=getDataPos(holder);
        if(viewType==BaseRecyclerAdapter.HEADER_TYPE){
            if(holder instanceof HeaderViewHolder){
                ((HeaderViewHolder)holder).headerImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onHeaderClickListener.onClick(v,dataPos);
                    }
                });
            }
        }else{
            if(holder instanceof SubViewHolder){
                SubViewHolder viewHolder=(SubViewHolder)holder;
                viewHolder.icon.setImageResource(list.get(dataPos).getImgId());
                viewHolder.name.setText(list.get(dataPos).getName());
                viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(v,dataPos);
                    }
                });
            }
        }
    }
}