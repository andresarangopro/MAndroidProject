package com.lide.app.ui.takeStock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.persistence.view.CircleProgressView;
import com.lide.app.presenter.takeStock.UploadCollectPresenterImpl;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.VInterface.IUploadCollectView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpLoadCollectActivity extends BaseActivity implements IUploadCollectView {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tb_upload)
    Toolbar tbUpload;
    @BindView(R.id.cp_upload)
    CircleProgressView cpUpload;

    public static boolean isUploadAll = false;
    @BindView(R.id.tv_upload_num)
    TextView tvUploadNum;
    @BindView(R.id.tv_error_num)
    TextView tvErrorNum;
    @BindView(R.id.tv_succeed_num)
    TextView tvSucceedNum;

    private UploadCollectPresenterImpl mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        initView();
        mPresenter = new UploadCollectPresenterImpl(this);
    }

    private void initView() {
        setSupportActionBar(tbUpload);
        tbUpload.setNavigationIcon(R.mipmap.back_login);
        tbUpload.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.UploadEpcList();
        tbUpload.setTitle("");
    }

    //设置文本字体
    public void setText(String titleText) {
        tvToolbarTitle.setText(titleText);
    }
    //设置进度图画的进度
    @Override
    public void ShowProgress(int progress) {
        cpUpload.setProgressNotInUiThread(progress);
    }
    //设置本次上传的成功数与失败数
    @Override
    public void ShowNum(int succeed, int error) {
        tvSucceedNum.setText(String.valueOf(succeed));
        tvErrorNum.setText(String.valueOf(error));
    }
    //设置本次上传的总数
    @Override
    public void ShowUploadNum(int num) {
        tvUploadNum.setText(String.valueOf(num));
    }

    @OnClick({R.id.btn_diff, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_diff:
                //跳转到差异界面，看本任务关联的盘点单的差异
                startActivity(new Intent(this, QueryDiffActivity.class));
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
