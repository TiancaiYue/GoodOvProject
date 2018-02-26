package com.shichuang.goodov.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import goodov.shichuang.com.goodovproject.R;

/**
 * Created by zmy on 2018/1/15.
 */

public class GetIntegralSuccessDialog extends BaseDialog {
    private TextView tvTitle;
    private TextView tvMessage;
    private Button btnContent;
    private Button btnCancel;

    public GetIntegralSuccessDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public GetIntegralSuccessDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public GetIntegralSuccessDialog(Context context) {
        super(context);
        initView();
    }

    public GetIntegralSuccessDialog(Activity context) {
        super(context);
        initView();
    }

    public GetIntegralSuccessDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public TextView getTvMessage() {
        return tvMessage;
    }

    public void setTvMessage(TextView tvMessage) {
        this.tvMessage = tvMessage;
    }

    public Button getBtnContent() {
        return btnContent;
    }

    public void setBtnContent(Button btnContent) {
        this.btnContent = btnContent;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public void setSureListener(View.OnClickListener sureListener) {
        btnContent.setOnClickListener(sureListener);
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        btnCancel.setOnClickListener(cancelListener);
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_activity_details_layout, null);
        tvMessage = (TextView) dialogView.findViewById(R.id.tv_message);
        btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        btnContent = (Button) dialogView.findViewById(R.id.btn_content);
        tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        setContentView(dialogView);
    }

    @Override
    public void show() {
        super.show();
        String title = tvTitle.getText().toString().trim();
        if ("".equals(title)) {
            tvTitle.setVisibility(View.GONE);
        }
    }
}
