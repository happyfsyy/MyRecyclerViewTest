package com.example.myrecyclerviewtest;

import android.os.Bundle;

import com.example.adapter.SimpleAdapter;
import com.example.decoration.SelectionDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StickyAct extends AppCompatActivity {
    private List<String> mDataList;
    private List<String> mTitleList;
    private RecyclerView recyclerView;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initList();
        recyclerView=findViewById(R.id.recycler_view);
        adapter=new SimpleAdapter(mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SelectionDecoration(mDataList, this, new SelectionDecoration.DecorationCallback() {
            @Override
            public String getGroupId(int pos) {
                if(mTitleList.get(pos)!=null){
                    //todo 这个条件判断有什么用，删掉试试看
                    return mTitleList.get(pos);
                }
                //todo 为什么需要-1
                return "-1";
            }

            @Override
            public String getGroupFirstLine(int pos) {
                if(mTitleList.get(pos)!=null){
                    return mTitleList.get(pos);
                }
                return "";
            }
        }));
        recyclerView.setAdapter(adapter);
    }
    private void initList(){
        mDataList=new ArrayList<>();
        mTitleList=new ArrayList<>();
        for(int i=1;i<=30;i++){
            mDataList.add(String.valueOf(i));
        }
        mTitleList.add("1111111");
        mTitleList.add("1111111");
        mTitleList.add("1111111");
        mTitleList.add("1111111");
        mTitleList.add("1111111");
        mTitleList.add("22222");
        mTitleList.add("22222");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("3333");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");

        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
        mTitleList.add("444444444");
    }
}
