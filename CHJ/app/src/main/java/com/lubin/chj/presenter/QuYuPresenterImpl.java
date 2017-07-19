package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.modle.MInterface.IPickModel;
import com.lubin.chj.modle.PickModel;
import com.lubin.chj.presenter.IPresenter.IQuYuPresenter;
import com.lubin.chj.view.activity.VInterface.IQuYuView;

import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/29  17:51
 * @desc ${TODD}
 */
public class QuYuPresenterImpl implements IQuYuPresenter {

   private PickModel mModel;
    private IQuYuView mView;

    public QuYuPresenterImpl(IQuYuView view) {
        this.mModel = new PickModel();
        this.mView = view;
    }

    @Override
    public void QueryPCbyQybh(String qybh) {
        mModel.queryPc(mModel.getHashMapForQybh(qybh), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    mView.ShowQyMsg(mModel.getQueryPcListBean());
                } else {
                    mView.ShowDialog(returnMsg);
                }

            }
        });
    }
}
