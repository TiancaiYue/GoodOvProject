package com.shichuang.goodov.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.shichuang.goodov.interf.OnVideoStatusListener;
import com.shichuang.open.tool.RxToastTool;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/1/18.
 */

public class MyJZVideoPlayerStandard extends JZVideoPlayerStandard {
    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
        if(onVideoStatusListener != null){
            onVideoStatusListener.onStart();
        }
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        RxToastTool.showShort("onStatePause");
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }

    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
    }

    private OnVideoStatusListener onVideoStatusListener;

    public void setOnVideoStatusListener(OnVideoStatusListener onVideoStatusListener) {
        this.onVideoStatusListener = onVideoStatusListener;
    }
}
