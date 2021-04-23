package com.yinp.fortunatereader.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.yinp.fortunatereader.manager.BaseManager;

public abstract class PresenterBaseFragmentActivity<T extends ViewBinding,P extends BaseManager> extends AppBaseFragmentActivity<T>{
    protected P presenter;

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

}
