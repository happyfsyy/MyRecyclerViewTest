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

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder> {
    public static final int TYPE_HEADER=0;
    public static final int TYPE_NORMAL=1;
    private List<Day> dayList;
    private View headerView;
    private OnItemClickListener onItemClickListener;

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(itemView==headerView)  return;
            img=itemView.findViewById(R.id.normal_day_img);
            name=itemView.findViewById(R.id.normal_day_name);
        }
    }
    public HeaderAdapter(List<Day> dayList){
        this.dayList=dayList;
    }

    public void setHeaderView(View headerView){
        this.headerView=headerView;
        notifyItemInserted(0);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(headerView!=null&&position==0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(headerView!=null&&viewType==TYPE_HEADER) return  new ViewHolder(headerView);
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_recycler_view,parent,false);
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_HEADER) return;
        final int dataPos=getDataPosition(holder);
        holder.img.setImageResource(dayList.get(dataPos).getImgId());
        holder.name.setText(dayList.get(dataPos).getName());
        if(onItemClickListener==null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,dataPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return headerView==null?dayList.size():dayList.size()+1;
    }

    private int getDataPosition(ViewHolder holder){
        return headerView==null? holder.getLayoutPosition():holder.getLayoutPosition()-1;
    }
}
