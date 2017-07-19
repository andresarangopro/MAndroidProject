package com.lide.app.ui.inbound.LS;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.persistence.widget.ResourceUtil;
import com.lide.app.persistence.widget.recycler.AbsAdapterItem;
import com.lide.app.persistence.widget.recycler.BaseRecyclerAdapter;
import com.lide.app.persistence.widget.recycler.GridRecyclerView;
import com.lide.app.ui.FragmentBase;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lubin on 2016/12/7.
 */

public class ErrorResultsFragment extends FragmentBase {

    @BindView(R.id.results_layout)
    FrameLayout resultsLayout;
    private View mView;
    private BaseRecyclerAdapter resultsLayoutAdapter;
    private GridRecyclerView resultsListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_results, container, false);
        ButterKnife.bind(this, mView);
        init();
        return mView;
    }

    public void init() {
        resultsListView = new GridRecyclerView(getActivity());
        ((ViewGroup) resultsLayout).addView(resultsListView, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
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

    //展示从ScanningRFIDForMixFragment传过来的数据
    public void showErrorResultsData(List<Map> datas) {
        resultsLayoutAdapter.clear();
        Collections.sort(datas, new Comparator<Map>() {
            @Override
            public int compare(Map lhs, Map rhs) {
                if (rhs.get("status").equals("正常") && lhs.get("status").equals("正常")) {
                    return 0;
                }
                if (rhs.get("status").equals("正常") && !lhs.get("status").equals("正常")) {
                    return -1;
                }
                if (!rhs.get("status").equals("正常") && lhs.get("status").equals("正常")) {
                    return 1;
                }
                return 0;
            }
        });
        for (Map data : datas) {
            resultsLayoutAdapter.addItem(new ResultsListItem(data));
        }
    }

    public class ResultsListItem extends AbsAdapterItem {

        Map data;

        public ResultsListItem(Map data) {
            this.data = data;
        }

        @Override
        public View onCreateView(ViewGroup parent, int position) {
            int layout = ResourceUtil.getLayoutId(getActivity(), "scann_results_tablerow_layout");
            final View view = View.inflate(getActivity(), layout, null);
            return view;
        }

        @Override
        public void onBindView(BaseRecyclerAdapter.BaseViewHolder holder, View view, int position) {
            int id;
            id = ResourceUtil.getId(getActivity(), "results_id");
            TextView resultsId = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_state");
            TextView resultsState = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_sku");
            TextView resultsSku = (TextView) view.findViewById(id);
            id = ResourceUtil.getId(getActivity(), "results_epc");
            TextView resultsEpc = (TextView) view.findViewById(id);

            resultsId.setText(String.valueOf(position + 1));

            resultsState.setText(data.get("status").toString());

            if (data.get("barcode") != null) {
                resultsSku.setText(data.get("barcode").toString());
            } else {
                resultsSku.setText("");
            }

            resultsEpc.setText(data.get("epc").toString());

            if (data.get("status").toString().equals("正常")) {
                resultsId.setTextColor(Color.BLACK);
                resultsEpc.setTextColor(Color.BLACK);
                resultsSku.setTextColor(Color.BLACK);
                resultsState.setTextColor(Color.BLACK);
            } else {
                resultsId.setTextColor(Color.RED);
                resultsEpc.setTextColor(Color.RED);
                resultsSku.setTextColor(Color.RED);
                resultsState.setTextColor(Color.RED);
            }
        }

        @Override
        public void onViewAttachedToWindow(BaseRecyclerAdapter.BaseViewHolder holder, View view) {

        }
    }
}
