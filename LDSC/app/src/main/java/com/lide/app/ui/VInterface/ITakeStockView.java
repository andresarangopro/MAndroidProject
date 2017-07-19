package com.lide.app.ui.VInterface;

import android.content.Intent;

import com.lide.app.presenter.PInterface.ITakeStockOrderPresenter;

/**
 * Created by lubin on 2016/10/20.
 */

public interface ITakeStockView extends IDataView,IView{
    void showFragment(int FRAGMENT);
    void startNewActivity(Intent intent);
    ITakeStockOrderPresenter getOrderPresenter();
    void startProgressDialog(final String text);
    void stopProgressDialog(final String result);
}
