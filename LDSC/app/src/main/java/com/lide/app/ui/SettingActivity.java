package com.lide.app.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.setting.SettingPresenter;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/8/9  13:53
 * @desc ${TODD}
 */
public class SettingActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, IDataFragmentView<List<LinkedTreeMap>> {


    @BindView(R.id.tb_setting)
    Toolbar tbSetting;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.tv_power)
    TextView tvPower;
    @BindView(R.id.sb_power)
    SeekBar sbPower;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.sb_volume)
    SeekBar sbVolume;
    @BindView(R.id.et_net_address)
    EditText etNetAddress;
    @BindView(R.id.et_device_code)
    EditText etDeviceCode;
    @BindView(R.id.cb_protect_mode)
    AppCompatCheckBox cbProtectMode;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.btn_more)
    Button btnMore;
    private SettingPresenter settingPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();
        initSetting();
        setViewEnabled(false);
        settingPresenter = new SettingPresenter(this);
    }

    protected void onResume() {
        super.onResume();
        tbSetting.setTitle("");
    }

    private void initView() {
        sbPower.setOnSeekBarChangeListener(this);
        sbVolume.setOnSeekBarChangeListener(this);

        setSupportActionBar(tbSetting);
        tbSetting.setNavigationIcon(R.mipmap.back_login);
        tbSetting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initSetting() {
        cbProtectMode.setChecked((boolean) SPUtils.get(this, "isProtect", false));
        etDeviceCode.setText(((String) SPUtils.get(this, "deviceCode", "")));
        String url = SPUtils.get(this, "url", "").toString();
        if (!url.equals("")) {
            Utils.apiUrl = url;
        }
        etNetAddress.setText(Utils.apiUrl);
        sbPower.setProgress(((int) SPUtils.get(this, "power", 30)) - 5);
        sbVolume.setProgress(SoundUtils.Volume);
    }

    @OnClick({R.id.btn_setting, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                String text = btnSetting.getText().toString();
                if (text.equals("设置")) {
                    setViewEnabled(true);
                    btnSetting.setText("确定");
                } else {

                    String string = etNetAddress.getText().toString();
                    if (string.length() != 0) {
                        if (string.substring(string.length() - 1, string.length())
                                .equals("/"))
                            Utils.apiUrl = string;
                        else
                            Utils.apiUrl = string + "/";
                    } else {
                        Utils.apiUrl = string;
                    }

                    SPUtils.put(this, "url", Utils.apiUrl);
                    SPUtils.put(this, "isProtect", cbProtectMode.isChecked());
                    SPUtils.put(this, "power", sbPower.getProgress() + 5);
                    if (!etDeviceCode.getText().toString().equals("")) {
                        settingPresenter.queryDeviceList(etDeviceCode.getText().toString());
                    } else {
                        finish();
                    }
                }
                break;
            case R.id.btn_more:
                setVisibility();
                break;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_power:
                int j = i + 5;
                tvPower.setText(j + "ms");
                break;
            case R.id.sb_volume:
                tvVolume.setText(i + "");
                break;

        }

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.sb_volume:
                SoundUtils.setVolume(sbVolume.getProgress());
                break;
        }
    }

    //按钮失效
    public void setViewEnabled(boolean enabled) {
        sbVolume.setEnabled(enabled);
        sbPower.setEnabled(enabled);
        etNetAddress.setEnabled(enabled);
        etDeviceCode.setEnabled(enabled);
        cbProtectMode.setEnabled(enabled);
    }

    public void setVisibility() {
        if (llMore.getVisibility() == View.GONE) {
            llMore.setVisibility(View.VISIBLE);
            btnMore.setVisibility(View.GONE);
        }
    }

    @Override
    public void ShowToast(String text) {
        super.ShowToast(text);
        if (text.equals("设置成功")) {
            finish();
        }
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {

    }
}
