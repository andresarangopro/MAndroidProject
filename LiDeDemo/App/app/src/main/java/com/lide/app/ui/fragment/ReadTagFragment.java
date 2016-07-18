package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.lide.app.R;
import com.lide.app.ui.MainActivity;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.deviceapi.entity.SimpleRFIDEntity;

public class ReadTagFragment extends FragmentBase {

    private MainActivity mContext;

    CheckBox CkWithUii_Read;
    EditText EtTagUii_Read;
    Spinner SpinnerBank_Read;
    EditText EtPtr_Read;
    EditText EtLen_Read;
    EditText EtAccessPwd_Read;
    Spinner SpinnerOption_Read;
    EditText EtPtr2_Read;
    EditText EtLen2_Read;
    EditText EtData_Read;
    Button BtUii_Read;
    Button BtRead;


    Button BtTid_Read;
    CheckBox CkWithTid_Read;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read_tag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mContext = (MainActivity) getActivity();

        CkWithUii_Read = (CheckBox) mContext.findViewById(R.id.CkWithUii_Read);
        EtTagUii_Read = (EditText) mContext.findViewById(R.id.EtTagUii_Read);
        SpinnerBank_Read = (Spinner) mContext
                .findViewById(R.id.SpinnerBank_Read);
        EtPtr_Read = (EditText) mContext.findViewById(R.id.EtPtr_Read);
        EtLen_Read = (EditText) mContext.findViewById(R.id.EtLen_Read);
        EtAccessPwd_Read = (EditText) mContext
                .findViewById(R.id.EtAccessPwd_Read);
        SpinnerOption_Read = (Spinner) mContext
                .findViewById(R.id.SpinnerOption_Read);

        EtPtr2_Read = (EditText) mContext.findViewById(R.id.EtPtr2_Read);
        EtLen2_Read = (EditText) mContext.findViewById(R.id.EtLen2_Read);
        EtData_Read = (EditText) mContext.findViewById(R.id.EtData_Read);
        BtUii_Read = (Button) mContext.findViewById(R.id.BtUii_Read);
        BtRead = (Button) mContext.findViewById(R.id.BtRead);

        EtData_Read.setKeyListener(null);
        EtTagUii_Read.setKeyListener(null);
        BtUii_Read.setEnabled(false);
        EtPtr2_Read.setEnabled(false);
        EtLen2_Read.setEnabled(false);

        EtData_Read.setText("");

        CkWithUii_Read.setOnClickListener(new CkWithUii_ReadClickListener());
        BtUii_Read.setOnClickListener(new BtUii_ReadClickListener());
        BtRead.setOnClickListener(new BtReadClickListener());

        getActivity().setTitle("读取信息");

    }

    public class BtUii_ReadClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String uiiStr = mContext.mReader.inventorySingleTag();
            if (uiiStr != null) {
                EtTagUii_Read.setText(uiiStr);
            } else {
                EtTagUii_Read.setText("");
                ShowToast(R.string.uhf_msg_read_tag_fail);
            }
        }
    }

    public class BtReadClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            read();

        }
    }

    private void read() {
        String ptrStr = EtPtr_Read.getText().toString().trim();
        if (ptrStr.equals("")) {
            ShowToast( R.string.uhf_msg_addr_not_null);
            return;
        } else if (!TextUtils.isDigitsOnly(ptrStr)) {
            ShowToast(R.string.uhf_msg_addr_must_decimal);
            return;
        }

        String cntStr = EtLen_Read.getText().toString().trim();
        if (cntStr.equals("")) {
            ShowToast(R.string.uhf_msg_len_not_null);
            return;
        } else if (!TextUtils.isDigitsOnly(cntStr)) {
            ShowToast( R.string.uhf_msg_len_must_decimal);
            return;
        }

        String pwdStr = EtAccessPwd_Read.getText().toString().trim();
        if (!TextUtils.isEmpty(pwdStr)) {
            if (pwdStr.length() != 8) {

                ShowToast(R.string.uhf_msg_addr_must_len8);
                return;
            } else if (!mContext.vailHexInput(pwdStr)) {
                ShowToast(R.string.rfid_mgs_error_nohex);

                return;
            }

        } else {
            pwdStr = "00000000";
        }

        if (CkWithUii_Read.isChecked())// 指定标签
        {
            String uiiStr = EtTagUii_Read.getText().toString().trim();
            if (TextUtils.isEmpty(uiiStr)) {
                ShowToast(
                        R.string.uhf_msg_tag_must_not_null);
                return;
            }


            // 读取标签数据，指定UII @Ray
            String strReadData = mContext.mReader.readData(pwdStr, RFIDWithUHF.BankEnum
                            .valueOf(SpinnerBank_Read.getSelectedItem().toString()),
                    Integer.parseInt(ptrStr), Integer.parseInt(cntStr), uiiStr);

            if (!TextUtils.isEmpty(strReadData)) {
                EtData_Read.setText(strReadData);


            } else {
                ShowToast(R.string.uhf_msg_read_data_fail);
                EtData_Read.setText("");
            }

        } else {

            SimpleRFIDEntity entity = mContext.mReader.readData(pwdStr,
                    RFIDWithUHF.BankEnum.valueOf(SpinnerBank_Read.getSelectedItem()
                            .toString()), Integer.parseInt(ptrStr), Integer
                            .parseInt(cntStr));

            if (entity != null) {
                EtData_Read.setText("UII: " + entity.getId() + "\n\n" + "data："
                        + entity.getData());
            } else {
                ShowToast(R.string.uhf_msg_read_data_fail);
                EtData_Read.setText("");
            }

        }

    }

    public class CkWithUii_ReadClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            EtTagUii_Read.setText("");

            if (CkWithUii_Read.isChecked()) {
                BtUii_Read.setEnabled(true);
                BtUii_Read.setVisibility(View.VISIBLE);

            } else {
                BtUii_Read.setVisibility(View.GONE);
                BtUii_Read.setEnabled(false);
            }
        }
    }

}
