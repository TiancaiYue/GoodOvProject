package com.shichuang.goodov.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shichuang.goodov.company.activity.LogoutDialogActivity;

/**
 * Created by Administrator on 2017/10/19.
 * 退出登录
 */

public class LogoutReceiver extends BroadcastReceiver {
    //这地方来判断一下是技工还是企业进行跳转
    @Override
    public void onReceive(Context context, Intent intent) {
        if(LogoutDialogActivity.isFront == false){  // 如果不在前台显示，则打开，防止多次打开
            Intent mIntent = new Intent(context, LogoutDialogActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }
    }
}
