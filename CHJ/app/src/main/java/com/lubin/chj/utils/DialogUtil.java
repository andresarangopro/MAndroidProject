package com.lubin.chj.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lubin.chj.R;


/**
 * @author DaiJiCheng
 * @time 2016/7/22  11:39
 * @desc ${展示带有editText的自定义对话框}
 */
public class DialogUtil {
    public static Button bt_submit; //确定按钮
    public static Button bt_cancel; //取消按钮
    public static EditText et_dialog; //输入文本内容
    public static TextView tv_title;
    private static View view;
    private static Dialog mDialog;

    /**
     * Resource 输入会话框布局文件ID
     */

    public static void initView(Activity activity) {
        view = View.inflate(activity, R.layout.dialog, null);
        bt_submit = (Button) view.findViewById(R.id.btn_submit_dialog);
        bt_cancel = (Button) view.findViewById(R.id.btn_cancel_dialog);
        et_dialog = (EditText) view.findViewById(R.id.et_dialog);
        tv_title = (TextView) view.findViewById(R.id.tv_dialog);
        mDialog = new Dialog(activity, R.style.selectorDialog);
        mDialog.setContentView(view);
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.9); //设置宽度
        mDialog.getWindow().setAttributes(lp);
    }

    public static void showDialog() {
        mDialog.show();
    }

    public static void dismiss() {
        mDialog.dismiss();
    }

    public static void setTitleText(String text, String hint) {
        tv_title.setText(text);
        tv_title.setHint(hint);
    }

    public static void setSubmitlistener(View.OnClickListener listener) {
        bt_submit.setOnClickListener(listener);
    }

    public static void setCancellistener(View.OnClickListener listener) {
        bt_cancel.setOnClickListener(listener);
    }

    public static String getText() {
        return et_dialog.getText().toString();
    }

    public static void setEditTextGone() {
        et_dialog.setVisibility(View.GONE);
    }

    public static void setBtnText(String left, String right) {
        bt_submit.setText(left);
        bt_cancel.setText(right);
    }
}
