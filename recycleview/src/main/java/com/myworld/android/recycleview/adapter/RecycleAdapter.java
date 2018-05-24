package com.myworld.android.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myworld.android.recycleview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小可爱 on 2018/5/21.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<String>list;
    private Context context;
    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;
    public RecycleAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.recycle_adapter,parent,false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER){
            View foot = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
            return new FooterViewHolder(foot);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String ss = list.get(position);
            Log.i("tag"," ================"+ss);
            itemViewHolder.textView.setText(ss);
        }else if (holder instanceof FooterViewHolder){
            FooterViewHolder footer = (FooterViewHolder) holder;
            switch (mLoadMoreStatus){
                case PULLUP_LOAD_MORE:
                    footer.tvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footer.tvLoadText.setText("正在加载...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footer.layout.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        //RecycleView的count设置数据为总条数+1(footer)
        return list.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            initLister(itemView);
        }
        public void initLister(View itemView){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "poistion"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder{
            TextView tvLoadText;
            LinearLayout layout;
        public FooterViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tvLoadText = itemView.findViewById(R.id.tvLoadText);
        }
    }
    public void AddHeaderItem(List<String>items){
        list.addAll(0,items);
        notifyDataSetChanged();
    }
    public void AddFooterItem(List<String>items){
        list.addAll(items);
        notifyDataSetChanged();
    }
    public void changeMore(int status){
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }
}

