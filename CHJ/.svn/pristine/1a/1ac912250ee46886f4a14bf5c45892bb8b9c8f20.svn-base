package com.lubin.chj.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.lubin.chj.R;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.view.activity.Fragment.FragmentBase;
import com.lubin.chj.view.activity.Fragment.PickCertificateFragment;
import com.lubin.chj.view.activity.Fragment.PickDirectFragment;
import com.lubin.chj.view.activity.Fragment.PickInvoiceFragment;
import com.lubin.chj.view.activity.Fragment.PickInvoiceOderFragment;
import com.lubin.chj.view.activity.Fragment.PickMainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PickActivity extends BaseActivity {
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.ll_pick)
    LinearLayout llPick;
    InputMethodManager imm;
    private List<FragmentBase> mFragments;

    private int current;
    public ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();

    public String mFlag = "条码";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_pick);
        ButterKnife.bind(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initHeader();
        initFragment();
    }

    @Override
    protected void onResume() {
        tbCommon.setTitle("拣货管理");
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (current == 0 || current == 3) {
            menu.findItem(R.id.action_model).setVisible(false);
        } else {
            menu.findItem(R.id.action_model).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("条码")) {
            mFlag = "电子标签";
            item.setTitle("电子标签");
        } else {
            mFlag = "条码";
            item.setTitle("条码");
        }
        return super.onOptionsItemSelected(item);
    }

    private void initHeader() {
        setSupportActionBar(tbCommon);
        tbCommon.setNavigationIcon(R.mipmap.back);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragments.get(current).finishByBackIcon();
            }
        });
    }

    private void initFragment() {
        current = 0;
        mFragments = new ArrayList<>();
        mFragments.add(new PickMainFragment());
        mFragments.add(new PickCertificateFragment());
        mFragments.add(new PickDirectFragment());
        mFragments.add(new PickInvoiceOderFragment());
        mFragments.add(new PickInvoiceFragment());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mFragments.get(0), "0")
                .add(R.id.fragment_container, mFragments.get(1), "1")
                .add(R.id.fragment_container, mFragments.get(2), "2")
                .add(R.id.fragment_container, mFragments.get(3), "3")
                .add(R.id.fragment_container, mFragments.get(4), "4")
                .show(mFragments.get(0))
                .hide(mFragments.get(1))
                .hide(mFragments.get(2))
                .hide(mFragments.get(3))
                .hide(mFragments.get(4))
                .commit();
    }

    @OnClick(R.id.ll_pick)
    public void onClick(View view) {
        llPick.requestFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void changeFragment(int index) {
        getSupportFragmentManager().beginTransaction()
                .show(mFragments.get(index))
                .hide(mFragments.get(current))
                .commit();
        invalidateOptionsMenu();
        current = index;
        switch (index) {
            case 0:
                tbCommon.setTitle("拣货管理");
                ((PickMainFragment) mFragments.get(0)).initService();
                break;
            case 1:
                tbCommon.setTitle("采购单拣货");
//                ((PickCertificateFragment) mFragments.get(1)).getLight();
                break;
            case 2:
                tbCommon.setTitle("直接拣货");
                break;
            case 4:
                tbCommon.setTitle("配货单拣货");
//                ((PickInvoiceFragment) mFragments.get(4)).getLight();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (current == 1 || current == 2 || current == 4) {
                    if (mFlag.equals("电子标签"))
                        mService.inventory();
                    else
                        mService.scanBarcode();
                }
            }
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (current == 1 || current == 2 || current == 4) {
                    if (mFlag.equals("电子标签"))
                        mService.pause();
                    else
                        mService.stopScan();
                }
            }
        }
        return true;
    }

    public String getmFlag() {
        return mFlag;
    }

    @Override
    public void onBackPressed() {
        if (current != 0)
            mFragments.get(current).finishByBackBtn();
        else
            finish();
    }
}