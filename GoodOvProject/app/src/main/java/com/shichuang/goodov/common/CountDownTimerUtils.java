package com.shichuang.goodov.common;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/23.
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    private TextView yTextView;

    public CountDownTimerUtils(long millisInFuture, long countDownInterval, TextView mTextView, TextView yTextView) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
        this.yTextView = yTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setText(DateUtils.secToTime(millisUntilFinished));// 设置倒计时时间
    }

    @Override
    public void onFinish() {
        yTextView.setVisibility(View.INVISIBLE);
        mTextView.setText("已结束");
        mTextView.setTextColor(Color.parseColor("#565656"));
        mTextView.setTextSize(13);
    }
}
