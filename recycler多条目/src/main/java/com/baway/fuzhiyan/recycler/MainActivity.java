package com.baway.fuzhiyan.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baway.fuzhiyan.recycler.adapter.MyAdapter;
import com.baway.fuzhiyan.recycler.bean.DataMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现recyclerview多布局效果
 * 搭建环境
 * 创建适配器
 * 初始化数据
 * 设置适配器，布局管理器。。。
 */


public class MainActivity extends AppCompatActivity {
    int colors[] = {android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light};
    private List<DataMode> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //初始化数据
        initData();
        //创建适配器
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        final GridLayoutManager gridManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridManager);
        //设置一个监听，根据不同类型让其显示一列或者俩列
        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //拿到当前itemView的类型
                int type = myAdapter.getItemViewType(position);
                //根据类型判断。得到grid的列数
                if (type == DataMode.TYPE_thre) {

                    return gridManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });


    }


    /*
    * Type:类型
    * String 类型;name
    * String 类型：content
    * 图片：颜色
    *
    *
    * */
    private void initData() {

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
//            int type = (int) (Math.random() * 3 + 1);
            int type = 0;
            if (i < 5 || (i > 15 && i < 20)) {

                type = 1;
            } else if (i >= 5 && i < 10) {
                type = 2;
            } else {
                type = 3;
            }
            DataMode mode = new DataMode();
            //实际开发中是解析服务器拿到的数据
            mode.avatarcolor = colors[type - 1];
            mode.TYPE = type;
            mode.name = "Name" + i;
            mode.content = "Content" + i;
            mode.contentColor = colors[(type + 1) % 3];
            list.add(mode);
        }

    }


}
