package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.RegularListAdapter;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.ModifyPswResponse;
import www.chendanfeng.com.network.model.ProductResponse;
import www.chendanfeng.com.network.model.RegularDetailModel;
import www.chendanfeng.com.network.model.RegularModel;
import www.chendanfeng.com.network.model.RegularResponse;
import www.chendanfeng.com.util.LogUtil;
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
    private List<RegularDetailModel> mRegularDetailModelList = new ArrayList<>();
    private NetWorkCallBack mNetWorkCallBack;
    public int mPageNum = 1;
    public int mPageSize = 10;
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
        getData();
    }
    private void initRecyclerView() {


        this.mRegularListAdapter = new RegularListAdapter(getActivity(),this.mRegularDetailModelList);
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
    private void getData() {
        this.mNetWorkCallBack = new NetWorkCallBack();
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("page_size",String.valueOf(this.mPageSize));
        map.put("page_num",String.valueOf(this.mPageNum));
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_FINANCE_PRODUCT,map,RegularFragment.this.mNetWorkCallBack, RegularResponse.class);

    }
    private class NetWorkCallBack implements RequestListener {

        @Override
        public void onBegin() {

        }

        @Override
        public void onResponse(Object object) {
            if(object == null) {
                return;
            }
            if(object instanceof RegularResponse) {
                RegularResponse regularResponse = (RegularResponse)object;
                LogUtil.i(this,"regularResponse = " + regularResponse);
                RegularModel regularModel = regularResponse.financeproduct_list;
                List<RegularDetailModel> regularDetailModelList = regularModel.data_list;
                RegularFragment.this.mRegularListAdapter.setList(regularDetailModelList);
                RegularFragment.this.mRegularListAdapter.notifyDataSetChanged();

            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }

}
