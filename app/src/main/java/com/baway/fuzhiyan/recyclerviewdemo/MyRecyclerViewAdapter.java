package com.baway.fuzhiyan.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 * time:
 * author:付智焱
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> datas;


    public MyRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建view对象和viewholder对象
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_title.setText(datas.get(position));
//        holder.tv_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"点击了第"+datas.get(position)+"个条目",Toast.LENGTH_LONG).show();
//            }
//        });


    }

    //recyclerview条目的数量
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void addData(int position) {

        datas.add(position + "添加" + position);
        notifyItemInserted(position);
        //刷新指定区域 start end
        notifyItemRangeChanged(position, datas.size());

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView tv_title;

        public MyViewHolder(final View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            iv_icon = itemView.findViewById(R.id.iv_icon);
//            iv_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "点击了第" + getLayoutPosition() + "张图片", Toast.LENGTH_LONG).show();
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {

                        listener.onItemClickListener(getLayoutPosition(), itemView);
                        listener.onItemLongClickListener(getLayoutPosition(), itemView);
                    }
                }
            });


        }
    }

    public void remove(int position) {

        datas.remove(position);

        notifyItemRemoved(position);
        //局部刷新指定区域 参数对应头和尾
        notifyItemRangeChanged(position, datas.size());
    }

    //定义了一个子条目点击的接口
    interface OnItemClickListener {


        //点击事件
        void onItemClickListener(int position, View view);

        //长按事件
        void onItemLongClickListener(int position, View view);
    }


    public OnItemClickListener listener;


    //通过构造方法。提供对外的接口
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
