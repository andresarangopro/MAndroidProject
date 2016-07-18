package com.lide.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lide.app.R;
import com.lide.app.util.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_off_line)
    Button btnOffLine;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.view_login)
    LinearLayout viewLogin;
    @BindView(R.id.tb_login)
    Toolbar tbLogin;

    Boolean titleIsVisiable;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_login);
        ButterKnife.bind(this);
        initView();
        initHeader();
    }

    private void initView() {
        titleIsVisiable = true;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        viewLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    if (!titleIsVisiable) {
                        setVisiable();
                    }
                }
            }
        });

        etLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (titleIsVisiable) {
                        setVisiable();
                    }
                }
            }
        });

        etLoginUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (titleIsVisiable) {
                        setVisiable();
                    }
                }
            }
        });
    }

    private void initHeader() {
        tbLogin.setNavigationIcon(R.mipmap.back_login);
        tbLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @OnClick({R.id.btn_login, R.id.btn_off_line, R.id.view_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //1)取消焦点
                if (!titleIsVisiable) {
                    setVisiable();
                }
                //2)判空
                if (isEmpty(etLoginUsername, etLoginPassword)) {
                    return;
                }
                //3)判网
                if (!NetUtil.isNetworkAvailable(this)) {
                    ShowToast("网络不给力");
                    return;
                }
                break;
            case R.id.btn_off_line:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.view_login:
                view.requestFocus();
                break;
        }
    }

    public void setVisiable() {
        if (titleIsVisiable) {
            tbLogin.setVisibility(View.GONE);
            titleIsVisiable = false;
        } else {
            tbLogin.setVisibility(View.VISIBLE);
            titleIsVisiable = true;
        }
    }

//    public void toggleHideyBar() {
//
//        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
//        int newUiOptions = uiOptions;
//        boolean isImmersiveModeEnabled =
//                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
//        if (isImmersiveModeEnabled) {
//            Log.i("Tag", "Turning immersive mode mode off. ");
//        } else {
//            Log.i("Tag", "Turning immersive mode mode on.");
//        }
//        if (Build.VERSION.SDK_INT >= 16) {
//            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
//        }
//        if (Build.VERSION.SDK_INT >= 18) {
//            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        }
//
//        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
//    }
}
