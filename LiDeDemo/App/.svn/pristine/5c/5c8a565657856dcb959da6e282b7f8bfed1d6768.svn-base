package com.lide.app.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.lide.app.bean.DBBean.CheckTask;
import com.lide.app.bean.DBBean.EPC;
import com.lide.app.bean.DBBean.User;
import com.lide.app.config.Configs;
import com.lide.app.util.StringUtils;
import com.rscja.deviceapi.RFIDWithUHF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScanService extends Service {
    private ServiceReceiver myReceiver;
    private RFIDWithUHF mReader;
    private int inventoryFlag = 1;
    private boolean loopFlag = false;
    private User user;
    private CheckTask task;
    private List<String> EPCs;

    public void onCreate() {
        super.onCreate();
        init();
        initReceiver();

//        user = new User();
//        user.setWarehouseCode("001");
//        user.setUserName("admin");
//        user.setPassword("admin");
//        user.setSaveState(2);
//        user.setApiKey("5a3e93bd-b8dd-4d1a-afb1-731d15fb2e4c");
//        user.save();
//
//        task = new CheckTask();
//        task.setName("tw001");
//        task.setNumber(98);
//        task.setUser(user);
//        task.save();

        EPCs = new ArrayList<>();
    }

    private void init() {
        try {
            mReader = RFIDWithUHF.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        stopInventory();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyIBinder();
    }

    private void initReceiver() {
        myReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Configs.START_SCAN);
        intentFilter.addAction(Configs.SET_INVENTORY);
        intentFilter.addAction(Configs.STOP_SCAN);
        registerReceiver(myReceiver, intentFilter);
    }

    public class MyIBinder extends Binder {

        public ScanService getService() {
            return ScanService.this;
        }
    }

    public void setInventoryFlag(int inventoryFlag) {
        this.inventoryFlag = inventoryFlag;
    }

    private void SearchTag(String between,int Q) {

        switch (inventoryFlag) {
            case 0:// 单步
            {
                String strUII = mReader.inventorySingleTag();
                if (!TextUtils.isEmpty(strUII)) {
                    String strEPC = mReader.convertUiiToEPC(strUII);
                    Log.d("result",strEPC);
                } else {
                    Log.d("mReadLift","识别失败");
                }

            }
            break;
            case 1:// 单标签循环
            {

                if (mReader.startInventoryTag((byte) 0, (byte) 0)) {

                    loopFlag = true;

                    new TagThread(StringUtils.toInt(between, 0)).start();
                } else {
                    mReader.stopInventory();
                    Log.d("mReadLift","开启识别标签失败");
                }
            }

            break;
            case 2:// 防碰撞
            {
                if (mReader.startInventoryTag((byte) 1, Q)) {
                    loopFlag = true;
                    new TagThread(StringUtils.toInt(between, 0)).start();
                } else {
                    mReader.stopInventory();
                    Log.d("mReadLift","开启识别标签失败");
                }
            }
            break;
        }
    }

    private void stopInventory() {

        if (loopFlag) {

            loopFlag = false;

            if (mReader.stopInventory()) {
                Log.d("mReadLift","停止识别成功");
            } else {
                Log.d("mReadLift","停止识别失败");
            }
        }
    }

    class TagThread extends Thread {

        private int mBetween = 80;
        HashMap<String, String> map;

        public TagThread(int iBetween) {
            mBetween = iBetween;
        }

        public void run() {
            String strTid;
            String strResult;

            String[] res = null;

            while (loopFlag) {
                res = mReader.readTagFormBuffer();

                if (res != null) {
                    strTid = res[0];
                    if (!strTid.equals("0000000000000000") && !strTid.equals("000000000000000000000000")) {
                        strResult = "TID:" + strTid + "\n";
                    } else {
                        strResult = "";
                    }

                    String e = mReader.convertUiiToEPC(res[1]);

                    String result = strResult + "EPC:"
                            +e+ "@"
                            + res[2];

                    if(!EPCs.contains(e)){
                        EPC epc = new EPC();
                        epc.setDistance(res[2]);
                        epc.setEPC(res[1]);
                        epc.setTask(task);
                        epc.save();
                        EPCs.add(e);
                    }

                    Intent intent = new Intent(Configs.RECEIVE_TAG);
                    intent.putExtra(Configs.Result_Tag,result);
                    ScanService.this.sendBroadcast(intent);

                }
                try {
                    sleep(mBetween);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class ServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(Configs.SET_INVENTORY.equals(action)){
                int inventory = intent.getIntExtra(Configs.Inventory, 1);
                setInventoryFlag(inventory);
            }
            if(Configs.STOP_SCAN.equals(action)){
                stopInventory();
            }
            if(Configs.START_SCAN.equals(action)){
                String between = intent.getStringExtra(Configs.Between);
                int Q = intent.getIntExtra(Configs.Q, 0);
                SearchTag(between,Q);
            }
        }
    }

}
