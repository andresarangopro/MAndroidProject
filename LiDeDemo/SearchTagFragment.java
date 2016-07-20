package com.lide.app.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.ui.MainActivity;
import com.lide.app.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchTagFragment extends FragmentBase {

    private boolean loopFlag = false;
    private int inventoryFlag = 1;
    private LinearLayout llQValue;
    Handler handler;
    private ArrayList<HashMap<String, String>> tagList;
    SimpleAdapter adapter;

    Button BtClear;
    TextView tv_count;
    RadioGroup RgInventory;
    RadioButton RbInventorySingle;
    RadioButton RbInventoryLoop;
    RadioButton RbInventoryAnti;
    Spinner SpinnerQ;
    Button BtInventory;
    ListView LvTags;
    byte initQ;

    private EditText et_between;
    private LinearLayout llContinuous;

    private MainActivity mContext;

    private HashMap<String, String> map;
    private ScanReceiver scanReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = (MainActivity) getActivity();
        initReceiver();
        return inflater.inflate(R.layout.fragment_search_tag, container, false);

    }

    private void initReceiver() {
        scanReceiver = new ScanReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Configs.RECEIVE_TAG);
        mContext.registerReceiver(scanReceiver, intentFilter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("MY", "UHFReadTagFragment.onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        tagList = new ArrayList<HashMap<String, String>>();

        BtClear = (Button) mContext.findViewById(R.id.BtClear);
        tv_count = (TextView) mContext.findViewById(R.id.tv_count);
        RgInventory = (RadioGroup) mContext.findViewById(R.id.RgInventory);
        RbInventorySingle = (RadioButton) mContext
                .findViewById(R.id.RbInventorySingle);
        RbInventoryLoop = (RadioButton) mContext
                .findViewById(R.id.RbInventoryLoop);
        RbInventoryAnti = (RadioButton) mContext
                .findViewById(R.id.RbInventoryAnti);
        SpinnerQ = (Spinner) mContext.findViewById(R.id.SpinnerQ);
        BtInventory = (Button) mContext.findViewById(R.id.BtInventory);
        LvTags = (ListView) mContext.findViewById(R.id.LvTags);

        et_between = (EditText) mContext.findViewById(R.id.et_between);

        llContinuous = (LinearLayout) mContext.findViewById(R.id.llContinuous);

        adapter = new SimpleAdapter(mContext, tagList, R.layout.item_list_tag,
                new String[]{"tagUii", "tagLen", "tagCount", "tagRssi"},
                new int[]{R.id.TvTagUii, R.id.TvTagLen, R.id.TvTagCount,
                        R.id.TvTagRssi});

        BtClear.setOnClickListener(new BtClearClickListener());
        RgInventory
                .setOnCheckedChangeListener(new RgInventoryCheckedListener());
        BtInventory.setOnClickListener(new BtInventoryClickListener());
        SpinnerQ.setEnabled(false);
        SpinnerQ.setOnItemSelectedListener(new QItemSelectedListener());

        llQValue = (LinearLayout) mContext.findViewById(R.id.llQValue);

        LvTags.setAdapter(adapter);
        clearData();

        SpinnerQ.setSelection(3);
        getActivity().setTitle("扫描数据");
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = new Intent(Configs.STOP_SCAN);
        mContext.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(scanReceiver);
    }

    /**
     * 添加EPC到列表中
     *
     * @param epc
     */
    private void addEPCToList(String epc, String rssi) {
        if (!TextUtils.isEmpty(epc)) {
            int index = checkIsExist(epc);

            map = new HashMap<String, String>();

            map.put("tagUii", epc);
            map.put("tagCount", String.valueOf(1));
            map.put("tagRssi", rssi);

            // mContext.getAppContext().uhfQueue.offer(epc + "\t 1");

            if (index == -1) {
                tagList.add(map);
                LvTags.setAdapter(adapter);
                tv_count.setText("" + adapter.getCount());
            } else {
                int tagcount = Integer.parseInt(
                        tagList.get(index).get("tagCount"), 10) + 1;

                map.put("tagCount", String.valueOf(tagcount));

                tagList.set(index, map);

            }

            adapter.notifyDataSetChanged();

        }
    }

    public class BtClearClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            clearData();

        }
    }

    private void clearData() {
        tv_count.setText("0");

        tagList.clear();

        Log.i("MY", "tagList.size " + tagList.size());

        adapter.notifyDataSetChanged();
    }

    public class RgInventoryCheckedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            llQValue.setVisibility(View.GONE);
            llContinuous.setVisibility(View.GONE);

            if (checkedId == RbInventorySingle.getId()) {
                // 单步识别
                inventoryFlag = 0;
                SpinnerQ.setEnabled(false);
            } else if (checkedId == RbInventoryLoop.getId()) {
                // 单标签循环识别
                inventoryFlag = 1;
                SpinnerQ.setEnabled(false);

                llContinuous.setVisibility(View.VISIBLE);

            } else {
                // 防碰撞识别
                inventoryFlag = 2;
                SpinnerQ.setEnabled(true);
                llContinuous.setVisibility(View.VISIBLE);
                llQValue.setVisibility(View.VISIBLE);
            }
        }
    }

    public class QItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            initQ = Byte.valueOf((String) SpinnerQ.getSelectedItem(), 10);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public class BtInventoryClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            read();
        }
    }

    private void read() {
        if (BtInventory.getText().equals(mContext.getString(R.string.btInventory))) {
            setViewEnabled(false);
            Intent intent = new Intent(Configs.START_SCAN);
            mContext.sendBroadcast(intent);
            BtInventory.setText(mContext.getString(R.string.title_stop_Inventory));
        } else {
            setViewEnabled(true);
            Intent intent = new Intent(Configs.STOP_SCAN);
            mContext.sendBroadcast(intent);
            BtInventory.setText(mContext.getString(R.string.btInventory));
        }
    }


    private void setViewEnabled(boolean enabled) {
        RbInventorySingle.setEnabled(enabled);
        RbInventoryLoop.setEnabled(enabled);
        RbInventoryAnti.setEnabled(enabled);
        et_between.setEnabled(enabled);
        SpinnerQ.setEnabled(enabled);
    }

    /**
     * 判断EPC是否在列表中
     *
     * @param strEPC 索引
     * @return
     */
    public int checkIsExist(String strEPC) {
        int existFlag = -1;
        if (StringUtils.isEmpty(strEPC)) {
            return existFlag;
        }

        String tempStr = "";
        for (int i = 0; i < tagList.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp = tagList.get(i);

            tempStr = temp.get("tagUii");

            if (strEPC.equals(tempStr)) {
                existFlag = i;
                break;
            }
        }

        return existFlag;
    }

    class ScanReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Configs.RECEIVE_TAG.equals(action)) {
//                String result = intent.getStringExtra(Configs.Tags);
//                String[] strs = result.split("@");
//                addEPCToList(strs[0], strs[1]);
            }
        }
    }
}
