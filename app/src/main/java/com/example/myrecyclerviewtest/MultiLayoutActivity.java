package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MultiLayoutDayAdapter;
import com.example.bean.Day;
import com.example.decoration.ListDecoration;
import com.example.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiLayoutActivity extends AppCompatActivity {

    private List<Day> dayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_recycler_view);
        RecyclerView recyclerView=findViewById(R.id.normal_recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        initDayList();
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
    private void initDayList(){
        dayList=new ArrayList<>();
        for(int i=0;i<3;i++){
            Day day1=new Day(R.drawable.day1,getRandomLengthName("day1"),1);
            Day day2=new Day(R.drawable.day2,getRandomLengthName("day2"),1);
            Day day3=new Day(R.drawable.day3,getRandomLengthName("day3"),1);
            Day day4=new Day(R.drawable.day4,getRandomLengthName("day4"),1);
            Day day5=new Day(R.drawable.day5,getRandomLengthName("day5"),1);
            Day day6=new Day(R.drawable.day6,getRandomLengthName("day6"),1);
            Day day7=new Day(R.drawable.day7,getRandomLengthName("day7"),1);
            Day day8=new Day(R.drawable.day8,getRandomLengthName("day8"),1);
            Day day9=new Day(R.drawable.day9,getRandomLengthName("day9"),1);
            Day day10=new Day(R.drawable.day10,getRandomLengthName("day10"),1);
            Day day11=new Day(R.drawable.day11,getRandomLengthName("day11"),1);
            Day day12=new Day(R.drawable.day12,getRandomLengthName("day12"),1);
            Day day13=new Day(R.drawable.day13,getRandomLengthName("day13"),1);
            dayList.add(day1);dayList.add(day2);dayList.add(day3);dayList.add(day4);
            dayList.add(day5);dayList.add(day6);dayList.add(day7);dayList.add(day8);
            dayList.add(day9);dayList.add(day10);dayList.add(day11);dayList.add(day12);
            dayList.add(day13);
        }
    }
    private void initDayList2(){
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

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }
}
