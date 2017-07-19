package com.lubin.chj.view.activity.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;
import com.lubin.chj.MApplication;
import com.lubin.chj.R;
import com.lubin.chj.adapter.GwAdapter;
import com.lubin.chj.adapter.PickByCertificateAdapter;
import com.lubin.chj.bean.Gw;
import com.lubin.chj.bean.Light;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.GetPzhDetailResult;
import com.lubin.chj.presenter.PickPresenterImpl;
import com.lubin.chj.service.BarcodeReceiver;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.SharePreferenceUtil;
import com.lubin.chj.view.activity.BaseActivity;
import com.lubin.chj.view.activity.PickActivity;
import com.lubin.chj.view.activity.VInterface.IPickView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickCertificateFragment extends FragmentBase implements IPickView<GetPzhDetailResult> {
    @BindView(R.id.lv_picking)
    ListView lvPicking;
    @BindView(R.id.bt_picking_direct_light)
    Button btPickingDirectLight;
    @BindView(R.id.cb_all)
    CheckBox cbAll;

    @BindView(R.id.sp_gw_list)
    AppCompatSpinner spGwList;
    @BindView(R.id.pick_direct_id)
    public TextView storeCode;
    @BindView(R.id.pick_direct_all_num)
    public TextView allNum;
    @BindView(R.id.pick_direct_num)
    public TextView pickNum;
    @BindView(R.id.light_text)
    TextView lightText;
    @BindView(R.id.light_color)
    TextView lightColor;
    @BindView(R.id.bt_picking_direct_take)
    Button btPickingDirectTake;
    @BindView(R.id.bt_picking_direct_finish)
    Button btPickingDirectFinish;


    private View mView;
    private ScanServiceWithUHF mService;
    private List<GetPzhDetailResult.ListBean> mList = new ArrayList<>();
    private List<GetPzhDetailResult.ListBean> datas = new ArrayList<>();
    private PickByCertificateAdapter mAdapter;
    private List<Boolean> flags = new ArrayList<>();
    private List<Gw> gws = new ArrayList<>();
    private PickActivity mActivity;
    private PickPresenterImpl mPresenter;
    private String pzh;
    private GwAdapter gwAdapter;
    private SharePreferenceUtil mSpUtil = MApplication.getInstance().getSpUtil();

    private int atPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_picking_certificate, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (PickActivity) getActivity();
        mService = mActivity.mService;
        mPresenter = new PickPresenterImpl(this);
        initView();
        initListView();
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
        gwAdapter = new GwAdapter(getActivity(), gws);
        spGwList.setAdapter(gwAdapter);
        spGwList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSpUtil.getLight().equals("是")) {
                    if (gws.size() == 0) return;
                    String s = btPickingDirectLight.getText().toString();
                    if (s.equals("亮灯")) {
                        showSelectData(datas, position);
                    } else {
                        mPresenter.setVoluntarilyLight(getLights(false, atPosition), 2, position);
                    }
                } else
                showSelectData(datas, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (mSpUtil.getLightColor() != null && !mSpUtil.getLightColor().isEmpty())
            lightColor.setBackgroundColor(Color.parseColor(mSpUtil.getLightColor()));
    }

    private void initListView() {
        mAdapter = new PickByCertificateAdapter(getActivity(), mList, flags, this);
        lvPicking.setAdapter(mAdapter);
        lvPicking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    String barCode = "0000000000";

    public void initService() {
        if (mSpUtil.getBarCode() != null && !mSpUtil.getBarCode().isEmpty())
            barCode = mSpUtil.getBarCode();

        mService.SetHandleFunc(msgHandler);// 设置回调函数
        BarcodeReceiver.setListener(new OnBroadCaseFinishListener() {
            @Override
            public void onBroadCaseFinish(final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (data.equals(barCode)) {
                            mPresenter.fetchPC(getPcInfos(), pzh, "cgdh");
                        } else {
                            addToList(data);
                        }
                    }
                });
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

    private synchronized void addToList(final String epcCount) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] szInfo = epcCount.split(";");

                String epc = szInfo[0];

                List<GetPzhDetailResult.ListBean> list = mAdapter.getList();
                int i;
                for (i = 0; i < list.size(); i++) {
                    GetPzhDetailResult.ListBean bean = list.get(i);
                    if (bean.getPch().equals(epc)) {
                        int index = list.indexOf(bean);
                        flags.set(index, true);
                        break;
                    }
                }

                if (i == list.size()) {
                    if (mActivity.getmFlag().equals("条码")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("批次号：" + epc + " 不属于当前货品范围内的数据");
                        builder.setPositiveButton("确定", null);
                        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == 275) {
                                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                        if (event.getRepeatCount() == 0) {
                                            dialog.dismiss();
                                        }
                                    }
                                }
                                return false;
                            }
                        });
                        builder.show();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //开始
    public void QueryPcByPzh(String pzh) {
        this.pzh = pzh;

        storeCode.setText("");
        allNum.setText("");
        pickNum.setText("");

        datas.clear();
        flags.clear();
        mList.clear();
        gws.clear();
        spGwList.setAdapter(gwAdapter);
        gwAdapter.notifyDataSetChanged();
        spGwList.setSelection(0);
        mAdapter.notifyDataSetChanged();

        mPresenter.getOrderDetail(pzh, "cgdh");
    }

    @Override
    public void ShowPc(GetPzhDetailResult result) {
        if (result == null) return;
        final List<GetPzhDetailResult.ListBean> list = result.getList();
        if (list.size() == 0) {
            ShowDialog("该凭证没有可拣的批次号！");
            return;
        }

        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (datas == null) return;
                storeCode.setText("");
                allNum.setText("");
                pickNum.setText("");

                datas.clear();
                flags.clear();
                gws.clear();
                datas.addAll(list);

                List<String> gwh = new ArrayList<>();
                for (GetPzhDetailResult.ListBean bean : list) {
                    String s = null;
                    if (!bean.getGwbh().equals(""))
                        s = bean.getGwbh();
                    else
                        s = "空";
                    if (!gwh.contains(s)) {
                        Gw gw = new Gw();
                        gw.setName(s);
                        gw.setFinish(false);
                        gwh.add(s);
                        gws.add(gw);
                    }
                    flags.add(false);
                }
                spGwList.setAdapter(gwAdapter);
                gwAdapter.notifyDataSetChanged();
                spGwList.setSelection(0);
            }
        });
    }

    @Override
    public void RemovePC(List<PcInfo> pcs) {
        if (datas == null) return;
        List<GetPzhDetailResult.ListBean> list = mAdapter.getList();
        final List<GetPzhDetailResult.ListBean> container = new ArrayList<>();
        for (int i = 0; i < pcs.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (pcs.get(i).getPch().equals(list.get(j).getPch())) {
                    container.add(list.get(j));
                }
            }
        }
        for (GetPzhDetailResult.ListBean bean : container) {
            int position = list.indexOf(bean);
            list.remove(position);
            flags.remove(position);
            int indexOf = datas.indexOf(bean);
            datas.remove(indexOf);
        }

        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                int num = Integer.parseInt(pickNum.getText().toString());
                pickNum.setText(String.valueOf(num + container.size()));
                mAdapter.notifyDataSetChanged();
            }
        });

        if (list.size() == 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String s = btPickingDirectLight.getText().toString();
                    cbAll.setChecked(false);
                    if (s.equals("灭灯")) {
                        mPresenter.SetLight(getLights(false));
                    } else {
                        nextGw();
                    }
                }
            });
        }
    }

    private void nextGw() {
        int position = spGwList.getSelectedItemPosition();
        gws.remove(gws.get(position));
        spGwList.setAdapter(gwAdapter);
        gwAdapter.notifyDataSetChanged();
        spGwList.setSelection(0);
        getLight();
    }

    @Override
    public void changeBtnView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s = btPickingDirectLight.getText().toString();
                if (s.equals("亮灯")) {
                    spGwList.setEnabled(false);
                    btPickingDirectLight.setText("灭灯");
                } else {
                    spGwList.setEnabled(true);
                    btPickingDirectLight.setText("亮灯");
                    if (mList.size() == 0) {
                        nextGw();
                    }
                }
            }
        });
    }

    private void showSelectData(List<GetPzhDetailResult.ListBean> list, int position) {
        atPosition = position;
        cbAll.setChecked(false);
        mAdapter.getList().clear();
        flags.clear();
        if (gws.size() == 0) return;
        String name = gws.get(position).getName();
        for (GetPzhDetailResult.ListBean bean : list) {
            if (!name.equals("空")) {
                if (name.equals(bean.getGwbh())) {
                    mList.add(bean);
                    flags.add(false);
                }
            } else {
                if (bean.getGwbh().equals("")) {
                    mList.add(bean);
                    flags.add(false);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
        getLight();
    }

    @OnClick({R.id.bt_picking_direct_light, R.id.bt_picking_direct_take, R.id.bt_picking_direct_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_picking_direct_light:
                setLight();
                break;
            case R.id.bt_picking_direct_take:
                mPresenter.fetchPC(getPcInfos(), pzh, "cgdh");
                break;
            case R.id.bt_picking_direct_finish:
                if (btPickingDirectLight.getText().toString().equals("灭灯")) {
                    ShowDialog("该柜位还没灭灯！");
                } else {
                    setWqyy();
                }
                break;

        }
    }

    private void setWqyy() {
        if (datas.size() != 0) {
            final EditText editText = new EditText(getActivity());
            editText.setMinLines(3);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示！");
            builder.setMessage("请输入未取原因！");
            builder.setView(editText);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String yy = editText.getText().toString();
                    if (!yy.equals("")) {
                        List<PcInfo> pcInfos = new ArrayList<>();
                        for (GetPzhDetailResult.ListBean bean : datas) {
                            PcInfo pcInfo = new PcInfo();
                            pcInfo.setPch(bean.getPch());
                            pcInfo.setWqhyy(yy);
                            pcInfos.add(pcInfo);
                        }
                        mPresenter.nofetchPc(pcInfos, pzh, yy);
                    } else {
                        ShowDialog("请输入未取原因！");
                    }
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        } else {
            mActivity.changeFragment(0);
        }
    }

    private List<PcInfo> getPcInfos() {
        List<PcInfo> pcInfos = new ArrayList<>();
        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i)) {
                GetPzhDetailResult.ListBean bean = mAdapter.getList().get(i);
                PcInfo pcInfo = new PcInfo();
                pcInfo.setPch(bean.getPch());
                pcInfo.setQybh(bean.getQybh().toString());
                pcInfos.add(pcInfo);
            }
        }
        return pcInfos;
    }

    private List<Light> getLights(boolean isOpen) {
        List<Light> lights = new ArrayList<>();
        Light light = new Light();
        int position = spGwList.getSelectedItemPosition();
        light.gwbh = gws.get(position).getName();
        light.isOpen = isOpen;
        lights.add(light);
        return lights;
    }

    private List<Light> getLights(boolean isOpen, int position) {
        List<Light> lights = new ArrayList<>();
        Light light = new Light();
        light.gwbh = gws.get(position).getName();
        light.isOpen = isOpen;
        lights.add(light);
        return lights;
    }

    private void setLight() {
        if (gws.size() == 0) {
            ShowDialog("当前没有柜位！");
            return;
        }
        String s = btPickingDirectLight.getText().toString();
        if (s.equals("亮灯")) {
            mPresenter.SetLight(getLights(true));
        } else {
            mPresenter.SetLight(getLights(false));
        }
    }

    @Override
    public void ShowDialog(String result) {
        mActivity.ShowDialog(result);
    }

    public void finishByBackBtn() {
        if (btPickingDirectLight.getText().toString().equals("灭灯")) {
            ShowDialog("该柜位还没灭灯！");
        } else if (datas.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示");
            builder.setMessage("还有未取商品！");
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        } else {
            getActivity().finish();
        }
    }

    public void finishByBackIcon() {
        if (btPickingDirectLight.getText().toString().equals("灭灯")) {
            if (mSpUtil.getLight().equals("是"))
                mPresenter.setVoluntarilyLight(getLights(false), 3, -1);
            else
            ShowDialog("该柜位还没灭灯！");
        } else {
            back();
        }
    }

    private void back() {
        if (datas.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示");
            builder.setMessage("还有未取商品！");
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((BaseActivity) getActivity()).changeFragment(0);
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        } else {
            ((BaseActivity) getActivity()).changeFragment(0);
        }
    }

    @Override
    public void ActivityFinish() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.changeFragment(0);
            }
        });
    }

    public void getLight() {
        if (mSpUtil.getLight().equals("是")) {
            if (gws.size() == 0) {
                ShowDialog("当前没有柜位！");
                return;
            }
            String s = btPickingDirectLight.getText().toString();
            if (s.equals("亮灯")) {
                mPresenter.setVoluntarilyLight(getLights(true), 0, -1);
            } else {
                mPresenter.setVoluntarilyLight(getLights(false), 0, -1);
            }
        }
    }

    private void forLight() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("该柜使用中，是否切换下一个柜");
        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getNextGw();
            }
        });
        builder.setPositiveButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lightText.setVisibility(View.VISIBLE);
            }
        });
        builder.show();
    }

    @Override
    public void voluntarilyLight(final String volintarily) {
        if (mSpUtil.getLight().equals("是")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (volintarily) {
                        case "0006":
                            forLight();
                            break;
                        case "灭灯成功":
                            setWqyy();
                            break;
                        case "空":
                            getNextGw();
                            break;
                        case "3":
                            back();
                            break;
                    }

                }
            });
        }
    }

    @Override
    public void voluntarilyLight(String volintarily, final int postion) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btPickingDirectLight.setText("亮灯");
                showSelectData(datas, postion);
            }
        });
    }

    private void getNextGw() {
        int position = spGwList.getSelectedItemPosition();
        spGwList.setAdapter(gwAdapter);
        gwAdapter.notifyDataSetChanged();
        if (gws.size() > (position + 1))
            spGwList.setSelection(position + 1);
        else
            spGwList.setSelection(0);
    }
}