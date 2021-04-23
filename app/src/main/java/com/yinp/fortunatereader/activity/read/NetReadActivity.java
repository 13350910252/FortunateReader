package com.yinp.fortunatereader.activity.read;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.yinp.fortunatereader.R;
import com.yinp.fortunatereader.base.activity.AppBaseFragmentActivity;
import com.yinp.fortunatereader.databinding.ActivityNetReadBinding;
import com.yinp.fortunatereader.databinding.ItemShowTextBinding;
import com.yinp.fortunatereader.utils.save.AppUtils;
import com.yinp.tools.fragment_dialog.BaseDialogFragment;
import com.yinp.tools.fragment_dialog.CommonDialogFragment;
import com.yinp.tools.fragment_dialog.DialogFragmentHolder;
import com.yinp.tools.fragment_dialog.ViewConvertListener;
import com.yinp.tools.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class NetReadActivity extends AppBaseFragmentActivity<ActivityNetReadBinding> {
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        initViews();
    }

    private ItemShowTextBinding showTextBinding;
    private List<String> contentList = new ArrayList<>();

    @SuppressLint("WrongConstant")
    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.header.clTitleBar.setVisibility(View.GONE);
        title = getIntent().getStringExtra("title");
        title = TextUtils.isEmpty(title) ? "" : title;

        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");
        contentList.add("ffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdfffdsfdf");

        binding.vpContent.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                showTextBinding = ItemShowTextBinding.inflate(LayoutInflater.from(mContext), parent, false);
                return new RecyclerView.ViewHolder(showTextBinding.getRoot()) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
            }

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                showTextBinding.tvContent.setText(contentList.get(position));
                showTextBinding.tvContent.setOnTouchListener((v, event) -> {
                    int width = (int) AppUtils.getWidthPixels(mContext);
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            int x = (int) event.getRawX();
                            if (x > width / 3 && x < width / 3 * 2) {
                                if (mDialogFragment != null && mDialogFragment.isAdded()) {
                                    mDialogFragment.dismiss();
                                } else {
                                    showFunction();
                                }
                            }
                            break;
                    }
                    return true;
                });
            }

            @Override
            public int getItemCount() {
                return contentList.size();
            }
        });
        binding.vpContent.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    }

    /**
     * 获取当前章节
     */
    private void getCurChapter() {

    }

    BaseDialogFragment mDialogFragment;
    boolean isShow = false;

    private void showFunction() {
        isShow = true;
        CommonDialogFragment.newInstance(this).setLayoutId(R.layout.dialog_novel_control).setViewConvertListener(new ViewConvertListener() {
            @Override
            public void convertView(DialogFragmentHolder holder, BaseDialogFragment dialogFragment) {
                mDialogFragment = dialogFragment;
            }
        }).setGravity(BaseDialogFragment.BOTTOM).setAnimStyle(R.style.BottomDialogAnimation).show(getSupportFragmentManager());
    }
}