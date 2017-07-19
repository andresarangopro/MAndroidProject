package com.lide.app.ui.outbound.createOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.persistence.widget.ResourceUtil;
import com.lide.app.persistence.widget.recycler.AbsAdapterItem;
import com.lide.app.persistence.widget.recycler.BaseRecyclerAdapter;
import com.lide.app.persistence.widget.recycler.GridRecyclerView;
import com.lide.app.ui.FragmentBase;
import com.lubin.bean.OutBoundOperate;
import com.lubin.bean.OutBoundOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangjianxionh on 2016/10/8.
 */

public class ShowTagListFragment extends FragmentBase {
    @BindView(R.id.results_layout)
    FrameLayout resultsLayout;

    private GridRecyclerView resultsListView;
    BaseRecyclerAdapter resultsLayoutAdapter;

    private View mView;

    ScanOBOrderByCreateActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_results, container, false);
        ButterKnife.bind(this, mView);
        init();
        mActivity = (ScanOBOrderByCreateActivity) getActivity();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void init() {

        resultsListView = new GridRecyclerView(getActivity());
        ((ViewGroup) resultsLayout).addView(resultsListView, 0, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        resultsListView.setSpanCount(1);
        resultsListView.setItemMargin(0);
        resultsLayoutAdapter = new BaseRecyclerAdapter();
        resultsListView.setAdapter(resultsLayoutAdapter);
        resultsListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    public void initData() {
        OutBoundOrder outBoundOrder = OutboundTransaction.getOrderDBOperator().getItemByID(mActivity.orderId);
        List<OutBoundOperate> operates = outBoundOrder.getOperates();
        showScannResultsData(operates);

    }

    public void showScannResultsData(List<OutBoundOperate> outBoundOperates) {

        for (int i = 0; i < outBoundOperates.size(); i++) {
            if (outBoundOperates.get(i).getTagValue() != null) {
                resultsLayoutAdapter.addItem(new ResultsListItem(outBoundOperates.get(i)));
            }
        }
    }


    public class ResultsListItem extends AbsAdapterItem {
        OutBoundOperate operate;

        public ResultsListItem(OutBoundOperate operate) {
            this.operate = operate;
        }

        @Override
        public View onCreateView(ViewGroup parent, int position) {
            int layout = ResourceUtil.getLayoutId(getActivity(), "scann_results_tablerow_layout");
            final View view = View.inflate(getActivity(), layout, null);

            return view;
        }

        @Override
        public void onBindView(BaseRecyclerAdapter.BaseViewHolder holder, View view, int position) {
            int id = ResourceUtil.getId(getActivity(), "table_results");
            View tableResults = view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "view_bg");
            View viewBg = view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "results_id");
            TextView resultsId = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_state");
            TextView resultsState = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_sku");
            TextView resultsSku = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_epc");
            TextView resultsEpc = (TextView) view.findViewById(id);

            resultsId.setText(String.valueOf(position + 1));
            if (operate.getOperateQty() == 1) {
                resultsState.setText("成功");
                tableResults.setBackgroundColor(getResources().getColor(R.color.success_text));
                viewBg.setBackgroundColor(getResources().getColor(R.color.white));
            } else
                resultsState.setText("");

            resultsSku.setText(operate.getOutBoundDetail().getBarcode());

            resultsEpc.setText(operate.getTagValue());
        }

        @Override
        public void onViewAttachedToWindow(BaseRecyclerAdapter.BaseViewHolder holder, View view) {

        }
    }

}
