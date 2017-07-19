package com.lubin.chj.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;
import com.lubin.chj.R;
import com.lubin.chj.adapter.PcInfoAdapter;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.GetPcWZReturnBean;
import com.lubin.chj.modle.StorePcModelImpl;
import com.lubin.chj.presenter.IPresenter.IStorePresenter;
import com.lubin.chj.presenter.StorePresenterImpl;
import com.lubin.chj.service.BarcodeReceiver;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.AndroidUtils;
import com.lubin.chj.view.activity.VInterface.IStoreView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 16/9/17.
 */

public class StoreActivity extends BaseActivity implements IStoreView {
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.lv_store)
    ListView lvStore;
    @BindView(R.id.tv_succeedNum)
    TextView tvSucceedNum;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.cb_all)
    CheckBox cbAll;

    private EditText mEditText;

    List<PcInfo> pcInfoList = new ArrayList<>();
    private List<PcInfo> listEPC = new ArrayList<>();
    private List<String> container = new ArrayList<>();
    private List<Boolean> flags = new ArrayList<>();
    private PcInfoAdapter mAdapter;

    private ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();
    private IStorePresenter mPresenter;
    private String gwh;
    private String mFlag = "条码";

    private List<String> newDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        mPresenter = new StorePresenterImpl(this);
        initListView();
        initHeader();
    }

    @Override
    protected void onResume() {
        tbCommon.setTitle("存放");
        initService();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("条码")) {
            mFlag = "" + "电子标签";
            item.setTitle("" + "电子标签");
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
                finish();
            }
        });
    }

    private void initService() {
        mService.SetHandleFunc(msgHandler);// 设置回调函数
        BarcodeReceiver.setListener(new OnBroadCaseFinishListener() {
            @Override
            public void onBroadCaseFinish(String data) {
                if (mEditText != null) {
                    mEditText.setText(data);
                    mEditText.setSelection(data.length());
                } else {
                    addToList(data);
                }
            }
        });
    }

    private void initListView() {
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < flags.size(); i++) {
                    flags.set(i, isChecked);
                    String pch = listEPC.get(i).getPch();
                    if (isChecked) {
                        if (!container.contains(pch)) {
                            container.add(pch);
                            pcInfoList.add(listEPC.get(i));
                        }
                    } else {
                        container.remove(pch);
                        pcInfoList.remove(listEPC.get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        mAdapter = new PcInfoAdapter(this, listEPC, flags);
        lvStore.setAdapter(mAdapter);
        lvStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flags.get(position)) {
                    flags.set(position, false);
                    container.remove(listEPC.get(position).getPch());
                    pcInfoList.remove(listEPC.get(position));
                } else {
                    flags.set(position, true);
                    container.add(listEPC.get(position).getPch());
                    pcInfoList.add(listEPC.get(position));
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        timer.schedule(task, 100, 5000); // 1s后执行task,经过1s再次执行
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                getPcWZ();
            }
            super.handleMessage(msg);
        }
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    /*TimerTask stop = new TimerTask() {

        @Override
        public void run() {
            timer.cancel();
        }
    };*/

    List<PcInfo> getDeleteList = new ArrayList<>();

    @OnClick({R.id.btn_store, R.id.btn_query, R.id.btn_empty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_store:
                if (pcInfoList.size() == 0) return;
                boolean flag = false;
                for (Iterator<PcInfo> iterator = pcInfoList.iterator(); iterator.hasNext(); ) {
                    PcInfo pcInfo = iterator.next();
                    if (pcInfo.getGwbh() != null && !pcInfo.getGwbh().isEmpty()) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    showBarcodeScanDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StorePcModelImpl.mIngore = false;
                            gwh = mEditText.getText().toString();
                            mPresenter.StoreEpc(pcInfoList, gwh);
                            mEditText = null;
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                            builder.setMessage("选中的记录中已经有在库的，应清除");
                            builder.setNegativeButton("确定", null);
                            builder.show();
                        }
                    });
//                    ShowDialog("选中的记录中已经有在库的，应清除");
                }
                break;
            case R.id.btn_query:
                Intent intent = new Intent(this, CabinetActivityCs.class);
                intent.putExtra("size", String.valueOf(listEPC.size()));
                startActivity(intent);
                break;
            case R.id.btn_empty:
                for (String s : container) {
                    int i;
                    for (i = 0; i < listEPC.size(); i++) {
                        if (listEPC.get(i).getPch().equals(s)) {
                            break;
                        }
                    }
                    for (PcInfo pcInfo : pcInfoList) {
                        if (pcInfo.getPch().equals(listEPC.get(i).getPch())) {
                            pcInfoList.remove(pcInfo);
                            break;
                        }
                    }
                    listEPC.remove(i);
                    flags.remove(i);
                }
                container.clear();
                if (listEPC.size() == 0) cbAll.setChecked(false);
                mAdapter.notifyDataSetChanged();
                tvSucceedNum.setText(String.valueOf(listEPC.size()));
                break;
        }
    }

    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String str = msg.getData().getString("msg");
            if (null == str || 0 == str.length())
                return;
            if (msg.what == ScanServiceWithUHF.MSG_EPC) { // 标签内容
                String epc = str;
                addToList(epc);
            }
        }
    };

    @Override
    public void showDialogerror(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                builder.setMessage(result);
                builder.setNegativeButton("确定", null);
                builder.show();
            }
        });
    }

    private void addToList(final String epcCount) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] szInfo = epcCount.split(";");
                String epc = szInfo[0];
                int index = 0;
                for (index = 0; index < listEPC.size(); index++) {
                    PcInfo info = listEPC.get(index);
                    // list中有此EPC
                    if (epc.equals(info.getPch())) {
                        break;
                    }
                }

                if (index == listEPC.size()) {
                    PcInfo info = new PcInfo();
                    info.setPch(epc);
                    listEPC.add(info);
                    flags.add(false);
                    tvSucceedNum.setText(listEPC.size() + "");
                    newDatas.add(epc);
                }
                mAdapter.notifyDataSetChanged();
                lvStore.setSelection(lvStore.getCount() - 1);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (mFlag.equals("" + "电子标签"))
                    mService.inventory();
                else
                    mService.scanBarcode();
                //timer.schedule(task, 2000, 5000); // 1s后执行task,经过1s再次执行
            }
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (mFlag.equals("" + "电子标签"))
                    mService.pause();
                else
                    mService.stopScan();
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                mService.pause();
                mService.stopScan();
                finish();
            }
            //timer.schedule(stop, 3000, 60000); // 1s后执行task,经过1s再次执行
        }
        return true;
    }

    public void showBarcodeScanDialog(DialogInterface.OnClickListener listener) {
        mEditText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请扫描区域条码");
        builder.setView(mEditText);
        builder.setPositiveButton("确定", listener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEditText = null;
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getRepeatCount() == 0) {
                    if (keyCode == 275) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {
                            mService.scanBarcode();
                        } else {
                            mService.stopScan();
                        }
                    }
                }
                return false;
            }
        });
        builder.show();
    }

    @Override
    public void ShowContinueDialog(final String result) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                builder.setMessage(result);
                builder.setPositiveButton("继续存放", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StorePcModelImpl.mIngore = true;
                        mPresenter.StoreEpc(pcInfoList, gwh);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
    }

    List<PcInfo> deleteList = new ArrayList<>();
    List<PcInfo> deleteList1 = new ArrayList<>();
    List<Boolean> flags1 = new ArrayList<>();
    List<String> container1 = new ArrayList<>();

    @Override
    public void refreshData() {
        AndroidUtils.MainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < listEPC.size(); i++) {
                    for (PcInfo pcInfo1 : pcInfoList) {
                        if (listEPC.get(i).getPch().equals(pcInfo1.getPch())) {
                            deleteList.add(listEPC.get(i));
                            deleteList1.add(pcInfo1);
                            flags1.add(flags.get(i));
                            container1.add(listEPC.get(i).getPch());
                        }
                    }
                }
                container.removeAll(container1);
                listEPC.removeAll(deleteList);
                pcInfoList.removeAll(deleteList1);
                flags.removeAll(flags1);
                deleteList.clear();
                deleteList1.clear();
                mAdapter.notifyDataSetChanged();
                cbAll.setChecked(false);
                tvSucceedNum.setText(String.valueOf(listEPC.size()));
            }
        });
    }

    private void getPcWZ() {
        if (newDatas.size() > 0) {
            String pchList = "";
            for (int i = 0; i < newDatas.size(); i++) {
                if (i == 0) pchList = newDatas.get(i);
                else pchList = pchList + "," + newDatas.get(i);
            }
            mPresenter.GetPcWZ(pchList);
        }
    }

    @Override
    public void ShowStore(final List<GetPcWZReturnBean.ListBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (list.size() == 0) {
                    return;
                }
                for (PcInfo bean : listEPC) {
                    for (GetPcWZReturnBean.ListBean listBean : list) {
                        if (listBean.getPch().equals(bean.getPch())) {
                            bean.setGwbh(listBean.getGwbh());
                            bean.setQybh(listBean.getQybh());
                            bean.setPch(listBean.getPch());
                            bean.setGtbh(listBean.getGtbh());
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                newDatas.clear();
            }
        });
    }
}
