package com.lide.app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.extend.util.KeyboardUtils;
import android.extend.util.service.VersionUtils;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.persistence.util.NetUtil;
import com.lide.app.presenter.LoginPresenterImpl;
import com.lide.app.presenter.PInterface.ILoginPresenter;
import com.lide.app.presenter.VersionPresenter;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.VInterface.ILoginView;
import com.lubin.bean.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView, IDataFragmentView<List<LinkedTreeMap<String, String>>> {

    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.view_login)
    LinearLayout viewLogin;
    @BindView(R.id.tb_login)
    Toolbar tbLogin;
    @BindView(R.id.et_login_warehouseName)
    EditText etLoginWarehouseCode;

    Boolean titleIsVisiable;
    InputMethodManager imm;

    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.offline_enter)
    TextView offlineEnter;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;

    private ILoginPresenter mPresenter;
    private boolean isAtNet;

    private String offline = "off";
    private VersionPresenter versionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_login);
        ButterKnife.bind(this);
        init();
        initView();
        initHeader();
        versionPresenter = new VersionPresenter(this);
    }

    @Override
    protected void onResume() {
        mPresenter.getNewestUser();
        tvLogin.setText(isAtNet ? "在线模式" : "离线模式");
        tbLogin.setTitle("");
        super.onResume();
    }

    private void init() {
        mPresenter = new LoginPresenterImpl(this);
        isAtNet = getIntent().getBooleanExtra("isAtNet", false);
        offline = getIntent().getStringExtra("offline");
    }

    private void initView() {
        //添加关闭软键盘逻辑显示头布局
        titleIsVisiable = true;
        VersionUtils.Version version = VersionUtils.getAppVersionName(this);
        tvVersionName.setText(version.versionName);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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
        //获取焦点是隐藏头布局
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
        //获取焦点是隐藏头布局
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
        //获取焦点是隐藏头布局
        etLoginWarehouseCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (titleIsVisiable) {
                        setVisiable();
                    }
                }
            }
        });
        etLoginWarehouseCode.setVisibility(isAtNet ? View.VISIBLE : View.GONE);

        if (offline != null && offline.equals("offline")) {
            offlineEnter.setVisibility(View.VISIBLE);
        }

        offlineEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EnterActivity.class);
                startAnimActivity(intent);
                finish();
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

    @OnClick({R.id.btn_login, R.id.view_login})
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
                if (isAtNet) {
                    loginAtNet();
                } else {
                    loginAtOffLine();
                }
                break;
            case R.id.view_login:
                view.requestFocus();
                break;
        }
    }

    private void loginAtNet() {
        //3)判网
        if (!NetUtil.isNetworkAvailable(this)) {
            ShowToast("网络不给力");
            return;
        }
        startProgressDialog("登录中...");
        Map<String, String> map = new HashMap<>();
        map.put("userName", etLoginUsername.getText().toString());
        map.put("password", etLoginPassword.getText().toString());
        map.put("warehouseCode", etLoginWarehouseCode.getText().toString());
        mPresenter.loginAtNet(map);
    }

    private void loginAtOffLine() {
        Map<String, String> map = new HashMap<>();
        map.put("userName", etLoginUsername.getText().toString());
        map.put("password", etLoginPassword.getText().toString());
        mPresenter.LoginAtOffLine(map);
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

    @Override
    public void showResult(final String result) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                stopProgressDialog(result);
            }
        });
    }

    @Override
    public void showNewestUser(User user) {
        etLoginWarehouseCode.setText(user.getWarehouseCode());
        etLoginUsername.setText(user.getUserName());
    }

    @Override
    public void onLoginFinishAtNet() {
        setResult(RESULT_OK);
        KeyboardUtils.hideSoftInput(this);
        versionPresenter.queryVersionJsonUrl();
    }

    @Override
    public void onLoginFinishAtOffLine() {
        startAnimActivity(new Intent(this, EnterActivity.class));
        finish();
    }

    @Override
    public void changeMode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("离线登录失败,是否转为在线模式");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvLogin.setText("在线模式");
                etLoginWarehouseCode.setVisibility(View.VISIBLE);
                isAtNet = true;
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    public void ShowToast(String text) {
        super.ShowToast(text);
        if (offline != null) startAnimActivity(new Intent(this, EnterActivity.class));
        else finish();
    }

    @Override
    public void ShowData(final List<LinkedTreeMap<String, String>> linkedTreeMaps) {
        builder.setMessage("有新版本，可更新！");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = linkedTreeMaps.get(0).get("url");
                new VersionUtils(LoginActivity.this).version(url);
            }
        });
        builder.show();
    }
}
