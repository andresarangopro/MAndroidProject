package com.lubin.chj.view.activity.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.adapter.InventoryGwAdapter;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.presenter.IPresenter.IInventoryPresenter;
import com.lubin.chj.presenter.InventoryPresenterImpl;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.KeyboardUtils;
import com.lubin.chj.view.activity.BaseActivity;
import com.lubin.chj.view.activity.InventoryActivity;
import com.lubin.chj.view.activity.VInterface.IInventoryView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/9/27  16:16
 * @desc ${按照柜位盘点}
 */
public class InventoryByGwFragment extends FragmentBase implements IInventoryView<List<QueryPcReturn.ListBean>> {


    @BindView(R.id.bt_access)
    Button mBtAccess;
    @BindView(R.id.lv_inventory)
    ListView mLvInventory;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_yzys)
    TextView tvYzys;
    @BindView(R.id.tv_wzys)
    TextView tvWzys;
    @BindView(R.id.tv_yzws)
    TextView tvYzws;
    @BindView(R.id.et_query_barcode)
    EditText etQueryBarcode;

    private View mView;
    private InventoryActivity mActivity;
    private IInventoryPresenter mPresenter;
    private List<QueryPcReturn.ListBean> mList = new ArrayList<>();
    private InventoryGwAdapter mAdapter;
    private List<PcInfo> mPcInfoList;
    private int size = 0;
    private ScanServiceWithUHF mService;
    private String gwbh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_inventory_bygw, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (InventoryActivity) getActivity();
        mService = mActivity.mService;
        mPresenter = new InventoryPresenterImpl(this);
        initListView();
        initService();
        return mView;

    }

    private void initListView() {
        mAdapter = new InventoryGwAdapter(mActivity, mList);
        mLvInventory.setAdapter(mAdapter);
    }

    public void clearData() {
        mAdapter.clearAll();
    }

    private void initService() {
        mService.SetHandleFunc(msgHandler);// 设置回调函数
    }

    public void QueryPcByGw(String gwbh) {
        this.gwbh = gwbh;
        mPresenter.QueryPc(gwbh);
    }


    @Override
    public void ShowDialog(String result) {
        mActivity.ShowDialog(result);
    }

    @Override
    public void showQueryPcResult(final List<QueryPcReturn.ListBean> listBeen) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mList == null) return;
                mList.clear();
                mList.addAll(listBeen);
                mAdapter.notifyDataSetChanged();
                size = 0;
                tvSum.setText(String.valueOf(size));
                tvYzws.setText(String.valueOf(listBeen.size()));
                tvWzys.setText("0");
                tvYzys.setText("0");
            }
        });
    }

    @Override
    public void setBarcode(String barcode) {
        setEpc(barcode);
    }

    public void setEpc(String epc) {
        try {
            Integer.parseInt(epc);
        } catch (NumberFormatException e) {
            return;
        }
        QueryPcReturn.ListBean newData;
        int i;
        boolean flag = false;
        for (i = 0; i < mList.size(); i++) {
            QueryPcReturn.ListBean bean = mList.get(i);
            if (bean.getPch().equals(epc)) {
                if (bean.getPdjg().equals("有账无实")) {
                    bean.setPdjg("有账有实");
                    tvYzys.setText(String.valueOf(Integer.parseInt(tvYzys.getText().toString()) + 1));
                    tvYzws.setText(String.valueOf(Integer.parseInt(tvYzws.getText().toString()) - 1));
                    size++;
                    flag = false;
                }
                mAdapter.notifyDataSetChanged();
                break;
            }
        }
        if (i == mList.size()) {
            newData = new QueryPcReturn.ListBean();
            newData.setPch(epc);
            newData.setGwbh("");
            newData.setQybh("");
            newData.setPdjg("无账有实");
            mList.add(newData);
            tvWzys.setText(String.valueOf(Integer.parseInt(tvWzys.getText().toString()) + 1));
            size++;
            flag = true;
        }
        sortList(epc, flag);

        tvSum.setText(String.valueOf(size));
        mAdapter.notifyDataSetChanged();
    }

    private void sortList(String epc, boolean flag) {
        try {
            Collections.sort(mList, new Comparator<QueryPcReturn.ListBean>() {
                @Override
                public int compare(QueryPcReturn.ListBean lhs, QueryPcReturn.ListBean rhs) {
                    if (lhs.getPdjg().equals("有账无实") || lhs.getPdjg().equals("")) {
                        return -1;
                    } else if (lhs.getPdjg().equals("无账有实")) {
                        if (rhs.getPdjg().equals("有账有实")) {
                            return -1;
                        } else if (rhs.getPdjg().equals("有账无实") || rhs.getPdjg().equals("")) {
                            return 1;
                        }
                    } else if (lhs.getPdjg().equals("有账有实")) {
                        if (!rhs.getPdjg().equals("有账有实")) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                    return 0;
                }
            });
            Collections.sort(mList, new Comparator<QueryPcReturn.ListBean>() {
                @Override
                public int compare(QueryPcReturn.ListBean lhs, QueryPcReturn.ListBean rhs) {
                    if (lhs.getPdjg().equals(rhs.getPdjg())) {
                        int ilhs = Integer.parseInt(lhs.getPch());
                        int irhs = Integer.parseInt(rhs.getPch());
                        if (ilhs > irhs) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                    return 0;
                }
            });
            for (Iterator<QueryPcReturn.ListBean> iterator = mList.iterator(); iterator.hasNext(); ) {
                QueryPcReturn.ListBean listBean = iterator.next();
                if (!isNumeric(listBean.getPch())) {
                    getException(epc, flag);
                }
            }
        } catch (IllegalArgumentException e) {
            getException(epc, flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getException(String epc, boolean flag) {
        QueryPcReturn.ListBean errorData = null;
        for (QueryPcReturn.ListBean bean : mList) {
            if (bean.getPch().equals(epc)) {
                errorData = bean;
                break;
            }
        }
        mList.remove(errorData);
        if (flag)
            tvWzys.setText(String.valueOf(Integer.parseInt(tvWzys.getText().toString()) - 1));
        else {
            tvYzys.setText(String.valueOf(Integer.parseInt(tvYzys.getText().toString()) - 1));
            tvYzws.setText(String.valueOf(Integer.parseInt(tvYzws.getText().toString()) + 1));
        }
        size--;
        sortList(null, flag);
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
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
                setEpc(epc);
            }
        });
    }

    @Override
    public void finishByBackBtn() {
        getActivity().finish();
    }

    @Override
    public void finishByBackIcon() {
        ((BaseActivity) getActivity()).changeFragment(1);
    }

    @OnClick({R.id.btn_query, R.id.bt_access})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                String s = etQueryBarcode.getText().toString();
                if (!s.equals("") && !s.equals(" ") && !s.isEmpty()) {
                    addToList(s);
                    etQueryBarcode.setText("");
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                break;
            case R.id.bt_access:
                mPcInfoList = new ArrayList<>();
                for (QueryPcReturn.ListBean bean : mList) {
                    PcInfo info = new PcInfo();
                    info.setPch(bean.getPch());
                    info.setPdjg(bean.getPdjg().toString());
                    mPcInfoList.add(info);
                }
                mPresenter.SavePd(mPcInfoList, gwbh);
                break;
        }
    }
}
