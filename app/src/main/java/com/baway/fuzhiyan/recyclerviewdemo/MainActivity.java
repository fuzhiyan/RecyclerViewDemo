package com.baway.fuzhiyan.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;

    private MyRecyclerViewAdapter adapter;
    private ArrayList<String> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载控件
        initView();
        //加载数据，模拟添加100条
        initData();
        //适配器
        adapter = new MyRecyclerViewAdapter(this, datas);
        recyclerview.setAdapter(adapter);
        //设置布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Toast.makeText(MainActivity.this,"点击了第"+position+"个条目",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClickListener(int position, View view) {

                Toast.makeText(MainActivity.this,"别按了,快喘不上气了",Toast.LENGTH_LONG).show();
            }
        });
        //分割线
        recyclerview.addItemDecoration(new DividerListItemDecoration(this ,DividerListItemDecoration.VERTICAL_LIST));
    }

    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i = 0; i < 100; i++) {
            datas.add("Content_" + i);
        }

    }

    private void initView() {
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview = (RecyclerView) findViewById(R.id.recycler);

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add://D.添加数据

                adapter.addData(0);
                break;

            case R.id.btn_delete://D.删除数据

                adapter.remove(0);
                break;

            case R.id.btn_list://设置List类型效果

                recyclerview.setLayoutManager(new LinearLayoutManager(this));
                break;

            case R.id.btn_grid://设置Grid类型效果

                recyclerview.setLayoutManager(new GridLayoutManager(this,3));
                break;

            case R.id.btn_flow://设置瀑布流类型效果
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
                break;

        }
    }
}
