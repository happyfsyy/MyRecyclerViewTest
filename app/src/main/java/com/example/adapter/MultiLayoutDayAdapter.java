package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.listener.OnItemClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultiLayoutDayAdapter extends RecyclerView.Adapter<MultiLayoutDayAdapter.DayViewHolder>{
    private List<Day> dayList;
    private OnItemClickListener onItemClickListener;
    class DayViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView name;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.normal_day_img);
            name=itemView.findViewById(R.id.normal_day_name);
        }
    }

    public MultiLayoutDayAdapter(List<Day> dayList){
        this.dayList=dayList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_recycler_view,parent,false);
        final DayViewHolder dayViewHolder=new DayViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,dayViewHolder.getAdapterPosition());
            }
        });
        return dayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.icon.setImageResource(dayList.get(position).getImgId());
        holder.name.setText(dayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}