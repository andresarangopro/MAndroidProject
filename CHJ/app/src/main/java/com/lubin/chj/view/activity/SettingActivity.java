package com.lubin.chj.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lubin.chj.MApplication;
import com.lubin.chj.R;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/9/29  17:41
 * @desc ${TODD}
 */
public class SettingActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.tb_common)
    Toolbar mTbCommon;
    @BindView(R.id.tv_power)
    TextView tvPower;
    @BindView(R.id.sb_power)
    SeekBar sbPower;
    @BindView(R.id.setting_barcode)
    EditText settingBarcode;
    @BindView(R.id.setting_checkbox)
    CheckBox settingCheckbox;


    private Map<String, Integer> setting = new HashMap<>();
    private ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();
    private SharePreferenceUtil mSpUtil = MApplication.getInstance().getSpUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initHeader();
        sbPower.setOnSeekBarChangeListener(this);
        sbPower.setProgress(mService.GetPower() - 5);
        tvPower.setText(String.valueOf(mService.GetPower()));

    }


    private void initHeader() {
        mTbCommon.setNavigationIcon(R.mipmap.back);
        mTbCommon.setTitle("区域信息");
        mTbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (mSpUtil.getBarCode() != null && !mSpUtil.getBarCode().isEmpty())
            settingBarcode.setText(mSpUtil.getBarCode());
        else
            settingBarcode.setText("0000000000");

        if (mSpUtil.getLight() == null || mSpUtil.getLight().isEmpty() || mSpUtil.getLight().equals("是")) {
            settingCheckbox.setChecked(true);
            settingCheckbox.setText("是");
        } else {
            settingCheckbox.setChecked(false);
            settingCheckbox.setText("否");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTbCommon.setTitle("设置");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_power:
                int j = progress + 5;
                setting.put("power", j);
                tvPower.setText(j + "");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @OnClick({R.id.setting_checkbox, R.id.btn_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_checkbox:
                if (settingCheckbox.isChecked()) {
                    settingCheckbox.setChecked(true);
                    settingCheckbox.setText("是");
                } else {
                    settingCheckbox.setChecked(false);
                    settingCheckbox.setText("否");
                }
                break;
            case R.id.btn_setting:
                mSpUtil.setPower(setting.get("power"));
                String barcode = settingBarcode.getText().toString();
                if (barcode == null || barcode.isEmpty())
                    barcode = "0000000000";
                mSpUtil.setBarCode(barcode);
                mSpUtil.setLight(settingCheckbox.getText().toString());
                mService.SetPower(setting.get("power"));
                finish();
                break;
        }
    }
}
