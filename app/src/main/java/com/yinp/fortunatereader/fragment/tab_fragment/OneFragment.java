package com.yinp.fortunatereader.fragment.tab_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yinp.fortunatereader.activity.read.NetReadActivity;
import com.yinp.fortunatereader.base.fragment.AppBaseFragment;
import com.yinp.fortunatereader.databinding.FragmentOneBinding;
import com.yinp.fortunatereader.databinding.ItemBookBinding;
import com.yinp.fortunatereader.utils.recyclerview.SpaceItemDecoration;
import com.yinp.tools.adapter.ComViewHolder;
import com.yinp.tools.adapter.CommonAdapter;
import com.yinp.tools.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends AppBaseFragment<FragmentOneBinding> {
    private CommonAdapter<Integer> adapter;
    private List<Integer> dataList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    ItemBookBinding bookBinding;

    @Override
    protected void initViews(View view) {
        for (int i = 0; i < 10; i++) {
            dataList.add(i);
        }
        initRecycler();
    }

    private void initRecycler() {
        adapter = new CommonAdapter<Integer>(getContext(), dataList) {
            @Override
            protected ComViewHolder setComViewHolder(View view, int viewType, ViewGroup parent) {
                bookBinding = ItemBookBinding.inflate(LayoutInflater.from(getContext()), parent, false);
                return new ViewHolder(bookBinding.getRoot());
            }

            @Override
            public void onBindItem(RecyclerView.ViewHolder holder, int position, Integer item) {
                super.onBindItem(holder, position, item);
                if (dataList.get(position) % 2 == 0) {
                    bookBinding.tvTitle.setText("haha");
                } else {
                    bookBinding.tvTitle.setText("o0o0");
                }
            }
        };
        adapter.setOnItemClickListener(new ComViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                goToActivity(NetReadActivity.class);
            }
        });
        StaggeredGridLayoutManager sgl = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.rvList.setLayoutManager(sgl);
        /**
         * 设置间距
         */
        binding.rvList.addItemDecoration(new SpaceItemDecoration(getContext(), 3, 16, 16, 10, 20));
        binding.rvList.setAdapter(adapter);
    }
}
