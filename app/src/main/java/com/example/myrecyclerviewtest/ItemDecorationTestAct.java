package com.example.myrecyclerviewtest;

import android.os.Bundle;
import android.view.View;

import com.example.adapter.SimpleAdapter;
import com.example.decoration.DividerDecoration;
import com.example.decoration.PaddingDecoration;
import com.example.listener.OnItemClickListener;
import com.example.utils.DisplayUtil;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 可以测试Padding以及divider（onDraw，onDrawOver）
 */
public class ItemDecorationTestAct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SimpleAdapter adapter;
    private List<String> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.recycler_view);

        initList();
        adapter=new SimpleAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ToastUtils.showToast(dataList.get(pos));
            }
        });
        PaddingDecoration decoration=new PaddingDecoration();
        decoration.setPadding(DisplayUtil.dp2px(10));
        decoration.setLeftPadding(0);
        recyclerView.addItemDecoration(new DividerDecoration(this));
    }
    private void initList(){
        dataList=new ArrayList<>();
        for(int i=1;i<=50;i++){
            dataList.add(String.valueOf(i));
        }
    }
}
