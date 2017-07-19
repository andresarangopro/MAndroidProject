package com.lubin.chj.view.activity.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;
import com.lubin.chj.MApplication;
import com.lubin.chj.R;
import com.lubin.chj.adapter.BaseListAdapter;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.presenter.AdjustPresenter;
import com.lubin.chj.service.BarcodeReceiver;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.KeyboardUtils;
import com.lubin.chj.utils.SharePreferenceUtil;
import com.lubin.chj.view.activity.AdjustActivity;
import com.lubin.chj.view.activity.CabinetActivityCs;
import com.lubin.chj.view.activity.VInterface.IAdjustView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdjustPickFragment extends FragmentBase implements IAdjustView<List<QueryPcReturn.ListBean>> {

    @BindView(R.id.lv_adjust)
    ListView lvAdjust;
    @BindView(R.id.tv_succeedNum)
    TextView tvSucceedNum;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.et_query_barcode)
    EditText etQueryBarcode;

    private View mView;
    private ScanServiceWithUHF mService;
    private AdjustAdapter mAdapter;
    private List<Boolean> flags;
    private AdjustActivity mActivity;
    private Timer mTimer;

    private List<QueryPcReturn.ListBean> mList = new ArrayList<>();
    private List<String> newDatas = new ArrayList<>();
    private AdjustPresenter mPresenter;
    private EditText mEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_adjust, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (AdjustActivity) getActivity();
        mService = mActivity.mService;
        mPresenter = new AdjustPresenter(this);
        initView();
        initListView();
        initTimer();
        initService();
        return mView;
    }


    private void initView() {
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = cbAll.isChecked();
                for (int i = 0; i < flags.size(); i++) {
                    flags.set(i, checked);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initListView() {
        flags = new ArrayList<>();
        mAdapter = new AdjustAdapter(getActivity(), mList, flags);
        lvAdjust.setAdapter(mAdapter);
        lvAdjust.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flags.get(position)) {
                    flags.set(position, false);
                } else {
                    flags.set(position, true);
                }
                mAdapter.notifyDataSetChanged();
                int i;
                for (i = 0; i < flags.size(); i++) {
                    if (!flags.get(i)) {
                        break;
                    }
                }
                if (i == flags.size())
                    cbAll.setChecked(true);
                else
                    cbAll.setChecked(false);
            }
        });
    }

    private void initTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onScanStop();
            }
        }, 0, 2500);
    }

    private SharePreferenceUtil mSpUtil = MApplication.getInstance().getSpUtil();
    String barCode = "0000000000";

    private void initService() {
        if (mSpUtil.getBarCode() != null && !mSpUtil.getBarCode().isEmpty())
            barCode = mSpUtil.getBarCode();

        mService.SetHandleFunc(msgHandler);// 设置回调函数
        BarcodeReceiver.setListener(new OnBroadCaseFinishListener() {
            @Override
            public void onBroadCaseFinish(String data) {
                if (!data.equals(barCode)) {
                    if (mEditText != null) {
                        mEditText.setText(data);
                        mEditText.setSelection(data.length());
                    } else {
                        addToList(data);
                    }
                }
            }
        });
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

    private void addToList(final String epcCount) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] szInfo = epcCount.split(";");
                String epc = szInfo[0];

                int index = 0;
                for (index = 0; index < mList.size(); index++) {
                    QueryPcReturn.ListBean listBean = mList.get(index);
                    // list中有此EPC
                    if (epc.equals(listBean.getPch())) {
                        break;
                    }
                }

                if (index == mList.size()) {
                    QueryPcReturn.ListBean listBean = new QueryPcReturn.ListBean();
                    listBean.setPch(epc);
                    mList.add(listBean);
                    flags.add(false);
                    newDatas.add(epc);
                    cbAll.setChecked(false);
                }
                tvSucceedNum.setText(String.valueOf(mList.size()));
                mAdapter.notifyDataSetChanged();
                lvAdjust.setSelection(lvAdjust.getCount() - 1);
            }
        });
    }

    @Override
    public void ShowDialog(String result) {
        mActivity.ShowDialog(result);
    }

    @OnClick({R.id.bt_adjust_take, R.id.bt_adjust_finish, R.id.bt_adjust_query, R.id.bt_adjust_clear, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_adjust_take:
                if (mList.size() == 0) return;
                setQybh();
                break;
            case R.id.bt_adjust_finish:
                getActivity().finish();
                break;
            case R.id.bt_adjust_query:
                Intent intent = new Intent(mActivity, CabinetActivityCs.class);
                intent.putExtra("size", "0");
                startAnimActivity(intent);
                break;
            case R.id.bt_adjust_clear:
                clearData();
                break;
            case R.id.btn_query:
                String s = etQueryBarcode.getText().toString();
                if (!s.equals("") && !s.equals(" ") && !s.isEmpty()) {
                    addToList(s);
                    etQueryBarcode.setText("");
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                break;
        }
    }

    private void clearData() {
        List<QueryPcReturn.ListBean> container = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (flags.get(i)) {
                container.add(mList.get(i));
            }
        }
        for (QueryPcReturn.ListBean bean : container) {
            int position = mList.indexOf(bean);
            mList.remove(position);
            flags.remove(position);
        }
        cbAll.setChecked(false);
        tvSucceedNum.setText(String.valueOf(mList.size()));
        mAdapter.notifyDataSetChanged();
    }

    private void setQybh() {
        mEditText = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("请扫描区域条码");
        builder.setView(mEditText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String qybh = mEditText.getText().toString();
                mEditText = null;
                if (qybh.equals(" ") || qybh.equals("")) {
                    mActivity.ShowDialog("请输入新的区域编号！");
                    return;
                }
                String pchs = getPchs();
                if (pchs == null) return;
                mPresenter.MovePC(pchs, qybh);
            }
        });
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

    private String getPchs() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mList.size(); i++) {
            QueryPcReturn.ListBean bean = mList.get(i);
            sb.append(bean.getPch());
            sb.append(",");
        }
        if (sb.length() == 0) {
            return null;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    @Override
    public void onScanStop() {
        super.onScanStop();
        if (newDatas.size() == 0) return;
        StringBuffer sb = new StringBuffer();
        for (String data : newDatas) {
            sb.append(data + ",");
        }
        String pcs = sb.toString();
        if (!pcs.equals(""))
            mPresenter.QueryPc(pcs);
    }

    @Override
    public void finishByBackBtn() {
        getActivity().finish();
    }

    @Override
    public void finishByBackIcon() {
        getActivity().finish();
    }

    @Override
    public void ShowPc(final List<QueryPcReturn.ListBean> listBeen) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (listBeen.size() == 0) {
                    return;
                }
                for (QueryPcReturn.ListBean bean : mList) {
                    for (QueryPcReturn.ListBean listBean : listBeen) {
                        if (listBean.getPch().equals(bean.getPch())) {
                            bean.setGwbh(listBean.getGwbh());
                            bean.setQybh(listBean.getQybh());
                            bean.setWlh(listBean.getWlh());
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                newDatas.clear();
            }
        });
    }

    class AdjustAdapter extends BaseListAdapter<QueryPcReturn.ListBean> {
        List<Boolean> flags;

        public AdjustAdapter(Context context, List<QueryPcReturn.ListBean> list, List<Boolean> flags) {
            super(context, list);
            this.flags = flags;
        }

        @Override
        public View bindView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_lv_pick, parent, false);

            }
            final QueryPcReturn.ListBean listBean = getList().get(position);
            CheckBox cbQick = (CheckBox) convertView.findViewById(R.id.cb_isPick);
            TextView index = (TextView) convertView.findViewById(R.id.tv_index);
            TextView wlh = (TextView) convertView.findViewById(R.id.tv_wlh);
            TextView pch = (TextView) convertView.findViewById(R.id.tv_pch);
            TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
            TextView qybh = (TextView) convertView.findViewById(R.id.tv_qybh);
            if (flags.size() == position) {
                flags.add(true);
            }
            cbQick.setChecked(flags.get(position));
            index.setText(String.valueOf(position + 1));
            if (listBean.getWlh() == null) {
                wlh.setText("");
            } else {
                wlh.setText(listBean.getWlh().toString());
            }
            if (listBean.getGwbh() == null) {
                gwh.setText("");
            } else {
                gwh.setText(listBean.getGwbh().toString());
            }
            if (listBean.getQybh() == null) {
                qybh.setText("");
            } else {
                String qy = listBean.getQybh().toString();
                qybh.setText(qy.substring(qy.length() - 2, qy.length()));
            }

            pch.setText(listBean.getPch());

            return convertView;
        }

    }
}
