package com.example.utils;

import com.example.bean.Day;
import com.example.myrecyclerviewtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataUtils {
    public static String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }

    public static List<Day> initList(List<Day> dayList,boolean isRandomLength){
        dayList=new ArrayList<>();
        int [] imgIds={R.drawable.day1,R.drawable.day2,R.drawable.day3,R.drawable.day4,
                R.drawable.day5,R.drawable.day6,R.drawable.day7,R.drawable.day8,
                R.drawable.day9,R.drawable.day10,R.drawable.day11,R.drawable.day12,R.drawable.day13};
        String[] names={"day1","day2","day3","day4","day5","day6","day7","day8",
                "day5","day6","day7","day8","day9","day10","day11","day12","day13"};
        for(int i=0;i<3;i++){
            for(int j=0;j<imgIds.length;j++){
                Day day;
                if(isRandomLength){
                    day=new Day(imgIds[j],getRandomLengthName(names[j]),1);
                }else{
                    day=new Day(imgIds[j],names[j],1);
                }
                dayList.add(day);
            }
        }
        return dayList;
    }

}
