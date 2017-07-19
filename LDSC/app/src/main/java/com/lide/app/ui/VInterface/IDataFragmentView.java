package com.lide.app.ui.VInterface;

/**
 * Created by lubin on 2016/8/18.
 */
public interface IDataFragmentView<T> extends IView {
    void ShowData(T t);

    void startProgressDialog(String result);

    void stopProgressDialog(String result);

    void ShowToast(String text);
}
