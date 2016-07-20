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

import com.lide.app.config.Configs;
import com.lide.app.util.StringUtils;
import com.lubin.bean.CheckTask;
import com.lubin.bean.EPC;
import com.rscja.deviceapi.RFIDWithUHF;

import java.util.HashMap;

public class ScanService extends Service {
    private ServiceReceiver myReceiver;
    private RFIDWithUHF mReader;
    private int inventoryFlag = 1;
    private boolean loopFlag = false;
    private DbService db;
    private CheckTask checkTask;

    public void onCreate() {
        super.onCreate();
        init();
        initReceiver();

    }

    private void init() {
        try {
            mReader = RFIDWithUHF.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db = DbService.getInstance(this);
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

    private void SearchTag(int between,int Q,int num) {

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

                    new TagThread(between,num).start();
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
                    new TagThread(between,num).start();
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
        int num;

        public TagThread(int mBetween,int num) {
            this.mBetween = mBetween;
            this.num = num;
        }

        public void run() {
            String strResult;
            String[] res = null;

            while (loopFlag) {

                res = mReader.readTagFromBuffer();

                if (res != null) {

                    String strTid = res[0];
                    String EPC = mReader.convertUiiToEPC(res[1]);

//                    if (!strTid.equals("0000000000000000") && !strTid.equals("000000000000000000000000")) {
//                        strResult = "TID:" + strTid + "\n";
//                    } else {
//                        strResult = "";
//                    }


//                    String result = strResult + "EPC:"
//                            +e+ "@"
//                            + res[2];

                    if(db.queryEPC(EPC).size()==0){
                        EPC epc = new EPC();
                        epc.setTid(strTid);
                        epc.setEpc(EPC);
                        epc.setIsUploading(false);
                        epc.setCheckTask(checkTask);
                        db.saveEPC(epc);

                        Intent intent = new Intent(Configs.RECEIVE_TAG);
                        intent.putExtra(Configs.Tags,num++);
                        ScanService.this.sendBroadcast(intent);
                    }

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

                checkTask = (CheckTask) intent.getSerializableExtra(Configs.CheckTask);

                int between = intent.getIntExtra(Configs.Between,80);
                int num = intent.getIntExtra(Configs.Number,0);
                int Q = intent.getIntExtra(Configs.Q, 0);

                SearchTag(between,Q,num);
            }
        }
    }

}
