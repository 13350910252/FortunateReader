package com.yinp.fortunatereader.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.yinp.fortunatereader.R;
import com.yinp.tools.utils.FitScreenUtil;
import com.yinp.tools.utils.StatusBarUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AppBaseFragmentActivity<T extends ViewBinding> extends BaseFragmentActivity {
    public T binding;
    //记录按下,防止连续点击
    public boolean isClick = false;
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isClick = false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingLayout();
        FitScreenUtil.setCustomDensity(mActivity, getApplication());
        StatusBarUtil.setTranslucentStatus(mActivity);
    }
    public void bindingLayout() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                binding = (T) method.invoke(null, getLayoutInflater());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            setContentView(binding.getRoot());
        }
    }

    protected abstract void initViews();
    protected void bindData() {

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private long lastClickTime = 0;

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= 300;
    }

    /**
     * 界面跳转
     */
    public void goToActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 界面跳转
     */
    public void goToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 界面跳转
     */
    public void goToActivity(Class clazz, Bundle bundle, int requestcode) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestcode);
    }

    /**
     * 设置占位View的高度，主要是用于浸入式状态栏
     *
     * @param height 状态栏高度
     */
    protected void setStatusBarHeight(int height) {
        View view = findViewById(R.id.view_status);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }
}
