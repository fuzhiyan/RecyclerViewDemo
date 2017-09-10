package com.baway.fuzhiyan.recycler.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baway.fuzhiyan.recycler.bean.DataMode;

/**
 * Created by Administrator on 2017/9/8.
 * time:
 * author:付智焱
 *
 * 抽取基类的原则：
 * 1.子类共有，且实现相同的逻辑，抽取到基类中。
 * 2.子类共有，且实现不同的逻辑，通过抽象方法的形式定义到基类中。
 */

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindHolder(DataMode dataMode);
}
