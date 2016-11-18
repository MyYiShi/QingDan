package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yishi on 2016/11/1.
 */

public abstract class BaseRecycleView<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> datas;
    protected LayoutInflater inflater;

    public BaseRecycleView(Context context) {
        inflater = LayoutInflater.from(context);
        this.datas = new ArrayList<>();
    }

    /**
     * 新增数据
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void clearDatas() {
        this.datas.clear();
        notifyDataSetChanged();
    }


    /**
     * 返回脚部视图的个数
     * @return
     */
    protected abstract int getFooterCount();

    /**
     * 返回头部视图的个数
     * @return
     */
    protected abstract int getHeaderCount();

    /**
     * 获取对应位置的数据
     */
    public T getItem(int position){
        return datas.get(position - getHeaderCount());
    }

    @Override
    public int getItemCount() {
        return datas.size() + getHeaderCount() + getFooterCount();
    }
}
