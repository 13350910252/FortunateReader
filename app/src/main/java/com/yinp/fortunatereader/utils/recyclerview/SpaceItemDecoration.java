package com.yinp.fortunatereader.utils.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.yinp.fortunatereader.utils.save.AppUtils;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int count;  //一排的的数量
    private int firLeft;  //一排的的数量
    private int endRight;  //一排的的数量
    private int top;  //一排的的数量
    private int other;  //一排的的数量
    private Context context;

    /**
     * 传入dp
     *
     * @param context
     * @param count
     * @param firLeft  每一排第一个的左边距
     * @param endRight 每一排最后的右边距
     * @param top      距离上边的高度
     * @param other    比如四个，中间两个相距的距离
     */
    public SpaceItemDecoration(Context context, int count, int firLeft, int endRight, int top, int other) {
        this.count = count;
        this.firLeft = firLeft;
        this.endRight = endRight;
        this.top = top;
        this.other = other;
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % count == 1) {
            outRect.left = (int) AppUtils.dpToPx(context, firLeft);
            outRect.right = (int) AppUtils.dpToPx(context, other / 2f);
        } else if (parent.getChildAdapterPosition(view) % count == 0) {
            outRect.left = (int) AppUtils.dpToPx(context, other / 2f);
            outRect.right = (int) AppUtils.dpToPx(context, endRight);
        } else {
            outRect.left = (int) AppUtils.dpToPx(context, other / 2f);
            outRect.right = (int) AppUtils.dpToPx(context, other / 2f);
        }
        outRect.top = (int) AppUtils.dpToPx(context, top);
    }

}