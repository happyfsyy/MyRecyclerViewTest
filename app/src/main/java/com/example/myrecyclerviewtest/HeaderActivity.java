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


        initDayList();
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
    private void initDayList(){
        dayList=new ArrayList<>();
        for(int i=0;i<3;i++){
            Day day1=new Day(R.drawable.day1,"day1",1);
            Day day2=new Day(R.drawable.day2,"day2",1);
            Day day3=new Day(R.drawable.day3,"day3",1);
            Day day4=new Day(R.drawable.day4,"day4",1);
            Day day5=new Day(R.drawable.day5,"day5",1);
            Day day6=new Day(R.drawable.day6,"day6",1);
            Day day7=new Day(R.drawable.day7,"day7",1);
            Day day8=new Day(R.drawable.day8,"day8",1);
            Day day9=new Day(R.drawable.day9,"day9",1);
            Day day10=new Day(R.drawable.day10,"day10",1);
            Day day11=new Day(R.drawable.day11,"day11",1);
            Day day12=new Day(R.drawable.day12,"day12",1);
            Day day13=new Day(R.drawable.day13,"day13",1);
            dayList.add(day1);dayList.add(day2);dayList.add(day3);dayList.add(day4);
            dayList.add(day5);dayList.add(day6);dayList.add(day7);dayList.add(day8);
            dayList.add(day9);dayList.add(day10);dayList.add(day11);dayList.add(day12);
            dayList.add(day13);
        }
    }
}
