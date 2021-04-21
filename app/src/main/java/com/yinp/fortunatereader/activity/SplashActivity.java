package com.yinp.fortunatereader.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.yinp.fortunatereader.R;
import com.yinp.fortunatereader.base.activity.AppBaseActivity;

import java.util.List;


public class SplashActivity extends AppBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        agree();
        initViews();
    }

    ImageView iv_splash;

    @Override
    protected void initViews() {
        iv_splash = findViewById(R.id.iv_splash);
    }

    private void agree() {
        applyPermission();
    }

    private void applyPermission() {
        XXPermissions.with(this)
                .permission(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE}) //不指定权限则自动获取清单中的危险权限
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

//    /**
//     * 跑应用的逻辑
//     */
//    boolean isGo = true;
//
//    private void runApp() {
//        if (isGo) {
//            isGo = false;
//            new Thread(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                goToActivity(MainActivity.class);
//            }).start();
//        }
//    }

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
