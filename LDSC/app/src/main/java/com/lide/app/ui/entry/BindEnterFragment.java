package com.lide.app.ui.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lide.app.R;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.binding.OneToManyBindingActivity;
import com.lide.app.ui.binding.OneToOneBindingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/11/24.
 */

public class BindEnterFragment extends FragmentBase {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bind_enter, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }


    @OnClick({R.id.bind_one_to_one, R.id.bind_one_to_many})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind_one_to_one:
                startAnimActivity(new Intent(getActivity(), OneToOneBindingActivity.class));
                break;
            case R.id.bind_one_to_many:
                startAnimActivity(new Intent(getActivity(), OneToManyBindingActivity.class));
                break;
        }
    }
}
