package com.lubin.chj.service;

import android.device.ScanDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.RFID.RFIDReader;
import com.RFID.TAGINFO;
import com.lubin.chj.MApplication;
import com.lubin.chj.utils.SharePreferenceUtil;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by lubin on 16/9/15.
 */

public class ScanServiceWithUHF {
    private Handler msgHandler = null;
    public final static int MSG_INFO = 0;
    public final static int MSG_EPC = 1;
    public final static int MSG_STOP = 3;
    public final static int MSG_OPEN = 4;
    public final static int MSG_TIMER = 5; // 定时器
    private final int DEV_STAT_CLOSE = 0;

    private final int DEV_STAT_OPEN = 1;
    private int devStat = DEV_STAT_CLOSE;
    private final int CMD_CLOSE = 0;    //盘存状态:停止
    private final int CMD_PAUSE = 1;    //盘存状态:暂停
    private final int CMD_INV = 2;            //盘存状态:盘存
    private int devOpt = CMD_PAUSE;    //默认状态：暂停
    public RFIDReader reader = null;
    public ReaderParams Rparams = null;
    private ScanDevice scanDevice;
    private static ScanServiceWithUHF instance = null;
    SharePreferenceUtil spUtil = MApplication.getInstance().getSpUtil();
    private int power = spUtil.getPower();

    public static synchronized ScanServiceWithUHF getInstance() {
        if (instance == null) {
            instance = new ScanServiceWithUHF();
        }
        return instance;
    }

    public boolean init(int iModType) {
        reader = new RFIDReader();
        if (!reader.Init(iModType)) {
            sendMsg(MSG_INFO, "初始化失败");
            return false;
        }
        Rparams = new ReaderParams();
        scanDevice = new ScanDevice();
        return true;
    }

    public void inventory() {
        reader.StartContinueInventory();
    }

    public void pause() {
        reader.StopContinueInventory();
    }

    public void scanBarcode() {
        scanDevice.startScan();
    }

    public void stopScan() {
        scanDevice.stopScan();
    }

    private void Sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean Open() {
        if (devStat != DEV_STAT_CLOSE)
            return true;
        boolean bConn = reader.Connect();
        if (!bConn) {
            Sleep(1000);
            bConn = reader.Connect();
        }
        if (!bConn) {
            sendMsg(MSG_OPEN, "连接失败" + reader.GetLastError());
            return false;
        }
        scanDevice.setOutScanMode(0);
        scanDevice.openScan();
        devStat = DEV_STAT_OPEN;
        reader.SetCallback(mInvCallback);
        reader.SetPower(power);
        return true;
    }

    public boolean OpenByTcp(String IP, int port) {
        if (devStat != DEV_STAT_CLOSE)
            return true;
        // 模块上电
        boolean bConn = reader.ConnectByIP(IP, port);
        if (!bConn) {
            Sleep(1000);
            bConn = reader.ConnectByIP(IP, port);
        }
        if (!bConn) {
            sendMsg(MSG_OPEN, "连接失败" + reader.GetLastError());
            Log.d("test", reader.GetLastError());
            return false;
        }
        devStat = DEV_STAT_OPEN;
        reader.SetCallback(mInvCallback);
        reader.SetPower(power);
        boolean readMode = reader.SetReadMode(1);
        Log.d("test", "readMode:" + readMode);
        return true;
    }

    public boolean Close() {
        if (DEV_STAT_CLOSE != devStat)
            reader.Disconnect();
        scanDevice.closeScan();
        devStat = DEV_STAT_CLOSE;
        return true;
    }

    public void SetHandleFunc(Handler handler) {
        msgHandler = handler;
    }


    public void sendMsg(int msgType, String str) {
        if (msgHandler != null) {
            // 处理数据
            Message msg = new Message();
            msg.what = msgType;
            Bundle bundle = new Bundle();
            bundle.putString("msg", str); // 往Bundle中存放数据
            msg.setData(bundle);// mes利用Bundle传递数据
            if (msgHandler != null)
                msgHandler.sendMessage(msg);// 用activity中的handler发送消息
        }
    }

    private RFIDReader.InvCallBack mInvCallback = new RFIDReader.InvCallBack() {
        public String HexToString(byte[] bArray) {
            StringBuffer sb = new StringBuffer(bArray.length);
            String sTemp;
            for (int i = 0; i < bArray.length; i++) {
                sTemp = Integer.toHexString(0xFF & bArray[i]);
                if (sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }
            return sb.toString();
        }

        private void SendMsg(String EpcID) {
            Message msg = new Message();
            msg.what = ScanServiceWithUHF.MSG_EPC;
            Bundle bundle = new Bundle();
            bundle.putString("msg", EpcID); // 往Bundle中存放数据
            msg.setData(bundle); // mes利用Bundle传递数据
            if (msgHandler != null)
                msgHandler.sendMessage(msg);// 用activity中的handler发送消息
        }

        int iCount = 0;

        @Override
        public void execute(List<TAGINFO> lstTagInfo) {
            iCount++;
            if (null == lstTagInfo || lstTagInfo.size() == 0)
                Log.e("Inventory", "错误内容");
            Hashtable<byte[], TAGINFO> htNewTagInfo = new Hashtable<byte[], TAGINFO>();
            String szTag = "";

            for (int i = 0; i < lstTagInfo.size(); i++) {
                TAGINFO tfs = lstTagInfo.get(i);
                if (null == tfs)
                    continue;
                //Todo
                String barcodeStr = HexToString(tfs.EpcId);
                String epc = null;
                if (barcodeStr.length() > 10) {
                    epc = barcodeStr.substring(0, 10);
                } else {
                    epc = barcodeStr;
                }
                szTag = epc + ";" + tfs.ReadCnt;
                SendMsg(szTag);
            }

        }
    };

    //set power
    public void SetPower(int power) {
        boolean setPower = reader.SetPower(power);
        Log.d("test", "setPower:" + setPower);
    }

    //query power
    public int GetPower() {
        return reader.GetPower();
    }

    //Set ReadMode: 0-单标签读 1-多标签读
    public boolean SetReadMode(int invMode) {
        return reader.SetReadMode(invMode);
    }

    //Query Read Mode
    public int GetReadMode() {
        return reader.GetReadMode();
    }

    public boolean IsOpen() {
        return devStat == DEV_STAT_CLOSE ? false : true;
    }
}
