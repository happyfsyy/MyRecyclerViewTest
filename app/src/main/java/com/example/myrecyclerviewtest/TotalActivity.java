package com.example.myrecyclerviewtest;

import android.os.Bundle;
import android.view.View;

import com.example.adapter.SimpleAdapter;
import com.example.decoration.DividerDecoration;
import com.example.listener.MyItemTouchHelper;
import com.example.listener.OnItemClickListener;
import com.example.listener.OnRecyclerItemClickListener;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TotalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mDataList;
    private SimpleAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.recycler_view);
        initList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SimpleAdapter(mDataList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ToastUtils.showToast("你点击了"+mDataList.get(pos));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerDecoration(this));
        itemTouchHelper=new ItemTouchHelper(new MyItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void OnItemClick(RecyclerView.ViewHolder viewHolder) {
                ToastUtils.showToast(mDataList.get(viewHolder.getAdapterPosition()));
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                ToastUtils.showToast(mDataList.get(viewHolder.getAdapterPosition()));
                if(viewHolder.getLayoutPosition()!=0){
                    itemTouchHelper.startDrag(viewHolder);
                }
            }
        });

    }
    private void initList(){
        mDataList=new ArrayList<>();
        for(int i=0;i<50;i++){
            mDataList.add(String.valueOf(i));
        }
    }
}
