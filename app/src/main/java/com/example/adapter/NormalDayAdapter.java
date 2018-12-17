package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;
import com.example.myrecyclerviewtest.R;

import java.util.List;


public class NormalDayAdapter extends RecyclerView.Adapter<NormalDayAdapter.DayViewHolder> {
    private OnClickListener onClickListener;
    private OnItemClickListener onItemClickListener;

    private List<Day> dayList;
    /**
     * 在Activity中根据view.getTag，确认我当前的itemView是不是新建的
     */
    private int normalViewNum=0;
    private int headerViewNum=10000;

    private static final String TAG = ">>>>NormalDayAdapter>>>";
    class DayViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImageView;
        TextView nameTextView;
        TextView headerTextView;
        public DayViewHolder(@NonNull View itemView,int type) {
            super(itemView);
            if(type==Day.NORMAL_TYPE){
                iconImageView=itemView.findViewById(R.id.normal_day_img);
                nameTextView=itemView.findViewById(R.id.normal_day_name);
            }else {
                headerTextView = (TextView) itemView;
            }
        }
    }



    public NormalDayAdapter(List<Day> dayList){
        this.dayList=dayList;
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void insertData(Day day,int position){
        dayList.add(position,day);
        notifyItemInserted(position);
    }
    public void deleteData(int position){
        dayList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG,">>>>>>>onCreateViewHolder()>>>>>");

        final View itemView;
        final DayViewHolder dayViewHolder;
        if (viewType == Day.NORMAL_TYPE) {
            itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_recycler_view,parent,false);
            normalViewNum++;
            itemView.setTag(normalViewNum);
        }else{
            itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_normal_recycler_view,parent,false);
            headerViewNum++;
            itemView.setTag(headerViewNum);
        }
        dayViewHolder=new DayViewHolder(itemView,viewType);


        return dayViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return dayList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull final DayViewHolder dayViewHolder, int position) {
        if(onItemClickListener!=null){
            dayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,dayViewHolder.getAdapterPosition());
                }
            });
        }
        if(getItemViewType(position)==Day.HEADER_TYPE){
            dayViewHolder.headerTextView.setText(dayList.get(position).getHeaderText());
            return;
        }
        dayViewHolder.iconImageView.setImageResource(dayList.get(position).getImgId());
        dayViewHolder.nameTextView.setText(dayList.get(position).getName());
        if(onClickListener!=null){
            dayViewHolder.iconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v,dayViewHolder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}
