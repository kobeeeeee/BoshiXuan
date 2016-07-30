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
import www.chendanfeng.com.adapter.NewsListAdapter;
import www.chendanfeng.com.bean.UserInfoBean;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.model.MessageListResponse;
import www.chendanfeng.com.network.model.NewsDetailModel;
import www.chendanfeng.com.network.model.NewsModel;
import www.chendanfeng.com.network.model.NewsResponse;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;
import www.chendanfeng.com.xrecyclerview.ProgressStyle;
import www.chendanfeng.com.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public class SystemNewsFragment extends BaseFragment{
    @Bind(R.id.systemNewsRecyclerView)
    XRecyclerView mSystemNewsRecyclerView;
    private NewsListAdapter mNewsListAdapter;
    private View mView;
    private List<NewsDetailModel> mNewsDetailModelList;
    private NetWorkCallBack mNetWorkCallBack = new NetWorkCallBack();
    private int mPageNum=1;
    private int mPageSize = 10;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int mCurrentListSize = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_system_news, container, false);
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
        this.mNewsDetailModelList = new ArrayList<>();

        this.mNewsListAdapter = new NewsListAdapter(getActivity(),this.mNewsDetailModelList,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mSystemNewsRecyclerView.setLayoutManager(linearLayoutManager);
        this.mSystemNewsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        this.mSystemNewsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        this.mSystemNewsRecyclerView.setArrowImageView(R.drawable.icon_font_down_grey);


        this.mSystemNewsRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                getData();
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                SystemNewsFragment.this.mPageSize = SystemNewsFragment.this.mPageSize + 10;
                getData();
            }
        });
        this.mSystemNewsRecyclerView.setAdapter(this.mNewsListAdapter);
    }
    private void getData() {
        UserInfoBean userInfoBean = UserInfoBean.getUserInfoBeanInstance();
        String userId = userInfoBean.getCustId();
        String userPhone = userInfoBean.getCustMobile();
        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("page_num",String.valueOf(mPageNum));
        map.put("page_size",String.valueOf(mPageSize));
        map.put("msg_type","0");
        map.put("user_id",userId);
        map.put("user_phone",userPhone);
        RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_MESSAGE,map,SystemNewsFragment.this.mNetWorkCallBack, NewsResponse.class);

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
            if(object instanceof NewsResponse) {
                NewsResponse newsResponse = (NewsResponse)object;
                LogUtil.i(this,"newsResponse = " + newsResponse);
                NewsModel newsModel = newsResponse.msg_list;
                List<NewsDetailModel> newsDetailModelList = newsModel.data_list;
                if(newsDetailModelList.size() == 0) {
                    CommonUtil.showToast("暂无消息",getActivity());
                }
                SystemNewsFragment.this.mNewsListAdapter.setList(newsDetailModelList);
                SystemNewsFragment.this.mNewsListAdapter.notifyDataSetChanged();

                if(isRefresh) {
                    SystemNewsFragment.this.mSystemNewsRecyclerView.refreshComplete();
                    isRefresh = false;
                }
                if(isLoadMore) {
                    if(SystemNewsFragment.this.mCurrentListSize == newsDetailModelList.size()) {
                        CommonUtil.showToast("亲，就只有这么多了",getActivity());
                    }
                    SystemNewsFragment.this.mSystemNewsRecyclerView.loadMoreComplete();
                    isLoadMore = false;
                }
                SystemNewsFragment.this.mCurrentListSize = newsDetailModelList.size();
            }

        }

        @Override
        public void onFailure(Object message) {
            String msg = (String) message;
            CommonUtil.showToast(msg,getActivity());
            if(isRefresh) {
                SystemNewsFragment.this.mSystemNewsRecyclerView.refreshComplete();
                isRefresh = false;
            }
            if(isLoadMore) {
                SystemNewsFragment.this.mSystemNewsRecyclerView.loadMoreComplete();
                isLoadMore = false;
            }
        }
    }
}
