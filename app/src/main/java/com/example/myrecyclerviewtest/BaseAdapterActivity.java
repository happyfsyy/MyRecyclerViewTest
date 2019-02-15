package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.adapter.SubRecyclerAdapter;
import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;
import com.example.utils.DataUtils;
import com.example.utils.ToastUtils;

import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity {

    private List<Day> dayList;
    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
//        mLayoutManger=new LinearLayoutManager(this);
//        mLayoutManger=new GridLayoutManager(this,2);
        mLayoutManger=new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManger);

        dayList=DataUtils.initList(dayList,true);
        SubRecyclerAdapter adapter=new SubRecyclerAdapter(dayList);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToastUtils.showToast("OnClickListener: "+dayList.get(position).getName());
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ToastUtils.showToast("OnItemClickListener: "+dayList.get(pos).getName());
            }
        });
        adapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToastUtils.showToast("I'm headerClickListener");
            }
        });
        View headerView=getLayoutInflater().inflate(R.layout.header_recycler_view,recyclerView,false);
        adapter.setHeaderView(headerView);
        recyclerView.setAdapter(adapter);

    }
}
