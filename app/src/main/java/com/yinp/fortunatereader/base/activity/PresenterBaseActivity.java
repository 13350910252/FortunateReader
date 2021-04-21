package com.yinp.fortunatereader.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.yinp.fortunatereader.mvp.BasePresenter;
import com.yinp.fortunatereader.mvp.CBaseView;
import com.yinp.fortunatereader.web.retrofit.BaseRetrofitData;

public abstract class PresenterBaseActivity<T extends ViewBinding,P extends BasePresenter> extends AppBaseActivity<T> implements CBaseView {
    private P presenter;

    protected abstract P createPresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseRetrofitData model) {

    }
}
