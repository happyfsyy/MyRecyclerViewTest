package com.example.myrecyclerviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adapter.GridDividerAdapter;
import com.example.decoration.DividerGridItemDecoration;
import com.example.listener.OnItemClickListener;
import com.example.listener.OnLongClickListener;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class GridDividerAct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mDataList;
    private GridDividerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_divider);

        recyclerView=findViewById(R.id.grid_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        initList();
        adapter=new GridDividerAdapter(mDataList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ToastUtils.showToast("你点击了: "+mDataList.get(pos));
            }
        });
        adapter.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public void onLongClick(View view, int pos) {
                ToastUtils.showToast("long click: "+mDataList.get(pos));
                adapter.removeData(pos);
            }
        });
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initList(){
        mDataList=new ArrayList<>();
        for(int i='A';i<='z';i++){
            mDataList.add(""+(char)i);
        }
    }


}
