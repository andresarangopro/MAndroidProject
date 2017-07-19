package com.lubin.chj.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;
import com.lubin.chj.R;
import com.lubin.chj.adapter.CabinetAdapter;
import com.lubin.chj.bean.Light;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;
import com.lubin.chj.presenter.CabinetPresenterImpl;
import com.lubin.chj.service.BarcodeReceiver;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.KeyboardUtils;
import com.lubin.chj.view.activity.VInterface.ICabinetView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CabinetActivity extends BaseActivity implements ICabinetView<CabinetReturn.ListBean> {
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.lv_query_cabinet)
    ListView lvQueryCabinet;
    @BindView(R.id.et_query_cabinet)
    EditText etQueryCabinet;
    @BindView(R.id.btn_light)
    Button btnLight;
    @BindView(R.id.common_title)
    AppBarLayout mCommonTitle;

    private CabinetPresenterImpl mPresenter;
    private List<CabinetReturn.ListBean> list = new ArrayList<>();
    private CabinetAdapter mAdapter;
    public ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_query_cabinet);
        mPresenter = new CabinetPresenterImpl(this);
        ButterKnife.bind(this);
        initHeader();
        initListView();
        initService();
    }

    private void initHeader() {
        tbCommon.setNavigationIcon(R.mipmap.back);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leave();
            }
        });
    }

    private void initListView() {
        mAdapter = new CabinetAdapter(this, list);
        lvQueryCabinet.setAdapter(mAdapter);
        lvQueryCabinet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String qybh = list.get(i).getQybh();
                Intent intent = new Intent(CabinetActivity.this, QuYuActivity.class);
                intent.putExtra("qybh", qybh);
                startAnimActivity(intent);

            }
        });
    }

    private void initService() {
        BarcodeReceiver.setListener(new OnBroadCaseFinishListener() {
            @Override
            public void onBroadCaseFinish(String data) {
                if (etQueryCabinet.isEnabled())
                    etQueryCabinet.setText(data);
            }
        });
    }


    @Override
    protected void onResume() {
        tbCommon.setTitle("柜位查询");
        etQueryCabinet.requestFocus();
        super.onResume();
    }

    @OnClick({R.id.btn_query, R.id.btn_light})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                if (etQueryCabinet.getText().toString().equals("")) {
                    ShowDialog("输入柜位！");
                    return;
                }
                String s = btnLight.getText().toString();
                if (s.equals("亮灯")) {
                    String key = etQueryCabinet.getText().toString();
                    mPresenter.QueryCabinet(key);
                    KeyboardUtils.hideSoftInput(this);
                }
                break;
            case R.id.btn_light:
                if (etQueryCabinet.getText().toString().equals("")) {
                    ShowDialog("输入柜位！");
                    return;
                }
                String s1 = btnLight.getText().toString();
                String gwbh = etQueryCabinet.getText().toString();
                if (s1.equals("亮灯")) {
                    List<Light> lightList = getLightList(gwbh, true);
                    mPresenter.SetLight(lightList);
                } else {
                    List<Light> lightList = getLightList(gwbh, false);
                    mPresenter.SetLight(lightList);
                }
                break;
        }
    }

    @Override
    public void ShowCabinets(final List<CabinetReturn.ListBean> t) {
        if (list == null) return;
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                mAdapter.addAll(t);
            }
        });
    }

    @Override
    public void changeBtnStatus() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s1 = btnLight.getText().toString();
                if (s1.equals("亮灯")) {
                    btnLight.setText("灭灯");
                    etQueryCabinet.setFocusable(false);
                    etQueryCabinet.setEnabled(false);
                    KeyboardUtils.hideSoftInput(CabinetActivity.this);
                } else {
                    btnLight.setText("亮灯");
                    etQueryCabinet.setEnabled(true);
                    etQueryCabinet.setFocusable(true);
                    etQueryCabinet.setFocusableInTouchMode(true);
                    etQueryCabinet.requestFocus();
                    etQueryCabinet.findFocus();
                    KeyboardUtils.showSoftInput(CabinetActivity.this, etQueryCabinet);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (275 == keyCode)
            mService.scanBarcode();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (275 == keyCode)
            mService.stopScan();
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        leave();
    }

    private void leave() {
        String s1 = btnLight.getText().toString();
        if (s1.equals("灭灯")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("提示");
            builder.setMessage("灯还亮着，请先灭灯才能退出！");
            builder.setPositiveButton("确定", null);
            builder.show();
        } else {
            finish();
        }
    }

    private List<Light> getLightList(String gwbh, boolean b) {
        List<Light> list = new ArrayList<>();
        Light light = new Light();
        light.gwbh = gwbh;
        light.isOpen = b;
        list.add(light);
        return list;
    }

}
