package com.lubin.chj.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.lubin.chj.MApplication;
import com.lubin.chj.R;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.MyReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    private ScanServiceWithUHF mScanServiceWithUHF = ScanServiceWithUHF.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //initScanService();
        StartMoniter();
        initHeader();
    }

    private void initHeader() {
        tbCommon.setNavigationIcon(R.mipmap.back);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leave();
            }
        });
        tbCommon.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_out:
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        tbCommon.setTitle(MApplication.getInstance().getSpUtil().getName());
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScanServiceWithUHF.Close();
        StopMoniter();
    }

    // region 待机监控
    protected MyReceiver receiver = null;


    protected void StartMoniter() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyReceiver.ActionOffScreen);
        filter.addAction(MyReceiver.ActionOnScreen);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(receiver, filter);
    }

    protected void StopMoniter() {
        unregisterReceiver(receiver);
    }


    @OnClick({R.id.btn_store, R.id.btn_take, R.id.btn_adjust, R.id.btn_stockTake, R.id.btn_setting, R.id.btn_out, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_store:
                startActivity(new Intent(this, StoreActivity.class));
                break;
            case R.id.btn_take:
                startActivity(new Intent(this, PickActivity.class));
                break;
            case R.id.btn_adjust:
                startActivity(new Intent(this, AdjustActivity.class));
                break;
            case R.id.btn_stockTake:
                startActivity(new Intent(this, InventoryActivity.class));
                break;
            case R.id.btn_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.btn_out:
                MApplication.getInstance().getSpUtil().setPassword("");
                Intent intent = new Intent(this, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        leave();
    }

    private void leave() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示！");
        builder.setMessage("是否退出系统？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}