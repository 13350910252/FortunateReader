package com.yinp.fortunatereader.manager;

import android.content.Context;

import com.yinp.fortunatereader.web.retrofit.BaseObserver;
import com.yinp.fortunatereader.web.retrofit.BaseRetrofitData;
import com.yinp.fortunatereader.web.retrofit.BuildRetrofit;

public class GetNovelManager extends BaseManager {
    private Context context;

    public GetNovelManager(Context context) {
        this.context = context;
    }

    /**
     * 根据标题或者作者搜索
     */
    public void getNovelList(String option, String title, int from, int size, BaseObserver<BaseRetrofitData> baseObserver) {
        addDisposable(BuildRetrofit.getInstance(context, BuildRetrofit.BASE_URL)
                .getApiRetrofit().getNovelList(option, title, from, size), baseObserver);
    }
    public void getNovelContent(String chapterId,BaseObserver<BaseRetrofitData> baseObserver){
        addDisposable(BuildRetrofit.getInstance(context, BuildRetrofit.BASE_URL)
                .getApiRetrofit().getNovelContent(chapterId), baseObserver);
    }
}
