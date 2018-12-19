package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.adapter.SubRecyclerAdapter;
import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity {

    private List<Day> dayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_recycler_view);

        RecyclerView recyclerView=findViewById(R.id.normal_recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        initDayList();
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

    class A{

    }
    class B extends A{

    }
    public A hello(){
        return  new B();
    }
}
