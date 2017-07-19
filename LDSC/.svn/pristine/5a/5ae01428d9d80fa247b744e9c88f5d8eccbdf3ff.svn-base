package com.lide.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.ConfirmModel;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.widget.view.SwipeItemLayout;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.InBoundCaseDao;
import com.lubin.dao.InBoundDetailDao;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2017/2/21.
 */

public class ControlIBOrderAdapter extends BaseListAdapter<InBoundOrder> {
    private final FragmentBase fragment;
    private ConfirmModel confirmModel;
    private View view01, view02;
    private TextView orderAudit;
    private TextView orderDel;
    private TextView order_name;
    private TextView order_address;
    private TextView order_audit_state;
    private TextView order_date;
    private TextView text_state;
    private DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator;
    private DBOperator<InBoundCaseDao, InBoundCase> caseDBOperator;
    private DBOperator<InBoundDetailDao, InBoundDetail> detailDBOperator;
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;

    public ControlIBOrderAdapter(Context context, List<InBoundOrder> list, FragmentBase fragment) {
        super(context, list);
        this.fragment = fragment;
        orderDBOperator = InboundTransaction.getOrderDBOperator();
        caseDBOperator = InboundTransaction.getCaseDBOperator();
        detailDBOperator = InboundTransaction.getDetailDBOperator();
        operateDBOperator = InboundTransaction.getOperateDBOperator();
        confirmModel = new ConfirmModel();
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order_layout, parent, false);
        }
        final InBoundOrder data = getList().get(position);

        view01 = LayoutInflater.from(mContext).inflate(R.layout.item_order_layout, null);
        order_name = (TextView) view01.findViewById(R.id.order_name);
        order_address = (TextView) view01.findViewById(R.id.order_address);
        order_audit_state = (TextView) view01.findViewById(R.id.order_audit_state);
        order_date = (TextView) view01.findViewById(R.id.order_date);
        text_state = (TextView) view01.findViewById(R.id.text_state);

        order_name.setText(data.getCode());
        order_address.setText(data.getFormWarehouseName() + "," + data.getQty() + "/" + data.getOperateQty() + "件");
        order_audit_state.setText(status(data.getStatus()));
        if (data.getDate() != null && data.getDate().contains(".")) {
            String result = data.getDate().substring(0, data.getDate().lastIndexOf("."));
            order_date.setText(result);
        } else {
            order_date.setText("");
        }

        view02 = LayoutInflater.from(mContext).inflate(R.layout.item_order_item, null);
        orderAudit = (TextView) view02.findViewById(R.id.order_audit);
        orderAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.getCurrentUser() != null) {
                    showCheckDialog("审核", data);
                } else {
                    //未登录
                    Intent login = new Intent(mContext, LoginActivity.class);
                    login.putExtra("isAtNet", true);
                    fragment.startActivityForResult(login, Configs.LOGIN);
                }
            }
        });
        orderDel = (TextView) view02.findViewById(R.id.order_del);
        orderDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCheckDialog("删除", data);
            }
        });

        convertView = new SwipeItemLayout(view01, view02, null, null);
        return convertView;
    }

    public String status(int flag) {
        String name = null;
        switch (flag) {
            case 0:
                name = "未处理";
                text_state.setVisibility(View.INVISIBLE);
                break;
            case 1:
                name = "处理中";
                text_state.setVisibility(View.INVISIBLE);
                break;
            case 2:
                name = "已完成";
                order_audit_state.setTextColor(mContext.getResources().getColor(R.color.goods_delivery_title_bg));
                text_state.setVisibility(View.VISIBLE);
                text_state.setText("订单已完成，建议7天内向左滑动删除该单据!");
                break;
        }
        return name;
    }

    void showCheckDialog(final String state, final InBoundOrder data) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("是否" + state + ":");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (state.equals("审核"))
                    confirm(data.getId());
                else {
                    delete(data.getOrderId());
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void delete(String orderId) {
        List<InBoundOrder> list = orderDBOperator.getItemsByParameters(null
                , InBoundOrderDao.Properties.OrderId.eq(orderId));
        if (list.size() == 0) return;
        InBoundOrder inBoundOrder = list.get(0);
        List<InBoundCase> cases = inBoundOrder.getCases();
        List<InBoundDetail> details = inBoundOrder.getDetails();
        List<InBoundOperate> operates = inBoundOrder.getOperates();
        caseDBOperator.deleteDatas(cases);
        detailDBOperator.deleteDatas(details);
        operateDBOperator.deleteDatas(operates);
        inBoundOrder.delete();
        fragment.showDialog("刪除成功");
    }

    private void confirm(final Long orderId) {
        InBoundOrder inBoundOrder = orderDBOperator.getItemByID(orderId);
        List<InBoundOperate> list = operateDBOperator.getItemsByParameters(null
                , InBoundOperateDao.Properties.InBoundOrderId.eq(orderId)
                , InBoundOperateDao.Properties.OperateQty.notEq(0)
                , InBoundOperateDao.Properties.IsUpload.eq(false));
        if (list.size() > 0) {
            fragment.showDialog("还有未上传记录，请先上传");
            return;
        }
        try {
            confirmModel.confirmInboundOrder(inBoundOrder.getOrderId(), new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        InBoundOrder inBoundOrder = orderDBOperator.getItemByID(orderId);
                        inBoundOrder.setStatus(2);
                        inBoundOrder.update();
                        for (InBoundCase inBoundCase : inBoundOrder.getCases()) {
                            inBoundCase.setStatus(2);
                            inBoundCase.update();
                        }
                        fragment.showDialog("审核成功");
                    } else {
                        fragment.showDialog(map.get("result"));
                    }
                }
            });
        } catch (Exception e) {
            fragment.showDialog(e.getMessage());
        }
    }
}
