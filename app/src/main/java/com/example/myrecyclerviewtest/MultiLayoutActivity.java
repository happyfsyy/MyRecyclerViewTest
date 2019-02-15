package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MultiLayoutDayAdapter;
import com.example.bean.Day;
import com.example.decoration.ListDecoration;
import com.example.listener.OnItemClickListener;
import com.example.utils.DataUtils;

import java.util.List;

public class MultiLayoutActivity extends AppCompatActivity {

    private List<Day> dayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        dayList=DataUtils.initList(dayList,true);
        MultiLayoutDayAdapter dayAdapter=new MultiLayoutDayAdapter(dayList);
        recyclerView.setAdapter(dayAdapter);
        dayAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Toast.makeText(MultiLayoutActivity.this, dayList.get(pos).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.addItemDecoration(new ListDecoration(this,RecyclerView.HORIZONTAL));

    }

}
