package com.yinp.fortunatereader.mvp;

import com.yinp.fortunatereader.web.retrofit.BaseRetrofitData;

public interface CBaseView {
    /**
     * 显示dialog
     */
    void showLoading();

    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg
     */
    void showError(String msg);

    /**
     * 错误码
     */
    void onErrorCode(BaseRetrofitData model);
}
