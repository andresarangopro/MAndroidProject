package com.lubin.chj.view.activity.VInterface;

/**
 * Created by lubin on 2016/11/8.
 */

public interface ISearchView<T> extends IShowDialogView {
    void ShowData(T t);

    void ShowToast(String text);

    void changeBtnView();
}
