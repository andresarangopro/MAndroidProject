package com.lide.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lide.app.R;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.CreateModel;
import com.lide.app.persistence.util.NetWorkUtil;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/10/8  16:13
 * @desc ${测试}
 */
public class TestActivity extends BaseActivity {


    private CreateModel createModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        NetWorkUtil.init();
        createModel = new CreateModel();
    }

    @OnClick({R.id.bt_test1, R.id.bt_test2, R.id.bt_test3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_test1:
                try {
                    createModel.createOutBoundOrder("d37079c3-bcc1-4807-9b9c-2df1f4229390", new OnLoadFinishListener() {
                        @Override
                        public void OnLoadFinish(Map<String, String> map) {
                            Log.d(TAG, "OnLoadFinish: " + map.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_test2:
                break;
            case R.id.bt_test3:
                break;
        }
    }

}