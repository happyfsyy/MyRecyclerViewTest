package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HeaderAdapter;
import com.example.bean.Day;
import com.example.decoration.ListDecoration;
import com.example.listener.OnItemClickListener;
import com.example.utils.DataUtils;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class HeaderActivity extends AppCompatActivity {

    private List<Day> dayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_recycler_view);
        RecyclerView recyclerView=findViewById(R.id.normal_recycler_view);


        dayList=DataUtils.initList(dayList,false);
        final HeaderAdapter headerAdapter=new HeaderAdapter(dayList);
        headerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ToastUtils.showToast(dayList.get(pos).getName());
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        final GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return headerAdapter.getItemViewType(position)==HeaderAdapter.TYPE_HEADER?gridLayoutManager.getSpanCount():1;
//            }
//        });
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(linearLayoutManager);
        View headerView=LayoutInflater.from(this).inflate(R.layout.header_recycler_view,recyclerView,false);
        headerAdapter.setHeaderView(headerView);
        recyclerView.setAdapter(headerAdapter);
        recyclerView.addItemDecoration(new ListDecoration(this,RecyclerView.VERTICAL));
    }

}
