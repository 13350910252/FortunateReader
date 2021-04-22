package com.yinp.fortunatereader.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yinp.fortunatereader.R;
import com.yinp.fortunatereader.base.activity.AppBaseFragmentActivity;
import com.yinp.fortunatereader.databinding.ActivityMainBinding;
import com.yinp.fortunatereader.fragment.tab_fragment.OneFragment;
import com.yinp.fortunatereader.fragment.tab_fragment.ThreeFragment;
import com.yinp.fortunatereader.fragment.tab_fragment.TwoFragment;
import com.yinp.tools.utils.StatusBarUtil;

public class MainActivity extends AppBaseFragmentActivity<ActivityMainBinding> {
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private Fragment currFragment;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }
    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        fragmentManager = getSupportFragmentManager();

//        binding.headerLayout.headerBackImg.setVisibility(View.GONE);
        binding.headerLayout.headerCenterTitle.setText("首页");
        binding.rgBottomGroup.setOnCheckedChangeListener(new CheckedListener());
    }

    @Override
    protected void bindData() {
        super.bindData();
        changeFragment(R.id.rb_one);
    }

    private class CheckedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_one) {
                changeFragment(R.id.rb_one);
            } else if (checkedId == R.id.rb_two) {
                changeFragment(R.id.rb_two);
            } else if (checkedId == R.id.rb_three) {
                changeFragment(R.id.rb_three);
            }
        }
    }

    private void changeFragment(int checkedId) {
        transaction = fragmentManager.beginTransaction();
        if (checkedId == R.id.rb_one) {
            if (oneFragment == null) {
                oneFragment = new OneFragment();
                transaction.add(R.id.fl_page, oneFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = oneFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_two) {
            if (twoFragment == null) {
                twoFragment = new TwoFragment();
                transaction.add(R.id.fl_page, twoFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = twoFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_three) {
            if (threeFragment == null) {
                threeFragment = new ThreeFragment();
                transaction.add(R.id.fl_page, threeFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = threeFragment;
            transaction.show(currFragment);
            transaction.commit();
        }
    }
}