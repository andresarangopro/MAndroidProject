package com.lide.app.ui.findProduct;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//输入框如果没有焦点，就是读写器返回的数据是携带能量值的，数据是用来与输入框要找的epc匹配的，
//如果有焦点，读写器的返回的数据是没有能力值,数据是用来作为目标的
public class FindEPCFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {


    @BindView(R.id.animation1)
    ImageView animation1;
    @BindView(R.id.et_epc)
    EditText etEpc;
    @BindView(R.id.btn_read_or_stop)
    Button btnReadOrStop;

    private View mView;
    private ScanPresenter scanPresenter;
    private FindActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_show_goods_epc, container, false);
        ButterKnife.bind(this, mView);
        mActivity = ((FindActivity) getActivity());
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        etEpc.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etEpc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null)
                    if (event.getAction() == KeyEvent.ACTION_UP) return true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                    animation1.requestFocus();
                    return true;
                }
                return false;
            }
        });
        animation1.setImageResource(R.drawable.icon1);
        initScanPresenter();
        if (mActivity.epc != null) {
            etEpc.setText(mActivity.epc);
            animation1.requestFocus();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        scanPresenter.stopReadRfid();
        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0);
    }

    private void initScanPresenter() {
        scanPresenter = new ScanPresenter(this);
        scanPresenter.initData();
        scanPresenter.setCurrentSetting(ScanPresenter.Setting.stockRead);
        scanPresenter.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                if (etEpc.hasFocus()) {
                    etEpc.setText(data);
                    animation1.requestFocus();
                } else {
                    String[] split = data.split("@");
                    String epc = etEpc.getText().toString();
                    if (epc.equals(split[0])) {
                        if (split.length == 1) {
                            icon(0);
                        } else {
                            int rssi = Double.valueOf(split[1]).intValue();
                            icon(rssi);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void readOrClose() {
        if (btnReadOrStop.getText().toString().equals("停止扫描")) {
            scanPresenter.stopReadRfid();
            animation1.setImageResource(R.drawable.icon1);
            btnReadOrStop.setBackground(commonBackground);
            mActivity.tbCommon.setVisibility(View.VISIBLE);
            etEpc.setEnabled(true);
            btnReadOrStop.setText("开始扫描");
        } else {
            if (etEpc.hasFocus()) {
                scanPresenter.setReadDataModel(0);
                scanPresenter.startScanBarcode();
            } else {
                scanPresenter.setReadDataModel(1);
                scanPresenter.startReadRfid();
                etEpc.setEnabled(false);
                btnReadOrStop.setBackground(redBackground);
                mActivity.tbCommon.setVisibility(View.INVISIBLE);
                btnReadOrStop.setText("停止扫描");
            }
        }
    }

    public void setEpcName(String epc) {
        if (etEpc != null) {
            etEpc.setText(epc);
            animation1.requestFocus();
        }
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {
    }


    public void icon(int iconNum) {
        switch (iconNum) {
            case 0:
                animation1.setImageResource(R.drawable.icon1);
                SoundUtils.playByVolume(1, 2);
                break;
            case 1:
                animation1.setImageResource(R.drawable.icon2);
                SoundUtils.playByVolume(1, 4);
                break;
            case 2:
                animation1.setImageResource(R.drawable.icon3);
                SoundUtils.playByVolume(1, 6);
                break;
            case 3:
                animation1.setImageResource(R.drawable.icon4);
                SoundUtils.playByVolume(1, 9);
                break;
            case 4:
                animation1.setImageResource(R.drawable.icon5);
                SoundUtils.playByVolume(1, 12);
                break;
            case 5:
                animation1.setImageResource(R.drawable.icon6);
                SoundUtils.playByVolume(1, 15);
                break;
        }

    }

    @OnClick(R.id.btn_read_or_stop)
    public void onClick() {
        readOrClose();
    }
}

