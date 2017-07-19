package com.lide.app.presenter.takeStock;

import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.Utils;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.takeStock.TakeStockTransaction;
import com.lubin.bean.TakeStockEpcCollect;
import com.lubin.dao.TakeStockEpcCollectDao;

import java.util.List;

public class EPCPresenter {

    private final DBOperator<TakeStockEpcCollectDao, TakeStockEpcCollect> epcDBOperator;
    IDataFragmentView view;

    public EPCPresenter(IDataFragmentView view) {
        this.view = view;
        epcDBOperator = TakeStockTransaction.getEpcDBOperator();
    }

    public void showEPCList() {
        List<TakeStockEpcCollect> list = epcDBOperator.getItemsByParameters(
                "T.'EPC' ASC"
                , TakeStockEpcCollectDao.Properties.TaskId.eq(Utils.getCurrentTask().getId()));
        view.ShowData(list);
    }

    public void searchDetailEpc(String keyWord) {
        List<TakeStockEpcCollect> list = epcDBOperator.getItemsByParameters(
                "T.'EPC' ASC",
                TakeStockEpcCollectDao.Properties.Epc.like("%" + keyWord + "%")
                , TakeStockEpcCollectDao.Properties.TaskId.eq(Utils.getCurrentTask().getId()));
        view.ShowData(list);
    }

}
