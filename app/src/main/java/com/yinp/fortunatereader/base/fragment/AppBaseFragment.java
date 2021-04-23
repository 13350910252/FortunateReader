package com.yinp.fortunatereader.base.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.yinp.tools.utils.FitScreenUtil;
import com.yinp.tools.utils.StatusBarUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AppBaseFragment<T extends ViewBinding> extends BaseFragment{
    //记录按下,防止连续点击
    public boolean isClick = false;
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isClick = false;
        }
    };

    public T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bindingLayout();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FitScreenUtil.setCustomDensity(mActivity, mActivity.getApplication());
        StatusBarUtil.setTranslucentStatus(mActivity);
    }

    private View bindingLayout() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                binding = (T) method.invoke(null, getLayoutInflater());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return binding.getRoot();
    }

    protected abstract void initViews(View view);

    protected void bindData() {
    }
    /**
     * 跳转界面
     */
    public void goToActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }
    }

    /**
     * 跳转界面
     */
    public void goToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }
    }

    /**
     * 跳转界面
     */
    public void goToActivity(Class clazz, Bundle bundle, int code) {
        Intent intent = new Intent(getContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, code);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }
    }
}
