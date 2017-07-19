package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnLoginFinshListener;
import com.lubin.chj.bean.LoginReturn;
import com.lubin.chj.bean.User;
import com.lubin.chj.modle.LoginModelImpl;
import com.lubin.chj.modle.MInterface.ILoginModel;
import com.lubin.chj.presenter.IPresenter.ILoginPresenter;
import com.lubin.chj.view.activity.VInterface.ILoginView;

/**
 * @author DaiJiCheng
 * @time 2016/9/29  10:12
 * @desc ${TODD}
 */
public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginModel mModel;
    private ILoginView mView;
    private LoginReturn mLoginReturn;

    public LoginPresenterImpl(ILoginView view) {
        this.mView = view;
        mModel = new LoginModelImpl();
    }

    @Override
    public void Login(User user, String equipmentID) {
        mModel.login(user, equipmentID, new OnLoginFinshListener() {
            @Override
            public void onLoginFinish(Object result) {
                mLoginReturn = (LoginReturn) result;
                if (mLoginReturn.getReturnCode().equals("0000")) {
                    mView.onLoginFinish(mLoginReturn);
                } else {
                    mView.showReturnMessage(mLoginReturn.getReturnMsg());
                }

            }
        });
    }
}
