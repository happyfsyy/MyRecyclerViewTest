package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.listener.OnItemClickListener;
import com.example.listener.OnLongClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridDividerAdapter extends RecyclerView.Adapter<GridDividerAdapter.LetterViewHolder> {
    static class LetterViewHolder extends RecyclerView.ViewHolder{
        TextView letter;
        public LetterViewHolder(@NonNull View itemView) {
            super(itemView);
            letter=(TextView)itemView;
        }
    }
    private List<String> mDataList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnLongClickListener onLongClickListener;

    public GridDividerAdapter(List<String> dataList){
        this.mDataList=dataList;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener=onLongClickListener;
    }
    public void addData(int position){
        mDataList.add(position,"InsertOne");
        notifyItemInserted(position);
    }
    public void removeData(int position){
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public LetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.grid_divider_item,parent,false);
        LetterViewHolder viewHolder=new LetterViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final LetterViewHolder holder, int position) {
        holder.letter.setText(mDataList.get(position));
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,holder.getAdapterPosition());
                }
            });
        }
        if(onLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onLongClickListener.onLongClick(holder.itemView,pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
