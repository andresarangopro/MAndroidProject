package com.lide.app.ui.inbound.LS;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.adapter.BaseListAdapter;
import com.lide.app.adapter.ViewHolder;
import com.lide.app.config.Configs;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.widget.ResourceUtil;
import com.lide.app.persistence.widget.recycler.AbsAdapterItem;
import com.lide.app.persistence.widget.recycler.BaseRecyclerAdapter;
import com.lide.app.persistence.widget.recycler.GridRecyclerView;
import com.lide.app.presenter.inbound.InBoundPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.InBoundDetailDao;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InboundDetailFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

    @BindView(R.id.order_address_all)
    TextView tvFormWarehouseName;

    @BindView(R.id.order_all_num)
    TextView tvQtyNum;
    @BindView(R.id.order_state)
    TextView tvOrderState;

    @BindView(R.id.particulars_layout)
    FrameLayout particularsLayout;


    @BindView(R.id.p_reset)
    Button btn_Reset;
    @BindView(R.id.p_start)
    Button btn_epc;
    @BindView(R.id.sku_start)
    Button btn_sku;

    private GridRecyclerView particularsListView;
    BaseRecyclerAdapter particularsListAdapter;

    private View mView;
    private InboundActivity mActivity;
    private InBoundPresenter inBoundPresenter;
    private DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator;
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;
    private DBOperator<InBoundDetailDao, InBoundDetail> detailDBOperator;
    private boolean canUpload = true;

    //同个单里面有多个箱，同个箱里面有多个单，
    // 就造成啦InboundCase,InboundDetail,InboundOperate里面存在code相同，但是id不同的数据；

    //混合模式是指：本来要通过扫描条码来收货的操作数据，支持标签来收货

    //正常收货是指：后端存在的单里面有什么类型（标签、条码）操作数据，就是以什么类型操作方式来收货

    //允许超收是指: 条码的操作数据允许操作数超过单据数的限制，人话是：后端让你收多少，你可以想收多少都行

    //混合模式下，epc操作数据里面的单据数是为零，操作数为一
    //因为该单下载下来时，是没有epc操作表的数据，单里面的单据数全部分配到
    //sku操作明细里面，所以要想用epc来代表sku操作数，就需要生成一条操作表数据是
    //epc:0000000001 qty:0 operateQty:1 其中的qty为零是因为本地生成的，不存在单据数
    //operateQty:1 是代表0000000001这个epc所绑定的sku的操作数为增加1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail_in_bound_order, container, false);
        ButterKnife.bind(this, mView);
        initViews();
        mActivity = (InboundActivity) getActivity();
        inBoundPresenter = new InBoundPresenter(this);
        orderDBOperator = InboundTransaction.getOrderDBOperator();
        detailDBOperator = InboundTransaction.getDetailDBOperator();
        operateDBOperator = InboundTransaction.getOperateDBOperator();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    //当fragment消失或者展示给用户时，都会调用这个回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //第一次回调这个方法时，mActivity还没有初始化
            if (mActivity == null) return;
            //刷新界面中展示的数据
            refreshData();
        }
    }

    private void initViews() {
        particularsListView = new GridRecyclerView(getActivity());
        ((ViewGroup) particularsLayout).addView(particularsListView, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        particularsListView.setSpanCount(1);
        particularsListView.setItemMargin(0);
        particularsListAdapter = new BaseRecyclerAdapter();
        particularsListView.setAdapter(particularsListAdapter);
        particularsListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        particularsListAdapter.clear();
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {

    }

    boolean isUploadFinish = false;

    @Override
    public void showDialog(String result) {
        if (result.equals("上传成功~")) {
            //判断是否是已经上传弹窗完成，以避免弹出重复的上传成功，因为多箱情况下，是一箱一箱上传
            if (isUploadFinish) return;
            isUploadFinish = true;
            super.showDialog(result);
            List<InBoundOperate> operates = null;
            //更新已上传的操作明细
            if (mActivity.orderId != 0) {
                operates = orderDBOperator.getItemByID(mActivity.orderId).getOperates();
            } else {
                operates = operateDBOperator.getItemsByParameters(null
                        , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList()));
            }
            for (InBoundOperate operate : operates) {
                operate.setIsUpload(true);
            }
            operateDBOperator.updateData(operates);
        } else {
            super.showDialog(result);
        }
    }

    public void upload() {
        //判断是否可以上传，如果该单是已经审核完成，不允许上传
        if (!canUpload) return;
        if (Utils.getApiKey() == null) {
            Intent login = new Intent(getActivity(), LoginActivity.class);
            login.putExtra("isAtNet", true);
            startActivityForResult(login, Configs.LOGIN);
        } else {
            if (!SPUtils.get(getActivity(), "deviceId", "").equals("")) {
                //判断是否有sku明细里面有没有存在操作数大于单据数的
                List<InBoundDetail> details;
                if (mActivity.orderId != 0) {
                    details = detailDBOperator.getItemsByParameters(null
                            , InBoundDetailDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                            , new WhereCondition.StringCondition("T.QTY < T.OPERATE_QTY"));
                } else {
                    details = detailDBOperator.getItemsByParameters(null
                            , InBoundDetailDao.Properties.InBoundOrderId.in(mActivity.getCasesIdList())
                            , new WhereCondition.StringCondition("T.QTY < T.OPERATE_QTY"));
                }
                if (details.size() == 0) {
                    //没有直接上传
                    startUpload();
                    return;
                }
                //如果sku明细里面有存在操作数大于单据数的，弹窗提示sku明细，询问用户是否继续上传
                details.add(0, new InBoundDetail());
                LinearLayout listContainer = new LinearLayout(getActivity());
                listContainer.setOrientation(LinearLayout.VERTICAL);
                ListView listView = new ListView(getActivity());
                listView.setAdapter(new DialogListAdapter(getActivity(), details));
                listContainer.addView(listView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350, 1));

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("超出单据数，是否继续上传？");
                builder.setView(listContainer);
                builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpload();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            } else {
                ShowToast("请先设置设备");
            }
        }
    }

    private void startUpload() {
        //开始上传时，让已上传的flag变成false，不然无法弹窗提示上传成功
        isUploadFinish = false;
        if (mActivity.orderId != 0) {
            //取出操作表里面没有上传过的数据，与单id关系的数据
            List<InBoundOperate> container;
            final InBoundOrder inBoundOrder;
            inBoundOrder = orderDBOperator.getItemByID(mActivity.orderId);
            container = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.IsUpload.eq(false));
            if (container.size() == 0) {
                ShowToast("没有可上传的数据~");
            }
            inBoundPresenter.UploadInboundOperate(inBoundOrder.getOrderId(), container);
        } else {
            //遍历同名箱不同id的箱，因为不属于同一个单，需要逐单上传
            for (InBoundCase inBoundCase : mActivity.cases) {
                List<InBoundOperate> container;
                container = operateDBOperator.getItemsByParameters(null
                        , InBoundOperateDao.Properties.InBoundCaseId.eq(inBoundCase.getId())
                        , InBoundOperateDao.Properties.IsUpload.eq(false));
                String orderId = inBoundCase.getInBoundOrder().getOrderId();
                if (container.size() == 0) {
                    ShowToast("没有可上传的数据~");
                    continue;
                }
                inBoundPresenter.UploadInboundOperate(orderId, container);
            }
        }
    }

    //重置操作表数据，sku操作数据不允许重置，只有epc没上传的操作数据才能允许重置
    private void reset() {
        startProgressDialog("重置中...");
        runOnWorkThread(new Runnable() {
            @Override
            public void run() {
                List<InBoundOperate> container;
                //混合收货模式
                if (mActivity.isMixedModel) {
                    if (mActivity.orderId != 0) {
                        //取出与需操作的单的id关联的epc操作数据，做删除操作
                        container = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                                , InBoundOperateDao.Properties.IsUpload.eq(false)
                                , InBoundOperateDao.Properties.Qty.eq(0)
                                , InBoundOperateDao.Properties.OperateQty.eq(1));
                        if (container.size() == 0) {
                            stopProgressDialog("没有数据可重置~");
                            return;
                        }
                        operateDBOperator.deleteDatas(container);
                    } else {
                        //取出与需操作的同箱号不同id的箱关联的epc操作数据，做删除操作
                        container = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundOrderId.in(mActivity.getCasesIdList())
                                , InBoundOperateDao.Properties.IsUpload.eq(false)
                                , InBoundOperateDao.Properties.Qty.eq(0)
                                , InBoundOperateDao.Properties.OperateQty.eq(1));
                        if (container.size() == 0) {
                            stopProgressDialog("没有数据可重置~");
                            return;
                        }
                        operateDBOperator.deleteDatas(container);
                    }
                } else {
                    //正常收货模式
                    if (mActivity.orderId != 0) {
                        //取出与需操作的单的id关联的epc操作数据，把里面的操作数归于0，然后更新而不是删除操作，谨记
                        container = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                                , InBoundOperateDao.Properties.IsUpload.eq(false)
                                , InBoundOperateDao.Properties.Epc.isNotNull()
                                , InBoundOperateDao.Properties.OperateQty.eq(1));
                        if (container.size() == 0) {
                            stopProgressDialog("没有数据可重置~");
                            return;
                        }
                        for (InBoundOperate operate : container) {
                            operate.setOperateQty(0);
                        }
                        operateDBOperator.updateData(container);
                    } else {
                        //取出与需操作的同箱号不同id的箱关联的epc操作数据，把里面的操作数归于0，然后更新而不是删除操作，谨记
                        container = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                                , InBoundOperateDao.Properties.IsUpload.eq(false)
                                , InBoundOperateDao.Properties.Epc.isNotNull()
                                , InBoundOperateDao.Properties.OperateQty.eq(1));
                        if (container.size() == 0) {
                            stopProgressDialog("没有数据可重置~");
                            return;
                        }
                        for (InBoundOperate operate : container) {
                            operate.setOperateQty(0);
                        }
                        operateDBOperator.updateData(container);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //刷新在缓存中的数据
                        mActivity.recount();
                        //刷新界面中展示的数据
                        refreshData();
                        stopProgressDialog("重置成功~");
                    }
                });
            }
        });
    }

    /**
     * 展示明细
     */
    private void refreshData() {
        List<InBoundDetail> details = new ArrayList<>();
        if (mActivity.orderId != 0) {
            InBoundOrder inBoundOrder = orderDBOperator.getItemByID(mActivity.orderId);
            Iterator<InBoundDetail> iterator = inBoundOrder.getDetails().iterator();
            mActivity.setTitleText("单号：" + inBoundOrder.getCode());
            tvOrderState.setText(status(inBoundOrder.getStatus()));
            tvFormWarehouseName.setText(inBoundOrder.getFormWarehouseName());
            tvQtyNum.setText(inBoundOrder.getQty() + "/" + inBoundOrder.getOperateQty());
            //相同的条码的InboundDetail合并展示
            while (iterator.hasNext()) {
                InBoundDetail next = iterator.next();
                boolean flag = false;
                for (InBoundDetail inBoundDetail : details) {
                    if (inBoundDetail.getBarcode().equals(next.getBarcode())) {
                        inBoundDetail.setQty(inBoundDetail.getQty() + next.getQty());
                        inBoundDetail.setOperateQty(inBoundDetail.getOperateQty() + next.getOperateQty());
                        flag = true;
                    }
                }
                if (!flag) {
                    InBoundDetail inBoundDetail = new InBoundDetail();
                    inBoundDetail.setQty(next.getQty());
                    inBoundDetail.setOperateQty(next.getOperateQty());
                    inBoundDetail.setBarcode(next.getBarcode());
                    inBoundDetail.setSkuName(next.getSkuName());
                    inBoundDetail.setDate(next.getDate());
                    details.add(inBoundDetail);
                }
            }
            //展示明细
            showInBoundOrderData(details);
            //如果单据状态是已完成，是重置和进入操作的按钮失效
            if (inBoundOrder.getStatus() == 2) {
                btn_epc.setBackground(greyBackground);
                btn_epc.setEnabled(false);
                btn_Reset.setBackground(greyBackground);
                btn_Reset.setEnabled(false);
                btn_sku.setBackground(greyBackground);
                btn_sku.setEnabled(false);
                canUpload = false;
            }
        } else {
            int qty = 0;
            int operateQty = 0;
            Iterator<InBoundDetail> iterator = detailDBOperator
                    .getItemsByCondition(InBoundDetailDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList()))
                    .iterator();
            //相同的条码的InboundDetail合并展示
            while (iterator.hasNext()) {
                InBoundDetail next = iterator.next();
                boolean flag = false;
                for (InBoundDetail inBoundDetail : details) {
                    if (inBoundDetail.getBarcode().equals(next.getBarcode())) {
                        inBoundDetail.setQty(inBoundDetail.getQty() + next.getQty());
                        inBoundDetail.setOperateQty(inBoundDetail.getOperateQty() + next.getOperateQty());
                        flag = true;
                    }
                }
                if (!flag) {
                    InBoundDetail inBoundDetail = new InBoundDetail();
                    inBoundDetail.setQty(next.getQty());
                    inBoundDetail.setOperateQty(next.getOperateQty());
                    inBoundDetail.setBarcode(next.getBarcode());
                    inBoundDetail.setSkuName(next.getSkuName());
                    inBoundDetail.setDate(next.getDate());
                    details.add(inBoundDetail);
                }
                qty += next.getQty();
                operateQty += next.getOperateQty();
            }
            tvOrderState.setText("");
            tvQtyNum.setText(qty + "/" + operateQty);
            mActivity.setTitleText("箱号：" + mActivity.caseCode);
            tvFormWarehouseName.setText(mActivity.cases.get(0).getInBoundOrder().getFormWarehouseName());
            //展示明细
            showInBoundOrderData(details);
            //如果单据状态是已完成，是重置和进入操作的按钮失效
            if (mActivity.cases.get(0).getStatus() == 2) {
                btn_epc.setBackground(greyBackground);
                btn_epc.setEnabled(false);
                btn_Reset.setBackground(greyBackground);
                btn_Reset.setEnabled(false);
                btn_sku.setBackground(greyBackground);
                btn_sku.setEnabled(false);
                canUpload = false;
            }
        }
    }

    public void showInBoundOrderData(List<InBoundDetail> details) {
        if (particularsListAdapter != null) {
            particularsListAdapter.clear();
            for (int i = 0; i < details.size(); i++) {
                particularsListAdapter.addItem(new ParticularsListItem(details.get(i)));
            }
        }
    }

    public class ParticularsListItem extends AbsAdapterItem {
        InBoundDetail mInBoundDetail;

        public ParticularsListItem(InBoundDetail mInBoundDetail) {
            this.mInBoundDetail = mInBoundDetail;
        }

        @Override
        public View onCreateView(ViewGroup parent, int position) {
            int layout = ResourceUtil.getLayoutId(getActivity(), "particulars_tablerow_layout");
            final View view = View.inflate(getActivity(), layout, null);

            return view;
        }

        @Override
        public void onBindView(BaseRecyclerAdapter.BaseViewHolder holder, View view, int position) {
            int id = ResourceUtil.getId(getActivity(), "table_row");
            View tableRow = view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "view_bg");
            View viewBg = view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "particulars_id");
            TextView particularsID = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "particulars_sku");
            TextView particularsSKU = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "particulars_name");
            TextView particularsNAME = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "particulars_order_num");
            TextView particularsOrderNum = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "particulars_operation_num");
            TextView particularsOperationNum = (TextView) view.findViewById(id);

            particularsID.setText(String.valueOf((position + 1)));
            particularsSKU.setText(mInBoundDetail.getBarcode());
            particularsNAME.setText(mInBoundDetail.getSkuName());
            particularsOrderNum.setText(String.valueOf(mInBoundDetail.getQty()));
            particularsOperationNum.setText(String.valueOf(mInBoundDetail.getOperateQty()));

            if (mInBoundDetail.getQty() == mInBoundDetail.getOperateQty()) {
                tableRow.setBackgroundColor(getResources().getColor(R.color.success_text));
                viewBg.setBackgroundColor(getResources().getColor(R.color.white));
                particularsOperationNum.setTextColor(getResources().getColor(R.color.goods_delivery_title_bg));
            } else {
                tableRow.setBackgroundColor(getResources().getColor(R.color.white));
                viewBg.setBackgroundColor(getResources().getColor(R.color.shadow));
                particularsOperationNum.setTextColor(getResources().getColor(R.color.goods_del_bg));
            }

        }

        @Override
        public void onViewAttachedToWindow(BaseRecyclerAdapter.BaseViewHolder holder, View view) {

        }
    }

    public String status(int flag) {
        String name = null;
        switch (flag) {
            case 0:
                name = "未处理";
                break;
            case 1:
                name = "处理中";
                break;
            case 2:
                name = "已完成";
                break;
        }
        return name;
    }

    @OnClick({R.id.p_reset, R.id.p_start, R.id.sku_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.p_reset:
                builder.setMessage("是否重置？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reset();
                    }
                });
                builder.show();
                break;
            case R.id.p_start:
                if (mActivity.isMixedModel) {
                    ((ScanningRFIDForMixFragment) mActivity.fragments.get(1)).clear();
                    mActivity.setCurrentFragment(1);
                } else {
                    if (getBoolean() != 2)
                        mActivity.setCurrentFragment(2);
                    else
                        showDialog("该单不支持RFID收货！");
                }
                break;
            case R.id.sku_start:
                if (mActivity.isMixedModel) {
                    mActivity.setCurrentFragment(3);
                } else {
                    if (getBoolean() != 0)
                        mActivity.setCurrentFragment(4);
                    else
                        showDialog("该单不支持条码收货！");
                }

                break;
        }
    }

    //在正常收货模式下，如果有进入相应的操作界面，需要该单或者箱在数据库里面有相应的明细
    public int getBoolean() {
        int flag = 1;
        if (mActivity.orderId == 0) {
            //获取多个或者一个箱对应操作数据，算出它们的单据数总和
            int epcOperateSum = operateDBOperator.rawQueryForSum(mActivity.getCasesId(), "IN_BOUND_CASE_ID in (" + mActivity.getParameter() + ") AND EPC IS NOT NULL", "QTY", "IN_BOUND_OPERATE");
            if (epcOperateSum == 0) {
                flag = 2;
            } else {
                //获取多个或者一个箱的单据数总和
                int caseSum = operateDBOperator.rawQueryForSum(mActivity.getCasesId(), "_ID in (" + mActivity.getParameter() + ") ", "QTY", " IN_BOUND_CASE ");
                if (epcOperateSum == caseSum) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
        } else {
            //获取单对应的操作数据，算出它们的单据数总和
            InBoundOrder inBoundOrder = orderDBOperator.getItemByID(mActivity.orderId);
            int epcOperateSum = operateDBOperator.rawQueryForSum(new String[]{String.valueOf(mActivity.orderId)}, " IN_BOUND_ORDER_ID = ? AND EPC IS NOT NULL", "QTY", "IN_BOUND_OPERATE");
            if (epcOperateSum == 0) {
                flag = 2;
            } else {
                if (epcOperateSum == inBoundOrder.getQty()) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
        }
        return flag;
    }

    class DialogListAdapter extends BaseListAdapter<InBoundDetail> {

        public DialogListAdapter(Context context, List<InBoundDetail> list) {
            super(context, list);
        }

        @Override
        public View bindView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_dialog_error_list, parent, false);
            }

            TextView index = ViewHolder.get(convertView, R.id.tv_sku_index);
            TextView barcode = ViewHolder.get(convertView, R.id.tv_barcode);
            TextView skuQty = ViewHolder.get(convertView, R.id.tv_sku_qty);
            TextView skuOperateQty = ViewHolder.get(convertView, R.id.tv_sku_operate_qty);

            if (position == 0) {
                index.setText("#");
                barcode.setText("条码");
                skuQty.setText("单据数");
                skuOperateQty.setText("操作数");
            } else {
                InBoundDetail detail = list.get(position);
                index.setText(String.valueOf(position));
                barcode.setText(detail.getBarcode());
                skuQty.setText(String.valueOf(detail.getQty()));
                skuOperateQty.setText(String.valueOf(detail.getOperateQty()));
            }

            return convertView;
        }
    }
}

