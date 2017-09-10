package com.baway.fuzhiyan.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.fuzhiyan.recycler.bean.DataMode;
import com.baway.fuzhiyan.recycler.R;
import com.baway.fuzhiyan.recycler.base.TypeAbstractViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 * time:
 * author:付智焱
 * 多布局适配器：
 * 1.创建一个类继承recyclerview的adapter
 * 2.定义recyclerview.Viewholder方便多布局
 * 3.创建三种布局的item及ViewHolder
 * 4.使用getItemViewType
 * 5.在OnBindViewHolder和onCreateViewHolder进行ViewHolder的创建和数据的加载
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //布局填充器
    private LayoutInflater layoutInflater;
    private Context context;
    private List<DataMode> mlist = new ArrayList<>();


    public MyAdapter(Context context, List<DataMode> mlist) {
        this.context = context;
        this.mlist = mlist;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case DataMode.TYPE_ONE:
                return new OneViewHolder(layoutInflater.inflate(R.layout.item_type_one, parent, false));
            case DataMode.TYPE_two:
                return new TwoViewHolder(layoutInflater.inflate(R.layout.item_type_two, parent, false));
            case DataMode.TYPE_thre:
                return new ThreeViewHolder(layoutInflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        switch (mlist.get(position).TYPE) {
//            case 1:
//                OneViewHolder oneViewHolder = (OneViewHolder) holder;
//                oneViewHolder.textView.setText(mlist.get(position).name);
//                oneViewHolder.imageView.setImageResource(mlist.get(position).avatarcolor);
//                oneViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context, "第一种", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
//            case 2:
//                TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
//                twoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context, "第二种", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////                twoViewHolder.name.setText(mlist.get(position).name);
////                twoViewHolder.content.setText(mlist.get(position).content);
////                twoViewHolder.imageView.setImageResource(mlist.get(position).avatarcolor);
//                break;
//            case 3:
//                ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
//                threeViewHolder.avatarImage.setImageResource(mlist.get(position).avatarcolor);
//                threeViewHolder.contentImage.setImageResource(mlist.get(position).avatarcolor);
//                threeViewHolder.name.setText(mlist.get(position).name);
//                threeViewHolder.content.setText(mlist.get(position).content);
//                threeViewHolder.content.setTextColor(mlist.get(position).contentColor);
//                threeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context, "第三种", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
//        }


        ((TypeAbstractViewHolder) holder).bindHolder(mlist.get(position));
        ((TypeAbstractViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "第"+mlist.get(position).TYPE+"种", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }


    //第一个多条目的布ViewHolder
    public class OneViewHolder extends TypeAbstractViewHolder {

        private TextView textView;
        private ImageView imageView;

        public OneViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.one_image_avatar);
            textView = itemView.findViewById(R.id.one_text_name);
        }

        @Override
        public void bindHolder(DataMode dataMode) {
            imageView.setImageResource(dataMode.avatarcolor);
            textView.setText(dataMode.name);
        }
    }

    //第二个
    public class TwoViewHolder extends TypeAbstractViewHolder {
        private ImageView imageView;
        private TextView content;
        private TextView name;

        public TwoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.two_image_avatar);
            name = itemView.findViewById(R.id.two_text_name);
            content = itemView.findViewById(R.id.two_text_content);
        }

        @Override
        public void bindHolder(DataMode dataMode) {
            name.setText(dataMode.name);
            content.setText(dataMode.content);
            imageView.setImageResource(dataMode.avatarcolor);
        }
    }

    //第三个
    public class ThreeViewHolder extends TypeAbstractViewHolder {

        private ImageView contentImage;
        private ImageView avatarImage;
        private TextView name;
        private TextView content;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            contentImage = itemView.findViewById(R.id.three_contentImage);
            avatarImage = itemView.findViewById(R.id.three_image_avatar);
            name = itemView.findViewById(R.id.three_text_name);
            content = itemView.findViewById(R.id.three_text_content);

        }

        @Override
        public void bindHolder(DataMode dataMode) {
            contentImage.setImageResource(dataMode.contentColor);
            content.setText(dataMode.content);
            name.setText(dataMode.name);
            avatarImage.setImageResource(dataMode.avatarcolor);
        }
    }

    //根据不同的下标返回不同的类型
    @Override
    public int getItemViewType(int position) {
        return mlist.get(position).TYPE;
    }

}
