package com.yinp.tools.fragment_dialog;

import android.content.Context;

import androidx.annotation.LayoutRes;

/**
 * 公用样式Dialog
 */
public class CommonDialogFragment extends BaseDialogFragment {
    private ViewConvertListener viewConvertListener = null;

    public static CommonDialogFragment newInstance(Context context) {
        return newInstance(context, false);
    }

    public static CommonDialogFragment newInstance(Context context, boolean setKeyListener) {
        CommonDialogFragment dialog = new CommonDialogFragment();
        mContext = context;
        mSetKeyListener = setKeyListener;
        return dialog;
    }

    /**
     * 设置Dialog布局
     *
     * @param layoutId
     * @return
     */
    public CommonDialogFragment setLayoutId(@LayoutRes int layoutId) {
        this.mLayoutResId = layoutId;
        return this;
    }

    /**
     * 用于通讯
     *
     * @param convertListener
     * @return
     */
    public CommonDialogFragment setViewConvertListener(ViewConvertListener convertListener) {
        viewConvertListener = convertListener;
        return this;
    }

    @Override
    protected int updateLayoutId() {
        return mLayoutResId;
    }

    /**
     * 通过id处理各种事件
     *
     * @param holder
     * @param dialog
     */
    @Override
    public void convertView(DialogFragmentHolder holder, BaseDialogFragment dialog) {
        if (viewConvertListener != null) {
            viewConvertListener.convertView(holder, dialog);
        }
    }
}