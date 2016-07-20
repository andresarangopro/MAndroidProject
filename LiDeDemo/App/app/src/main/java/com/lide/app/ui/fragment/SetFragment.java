package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lide.app.R;
import com.lide.app.ui.MainActivity;
import com.rscja.utility.StringUtility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetFragment extends FragmentBase {
    @BindView(R.id.SpinnerMode)
    Spinner SpinnerMode;
    @BindView(R.id.EtFreRange)
    EditText EtFreRange;
    @BindView(R.id.BtSetFre)
    Button BtSetFre;
    @BindView(R.id.BtGetFre)
    Button BtGetFre;
    @BindView(R.id.spPower)
    Spinner spPower;
    @BindView(R.id.et_worktime)
    EditText etWorktime;
    @BindView(R.id.et_waittime)
    EditText etWaittime;
    @BindView(R.id.btnWorkWait)
    Button btnWorkWait;
    private MainActivity mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater
                .inflate(R.layout.fragment_set, container, false);

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = (MainActivity) getActivity();
        BtSetFre.setOnClickListener(new SetFreOnclickListener());
        BtGetFre.setOnClickListener(new GetFreOnclickListener());
        btnWorkWait.setOnClickListener(new SetPWMOnclickListener());
        //设置标题
        getActivity().setTitle("设置");

    }

    @Override
    public void onResume() {
        super.onResume();
        getFre();
        getPwm();
        OnClick_GetPower(null);
    }

    public class SetFreOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // byte[] bBaseFre = new byte[2];
            //
            // if (mContext.mReader.setFrequency(
            // (byte) spMode.getSelectedItemPosition(), (byte) 0,
            // bBaseFre, (byte) 0, (byte) 0, (byte) 0)) {
            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_set_frequency_succ);
            // } else {
            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_set_frequency_fail);
            // }

            if (mContext.mReader.setFrequencyMode((byte) SpinnerMode
                    .getSelectedItemPosition())) {
                ShowToast(
                        R.string.uhf_msg_set_frequency_succ);
            } else {
                ShowToast(
                        R.string.uhf_msg_set_frequency_fail);
            }

        }
    }

    public void getFre() {
        int idx = mContext.mReader.getFrequencyMode();

        if (idx != -1) {
            int count = SpinnerMode.getCount();
            SpinnerMode.setSelection(idx > count - 1 ? count - 1 : idx);

            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_read_frequency_succ);
        } else {
            ShowToast(
                    R.string.uhf_msg_read_frequency_fail);
        }
    }

    public void getPwm() {
        int[] pwm = mContext.mReader.getPwm();

        if (pwm == null || pwm.length < 2) {
            ShowToast(R.string.uhf_msg_read_pwm_fail);
            return;
        }

        etWorktime.setText(pwm[0] + "");
        etWaittime.setText(pwm[1] + "");

    }

    public class SetPWMOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (mContext.mReader.setPwm(StringUtility.string2Int(etWorktime.getText().toString(), 0),
                    StringUtility.string2Int(etWaittime.getText().toString(), 0))) {
                ShowToast(
                        R.string.uhf_msg_set_pwm_succ);
            } else {
                ShowToast(
                        R.string.uhf_msg_set_pwm_fail);
            }
        }
    }

    public class GetFreOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // String strFrequency = mContext.mReader.getFrequency();
            //
            // if (StringUtils.isNotEmpty(strFrequency)) {
            //
            // etFreRange.setText(strFrequency);
            //
            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_read_frequency_succ);
            //
            // } else {
            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_read_frequency_fail);
            // }

            getFre();
        }

    }

    @OnClick(R.id.btnGetPower)
    public void OnClick_GetPower(View view) {
        int iPower = mContext.mReader.getPower();

        Log.i("UHFSetFragment", "OnClick_GetPower() iPower=" + iPower);

        if (iPower > -1) {
            int position = iPower - 5;
            int count = spPower.getCount();
            spPower.setSelection(position > count - 1 ? count - 1 : position);

            // UIHelper.ToastMessage(mContext,
            // R.string.uhf_msg_read_power_succ);

        } else {
            ShowToast(R.string.uhf_msg_read_power_fail);
        }

    }

    @OnClick(R.id.btnSetPower)
    public void OnClick_SetPower(View view) {
        int iPower = spPower.getSelectedItemPosition() + 5;

        Log.i("UHFSetFragment", "OnClick_SetPower() iPower=" + iPower);

        if (mContext.mReader.setPower(iPower)) {

            ShowToast(R.string.uhf_msg_set_power_succ);
        } else {
            ShowToast(R.string.uhf_msg_set_power_fail);
        }

    }

}
