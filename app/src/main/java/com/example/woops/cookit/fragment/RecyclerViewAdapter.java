package com.example.woops.cookit.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woops.cookit.R;
import com.example.woops.cookit.bean.NewsItem;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<NewsItem> list;
    private LayoutInflater inflater;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RecyclerViewAdapter(Context context, List<NewsItem> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.list_item_card_big, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.foodPic.setImageResource(list.get(position).getPic());
        holder.foodDesc.setText(list.get(position).getDesc());
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_HEADER;
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView foodPic;
        TextView foodDesc;


        public MyViewHolder(View parent) {
            super(parent);
            foodPic = (ImageView) parent.findViewById(R.id.card_iv);
            foodDesc = (TextView) parent.findViewById(R.id.card_tv);
        }
    }

}

