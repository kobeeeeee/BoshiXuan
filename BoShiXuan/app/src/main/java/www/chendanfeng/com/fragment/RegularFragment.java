package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.RegularListAdapter;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.network.model.RegularResponse;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class RegularFragment extends BaseFragment{
    @Bind(R.id.regularRecyclerView)
    XRecyclerView mRegularRecyclerView;
    private RegularListAdapter mRegularListAdapter;
    private View mView;
    private List<RegularResponse> mRegularResponseList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_regular, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }
    private void initRecyclerView() {
        this.mRegularResponseList = new ArrayList<>();
        RegularResponse regularResponse = new RegularResponse();
        regularResponse.regularProductDay= "360天";
        regularResponse.regularProductIncome = "18.00%";
        regularResponse.regularProductMin = "1000元起投";
        regularResponse.regularProductName = "360天长期理财";
        this.mRegularResponseList.add(regularResponse);

        regularResponse = new RegularResponse();
        regularResponse.regularProductDay= "180天";
        regularResponse.regularProductIncome = "15.00%";
        regularResponse.regularProductMin = "1000元起投";
        regularResponse.regularProductName = "180天中期理财";
        this.mRegularResponseList.add(regularResponse);

        regularResponse = new RegularResponse();
        regularResponse.regularProductDay= "90天";
        regularResponse.regularProductIncome = "12.00%";
        regularResponse.regularProductMin = "100元起投";
        regularResponse.regularProductName = "90天长期理财";
        this.mRegularResponseList.add(regularResponse);

        regularResponse = new RegularResponse();
        regularResponse.regularProductDay= "30天";
        regularResponse.regularProductIncome = "10.00%";
        regularResponse.regularProductMin = "100元起投";
        regularResponse.regularProductName = "30天长期理财";
        this.mRegularResponseList.add(regularResponse);


        this.mRegularListAdapter = new RegularListAdapter(getActivity(),this.mRegularResponseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mRegularRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRegularRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mRegularRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mRegularRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mRegularRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        RegularFragment.this.mRegularRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        RegularFragment.this.mRegularRecyclerView.loadMoreComplete();
                    }
                },3000);

            }
        });
        this.mRegularRecyclerView.setAdapter(this.mRegularListAdapter);
    }

}
