package com.lubin.chj.presenter.IPresenter;

import com.lubin.chj.bean.Light;

import java.util.List;

/**
 * Created by lubin on 2016/11/8.
 */

public interface ISearchPresenter {

    void QueryPc(String pcs);

    void SetLight(List<Light> lights);

}
