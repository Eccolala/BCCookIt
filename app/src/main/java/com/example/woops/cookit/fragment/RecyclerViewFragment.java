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


public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static final int ITEM_COUNT = 10;

    private List<NewsItem> mList = new ArrayList<>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
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
        item0.setPic(R.drawable.egg);
        item0.setDesc("番茄鸡蛋，具有滋补、美容的功效");
        mList.add(item0);

        NewsItem item1 = new NewsItem();
        item1.setPic(R.drawable.huang);
        item1.setDesc("凉拌黄瓜，润肺止咳、补气");
        mList.add(item1);

        NewsItem item2 = new NewsItem();
        item2.setPic(R.drawable.ss);
        item2.setDesc("椒盐虾，补肾壮阳，通乳抗毒");
        mList.add(item2);

        NewsItem item3 = new NewsItem();
        item3.setPic(R.drawable.eggpie);
        item3.setDesc("香煎土豆丝鸡蛋饼，防止中风、和胃健脾");
        mList.add(item3);

        NewsItem item4 = new NewsItem();
        item4.setPic(R.drawable.redmeat);
        item4.setDesc("红烧肉，提供优质蛋白质和必需的脂肪酸");
        mList.add(item4);

        NewsItem item5 = new NewsItem();
        item5.setPic(R.drawable.bbeaf);
        item5.setDesc("土豆炖牛肉，健脾养胃，强筋壮骨");
        mList.add(item5);


        NewsItem item7 = new NewsItem();
        item7.setPic(R.drawable.chicken);
        item7.setDesc("板栗鸡，益气血、养胃、补肾、健肝脾");
        mList.add(item7);

        NewsItem item8 = new NewsItem();
        item8.setPic(R.drawable.fish);
        item8.setDesc("清炖鲫鱼，温胃止痛");
        mList.add(item8);

        NewsItem item9 = new NewsItem();
        item9.setPic(R.drawable.pork);
        item9.setDesc("北京烤鸭，抗脚气病，抗衰老");
        mList.add(item9);



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
