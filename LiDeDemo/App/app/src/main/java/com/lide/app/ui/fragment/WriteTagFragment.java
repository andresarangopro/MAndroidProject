package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.lide.app.R;
import com.lide.app.ui.MainActivity;
import com.lide.app.util.StringUtils;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.utility.StringUtility;

public class WriteTagFragment extends FragmentBase {

    private MainActivity mContext;

    CheckBox CkWithUii_Write;
    EditText EtTagUii_Write;
    Spinner SpinnerBank_Write;
    EditText EtPtr_Write;
    EditText EtLen_Write;
    EditText EtData_Write;
    EditText EtAccessPwd_Write;
    Button BtUii_Write;
    Button BtWrite;
    Button BtErase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write_tag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = (MainActivity) getActivity();

        CkWithUii_Write = (CheckBox) mContext
                .findViewById(R.id.CkWithUii_Write);
        EtTagUii_Write = (EditText) mContext.findViewById(R.id.EtTagUii_Write);
        SpinnerBank_Write = (Spinner) mContext
                .findViewById(R.id.SpinnerBank_Write);
        EtPtr_Write = (EditText) mContext.findViewById(R.id.EtPtr_Write);
        EtLen_Write = (EditText) mContext.findViewById(R.id.EtLen_Write);
        EtData_Write = (EditText) mContext.findViewById(R.id.EtData_Write);
        EtAccessPwd_Write = (EditText) mContext
                .findViewById(R.id.EtAccessPwd_Write);
        BtUii_Write = (Button) mContext.findViewById(R.id.BtUii_Write);
        BtWrite = (Button) mContext.findViewById(R.id.BtWrite);

        BtErase = (Button) mContext.findViewById(R.id.BtErase);

        BtUii_Write.setEnabled(false);
        EtTagUii_Write.setKeyListener(null);

        CkWithUii_Write.setOnClickListener(new CkWithUii_WriteClickListener());
        BtUii_Write.setOnClickListener(new BtUii_WriteClickListener());

        BtWrite.setOnClickListener(new BtWriteOnClickListener());
        BtErase.setOnClickListener(new BtEraseOnClickListener());

        getActivity().setTitle("写数据");

    }

    public class BtUii_WriteClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String uiiStr = mContext.mReader.inventorySingleTag();

            if (uiiStr != null) {
                EtTagUii_Write.setText(uiiStr);
            } else {
                EtTagUii_Write.setText("");

                ShowToast( R.string.uhf_msg_read_tag_fail);
            }
        }
    }

    public class BtEraseOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String strPtr = EtPtr_Write.getText().toString().trim();

            if (StringUtils.isEmpty(strPtr)) {
                ShowToast(R.string.uhf_msg_addr_not_null);

                return;
            } else if (!StringUtility.isDecimal(strPtr)) {
                ShowToast(
                        R.string.uhf_msg_addr_must_decimal);
                return;
            }

            String strPWD = EtAccessPwd_Write.getText().toString().trim();// 访问密码

            if (StringUtils.isNotEmpty(strPWD)) {
                if (strPWD.length() != 8) {
                    ShowToast(
                            R.string.uhf_msg_addr_must_len8);
                    return;
                } else if (!mContext.vailHexInput(strPWD)) {
                    ShowToast(
                            R.string.rfid_mgs_error_nohex);

                    return;
                }
            } else {
                strPWD = "00000000";
            }


            String cntStr = EtLen_Write.getText().toString().trim();
            if (StringUtils.isEmpty(cntStr)) {
                ShowToast(R.string.uhf_msg_len_not_null);

                return;
            } else if (!StringUtility.isDecimal(cntStr)) {
                ShowToast(
                        R.string.uhf_msg_len_must_decimal);

                return;
            }





            if (CkWithUii_Write.isChecked())// 指定标签
            {

                String strUII = EtTagUii_Write.getText().toString().trim();
                if (StringUtils.isEmpty(strUII)) {
                    ShowToast(
                            R.string.uhf_msg_tag_must_not_null);
                    return;
                }

                if (mContext.mReader.eraseData(strPWD,
                        RFIDWithUHF.BankEnum.valueOf(SpinnerBank_Write.getSelectedItem()
                                .toString()), Integer.parseInt(strPtr), Integer
                                .valueOf(cntStr), strUII)) {
                    ShowToast("erase succ");
                } else {
                    ShowToast("erase fail");

                }

            } else {

                String strReUII = mContext.mReader.eraseData(strPWD,
                        RFIDWithUHF.BankEnum.valueOf(SpinnerBank_Write.getSelectedItem()
                                .toString()), Integer.parseInt(strPtr), Integer
                                .valueOf(cntStr));// 返回的UII
                if (StringUtils.isNotEmpty(strReUII)) {

                    ShowToast(
                            "erase succ" + "\nUII: "
                                    + strReUII);
                } else {
                    ShowToast("erase fail");
                }
            }


        }

    }



    public class BtWriteOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String strPtr = EtPtr_Write.getText().toString().trim();

            if (StringUtils.isEmpty(strPtr)) {
                ShowToast( R.string.uhf_msg_addr_not_null);

                return;
            } else if (!StringUtility.isDecimal(strPtr)) {
                ShowToast(
                        R.string.uhf_msg_addr_must_decimal);
                return;
            }

            String strPWD = EtAccessPwd_Write.getText().toString().trim();// 访问密码

            if (StringUtils.isNotEmpty(strPWD)) {
                if (strPWD.length() != 8) {
                    ShowToast(
                            R.string.uhf_msg_addr_must_len8);
                    return;
                } else if (!mContext.vailHexInput(strPWD)) {
                    ShowToast(
                            R.string.rfid_mgs_error_nohex);

                    return;
                }
            } else {
                strPWD = "00000000";
            }

            String strData = EtData_Write.getText().toString().trim();// 要写入的内容

            if (StringUtils.isEmpty(strData)) {
                ShowToast(
                        R.string.uhf_msg_write_must_not_null);

                return;
            } else if (!mContext.vailHexInput(strData)) {

                ShowToast( R.string.rfid_mgs_error_nohex);
                return;
            }

            // 多字单次

            String cntStr = EtLen_Write.getText().toString().trim();
            if (StringUtils.isEmpty(cntStr)) {
                ShowToast(R.string.uhf_msg_len_not_null);

                return;
            } else if (!StringUtility.isDecimal(cntStr)) {
                ShowToast(
                        R.string.uhf_msg_len_must_decimal);

                return;
            }

            if ((strData.length()) % 4 != 0) {
                ShowToast(
                        R.string.uhf_msg_write_must_len4x);

                return;
            } else if (!mContext.vailHexInput(strData)) {
                ShowToast( R.string.rfid_mgs_error_nohex);
                return;
            }

            if (CkWithUii_Write.isChecked())// 指定标签
            {

                String strUII = EtTagUii_Write.getText().toString().trim();
                if (StringUtils.isEmpty(strUII)) {
                    ShowToast(
                            R.string.uhf_msg_tag_must_not_null);
                    return;
                }

                if (mContext.mReader.writeData(strPWD,
                        RFIDWithUHF.BankEnum.valueOf(SpinnerBank_Write.getSelectedItem()
                                .toString()), Integer.parseInt(strPtr), Integer
                                .valueOf(cntStr), strData, strUII)) {
                    ShowToast(R.string.uhf_msg_write_succ);
                } else {
                    ShowToast( R.string.uhf_msg_write_fail);

                }

            } else {

                String strReUII = mContext.mReader.writeData(strPWD,
                        RFIDWithUHF.BankEnum.valueOf(SpinnerBank_Write.getSelectedItem()
                                .toString()), Integer.parseInt(strPtr), Integer
                                .valueOf(cntStr), strData);// 返回的UII
                if (StringUtils.isNotEmpty(strReUII)) {

                    ShowToast(
                            getString(R.string.uhf_msg_write_succ) + "\nUII: "
                                    + strReUII);
                } else {
                    ShowToast(R.string.uhf_msg_write_fail);
                }
            }
        }
    }

    public class CkWithUii_WriteClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            EtTagUii_Write.setText("");

            if (CkWithUii_Write.isChecked()) {
                BtUii_Write.setEnabled(true);
                BtUii_Write.setVisibility(View.VISIBLE);
            } else {
                BtUii_Write.setEnabled(false);
                BtUii_Write.setVisibility(View.GONE);
            }
        }
    }

}
