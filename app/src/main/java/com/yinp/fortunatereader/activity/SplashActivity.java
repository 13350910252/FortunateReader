package com.yinp.fortunatereader.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.yinp.fortunatereader.R;
import com.yinp.fortunatereader.base.activity.AppBaseFragmentActivity;
import com.yinp.fortunatereader.databinding.ActivitySplashBinding;
import com.yinp.tools.utils.StatusBarUtil;

import java.util.List;

/**
 * 启动叶面
 */
public class SplashActivity extends AppBaseFragmentActivity<ActivitySplashBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    ImageView iv_splash;

    //    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        iv_splash = findViewById(R.id.iv_splash);


        agree();
    }

    private void agree() {
        applyPermission();
    }

    private void applyPermission() {
        XXPermissions.with(this)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //不指定权限则自动获取清单中的危险权限
                .permission(Manifest.permission.READ_PHONE_STATE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        initAnimation();

                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        initAnimation();

                    }
                });
    }

    private void initAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f
                , Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        if (iv_splash != null) {
            iv_splash.startAnimation(scaleAnimation);
        }

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                goToActivity(MainActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
