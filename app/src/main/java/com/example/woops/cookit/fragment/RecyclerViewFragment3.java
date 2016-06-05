package com.example.woops.cookit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.woops.cookit.R;
import com.example.woops.cookit.activity.NewsDetail1;
import com.example.woops.cookit.activity.NewsDetail2;
import com.example.woops.cookit.bean.NewsItem;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment3 extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static final int ITEM_COUNT = 10;

    private List<NewsItem> mList = new ArrayList<>();

    public static RecyclerViewFragment3 newInstance() {
        return new RecyclerViewFragment3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

//        for (int i = 0; i < ITEM_COUNT; i++) {
//            NewsItem item = new NewsItem();
//            item.setPic(R.mipmap.ic_launcher);
//            item.setDesc("锅包肉锅包肉锅包肉锅包肉");
//            mList.add(item);
//        }
        NewsItem item0 = new NewsItem();
        item0.setPic(R.drawable.salad);
        item0.setDesc("蔬菜沙拉，减脂瘦身");
        mList.add(item0);

        NewsItem item1 = new NewsItem();
        item1.setPic(R.drawable.kugua);
        item1.setDesc("酿苦瓜，清热去火");
        mList.add(item1);





        RecyclerViewAdapter reAdapter = new RecyclerViewAdapter(getActivity(),mList );
        reAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
//                Toast.makeText(getActivity(), position + " click",
//                        Toast.LENGTH_SHORT).show();

                if (position == 1){
                    startActivity(new Intent(getActivity(), NewsDetail1.class));
                }else if (position == 2){
                    startActivity(new Intent(getActivity(), NewsDetail2.class));

                }else {
                    Toast.makeText(getActivity(), "正在开发敬请期待......",
                            Toast.LENGTH_SHORT).show();
                }
            }


        });
        mAdapter = new RecyclerViewMaterialAdapter(reAdapter);



        mRecyclerView.setAdapter(mAdapter);




        mAdapter.notifyDataSetChanged();


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }
}
