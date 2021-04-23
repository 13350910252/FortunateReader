package com.yinp.fortunatereader.fragment.tab_fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.yinp.fortunatereader.base.fragment.PresenterBaseFragment;
import com.yinp.fortunatereader.bean.NovelListBean;
import com.yinp.fortunatereader.databinding.FragmentTwoBinding;
import com.yinp.fortunatereader.databinding.ItemBookListBinding;
import com.yinp.fortunatereader.manager.GetNovelManager;
import com.yinp.fortunatereader.web.retrofit.BaseObserver;
import com.yinp.fortunatereader.web.retrofit.BaseRetrofitData;
import com.yinp.tools.adapter.ComViewHolder;
import com.yinp.tools.adapter.CommonAdapter;
import com.yinp.tools.adapter.ViewHolder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends PresenterBaseFragment<FragmentTwoBinding, GetNovelManager> {
    private String option = "title";//选择搜索项，标题：title ， 作者 ：author ，分类：fictionType
    private int from = 1;//当前页数，留空默认1
    private int size = 10;//一页显示的数量，留空默认10，最多100
    private List<NovelListBean> dataList = new ArrayList<>();
    private CommonAdapter<NovelListBean> adapter;

    @Override
    protected GetNovelManager createPresenter() {
        return new GetNovelManager(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecycler();
        refresh();
    }

    ItemBookListBinding itemBookListBinding;

    @Override
    protected void initViews(View view) {
        binding.tvSearch.setOnClickListener(this);
    }

    private void initRecycler() {
        adapter = new CommonAdapter<NovelListBean>(getContext(), dataList) {
            @Override
            protected ComViewHolder setComViewHolder(View view, int viewType, ViewGroup parent) {
                itemBookListBinding = ItemBookListBinding.inflate(LayoutInflater.from(getContext()), parent, false);
                return new ViewHolder(itemBookListBinding.getRoot());
            }

            @Override
            public void onBindItem(RecyclerView.ViewHolder holder, int position, NovelListBean item) {
                super.onBindItem(holder, position, item);
                itemBookListBinding.tvTitle.setText(item.getTitle());
                Glide.with(mContext).load(item.getCover()).into(itemBookListBinding.ivCover);
                itemBookListBinding.tvAuthor.setText(TextUtils.isEmpty(item.getAuthor()) ? "" : item.getAuthor() + item.getFictionType());
                itemBookListBinding.tvContent.setText(TextUtils.isEmpty(item.getDescs()) ? "" : item.getDescs());
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        binding.rvList.setLayoutManager(llm);
        binding.rvList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == binding.tvSearch) {
            search();
        } else if (v == binding.tvType) {

        }
    }

    private void refresh() {
        binding.baseRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                from = 1;
                binding.baseRefresh.setEnableLoadMore(true);
                getNovelList();
            }
        });
        binding.baseRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                from++;
                getNovelList();
            }
        });
    }

    public void search() {
        from = 1;
        binding.baseRefresh.setEnableLoadMore(true);
        getNovelList();
    }

    private void getNovelList() {
        presenter.getNovelList(option, binding.etSearchKey.getText().toString(), from, size, new BaseObserver<BaseRetrofitData>() {
            @Override
            public void onSuccess(BaseRetrofitData baseData) {
                Log.d("abcd", "onSuccess: " + baseData);
                JsonElement jsonElement = baseData.getData();
                if (jsonElement.isJsonNull()) {
                    return;
                }
                JsonElement data = jsonElement.getAsJsonObject().get("data");
                Type type = new TypeToken<ArrayList<NovelListBean>>() {
                }.getType();
                ArrayList<NovelListBean> arrayList = new Gson().fromJson(data, type);
                if (arrayList.size() <= 0) {
                    return;
                }
                if (from == 1) {
                    dataList.clear();
                    dataList.addAll(arrayList);
                    binding.baseRefresh.finishRefresh(true/*,false*/);//传入false表示刷新失败
                } else {
                    dataList.addAll(arrayList);
                    binding.baseRefresh.finishLoadMore(true/*,false*/);//传入false表示刷新失败
                }
                int count = jsonElement.getAsJsonObject().get("count").getAsInt();
                if (from * size >= count) {
                    binding.baseRefresh.setEnableLoadMore(false);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onFailure(Throwable e, String info) throws Exception {

            }
        });
    }
}
