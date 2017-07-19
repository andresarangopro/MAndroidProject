package com.lide.app.ui.takeStock;

import android.content.DialogInterface;
import android.content.Intent;
import android.extend.util.KeyboardUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.StockReadBySkuAdapter;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.PInterface.IUploadCollectBySkuPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IShowDataView;
import com.lubin.bean.TakeStockSkuCollect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/10/19.
 */

public class StockReadBySkuFragment extends FragmentBase implements IShowDataView<List<TakeStockSkuCollect>> {

    @BindView(R.id.et_sku)
    EditText etSku;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.lv_sku)
    ListView lvSku;
    @BindView(R.id.all_num)
    TextView allNum;

    private StockReadBySkuAdapter mAdapter;

    List<TakeStockSkuCollect> mTakeStockSkuCollects = new ArrayList<>();

    private View mView;
    private StockTakingBySkuActivity mActivity;
    private IUploadCollectBySkuPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_stock_read_sku, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (StockTakingBySkuActivity) getActivity();
        initView();
        initListView();
        mPresenter = mActivity.getCollectPresenter();
        mPresenter.getAllSkuCollectByTaskCode();
        return mView;
    }

    private void initView() {
        etSku.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etSku.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null)
                    if (event.getAction() == KeyEvent.ACTION_UP) return true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                    setBarcode(etSku.getText().toString());
                    return true;
                }
                return false;
            }
        });

        etNum.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null)
                    if (event.getAction() == KeyEvent.ACTION_UP) return true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                    setBarcode(etSku.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void initListView() {
        mAdapter = new StockReadBySkuAdapter(getActivity(), mTakeStockSkuCollects);
        lvSku.setAdapter(mAdapter);
        lvSku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    public void setBarcode(String barcode) {
        etSku.setText(barcode);
        SoundUtils.play(1);
        addSkuCollect();
    }

    @OnClick({R.id.btn_upload, R.id.btn_diff})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                Intent upLoadActivity = new Intent();
                upLoadActivity.putExtra("tag", "upload");
                mActivity.startNewActivity(upLoadActivity);
                break;
            case R.id.btn_diff:
                Intent diffActivity = new Intent(getActivity(), QueryDiffActivity.class);
                diffActivity.putExtra("tag", "diff");
                mActivity.startNewActivity(diffActivity);
                break;
        }
    }

    private void addSkuCollect() {
        if (etSku.getText().toString().equals("")) {
            ShowToast("条码不能为空!");
        } else if (etNum.getText().toString().equals("")) {
            ShowToast("数量不能为空!");
        } else {
            boolean flagNum = false;
            boolean flag = false;
            KeyboardUtils.hideSoftInput(getActivity());
            TakeStockSkuCollect mTakeStockSkuCollect = new TakeStockSkuCollect();
            mTakeStockSkuCollect.setBarcode(etSku.getText().toString());
            mTakeStockSkuCollect.setNum(Integer.parseInt(etNum.getText().toString()));
            mTakeStockSkuCollect.setTaskId(String.valueOf(Utils.getCurrentTask().getId()));
            mTakeStockSkuCollect.setDate(new Date(System.currentTimeMillis()));
            mTakeStockSkuCollect.setIsUpload(true);
            for (TakeStockSkuCollect mSkuC : mTakeStockSkuCollects) {
                if (mSkuC.getBarcode().equals(etSku.getText().toString())) {
                    if (mSkuC.getNum() + Integer.parseInt(etNum.getText().toString()) < 0) {
                        flagNum = true;
                    } else {
                        mSkuC.setNum(mSkuC.getNum() + Integer.parseInt(etNum.getText().toString()));
                        mSkuC.setDate(new Date(System.currentTimeMillis()));
                        mPresenter.updateSkuCollect(mSkuC);
                    }
                    flag = true;
                }
            }
            if (!flag) {
                if (mTakeStockSkuCollect.getNum() < 0) {
                    showDialog("条码超出!");
                } else {
                    mTakeStockSkuCollects.add(mTakeStockSkuCollect);
                    mPresenter.addSkuCollect(mTakeStockSkuCollect);
                }
            }

            if (flagNum)
                showDialog("条码超出!");

            mPresenter.getAllSkuCollectByTaskCode();
            etSku.setText("");
            etNum.setText("1");
        }
    }

    public void UpLoad() {
        mPresenter.uploadAllSkuCollectByTaskCode();
    }

    @Override
    public void ShowData(List<TakeStockSkuCollect> takeStockSkuCollects) {
        sortClass sort = new sortClass();
        Collections.sort(takeStockSkuCollects, sort);
        mAdapter.addAll(takeStockSkuCollects);

        int index = 0;

        for (TakeStockSkuCollect mTakeStockSkuCollect : takeStockSkuCollects) {
            index += mTakeStockSkuCollect.getNum();
        }
        allNum.setText(String.valueOf(index));
    }

    class sortClass implements Comparator {
        public int compare(Object arg0, Object arg1) {
            TakeStockSkuCollect user0 = (TakeStockSkuCollect) arg0;
            TakeStockSkuCollect user1 = (TakeStockSkuCollect) arg1;
            return user1.getDate().compareTo(user0.getDate());
        }
    }

    public void showDeleteDialog(final int position) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("是否删除条码：" + mAdapter.getList().get(position).getBarcode());
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TakeStockSkuCollect remove = mAdapter.getList().remove(position);
                mPresenter.deleteSkuCollect(remove);
                mPresenter.getAllSkuCollectByTaskCode();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


}
