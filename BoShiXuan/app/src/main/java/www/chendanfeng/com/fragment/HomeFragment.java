package www.chendanfeng.com.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.BannerViewPagerAdapter;
import www.chendanfeng.com.boishixuan.LeaseActivity;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.boishixuan.WithdrawActivity;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class HomeFragment extends BaseFragment{
    public static final int TYPE_LEASE = 1;
    public static final int TYPE_WITHDRAW = 2;
    public static final int TYPE_RECHARGE = 3;
    public static final int TYPE_DEPOSIT = 4;
    public static final int TYPE_ORDER = 5;
    public static final int TYPE_MORE = 6;

    @Bind(R.id.tv_head)
    TextView mHeader;
    //轮播图初始位置
    private int mPosition = 1;
    //当手指触碰到时，设置为false
    private boolean mIsContinue = true;
    private View mView;
    @Bind(R.id.left_dot)
    ImageView mLeftDot;
    @Bind(R.id.center_dot)
    ImageView mCenterDot;
    @Bind(R.id.right_dot)
    ImageView mRightDot;
    @Bind(R.id.vpInfoBanner)
    ViewPager mViewBanner;

    @Bind(R.id.leaseImage)
    ImageView mLeaseImage;
    @Bind(R.id.withdrawImage)
    ImageView mWithdrawImage;
    @Bind(R.id.rechargeImage)
    ImageView mRechargeImage;
    @Bind(R.id.depositImage)
    ImageView mDepositeImage;
    @Bind(R.id.orderImage)
    ImageView mOrderImage;
    @Bind(R.id.moreImage)
    ImageView mMoreImage;

    List<ImageView> mAdvancePicList = new ArrayList<>();
    private ViewPager.LayoutParams mParams;
    private List<ImageView> mDotList = new ArrayList<>();
    private List<Integer> mBannerResList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, this.mView);
        this.mParams = new ViewPager.LayoutParams();
        this.mParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        this.mParams.height = ViewPager.LayoutParams.MATCH_PARENT;
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBannerRes();
        initDot();
        initViewPager();
        initHeader();
        initClickListener();
    }

    private void initHeader() {
        this.mHeader.setVisibility(View.VISIBLE);
        this.mHeader.setText("博时轩品名");
    }
    //初始化轮播图资源
    private void initBannerRes() {
        this.mBannerResList.clear();
        this.mBannerResList.add(R.drawable.home_advance_3);
        this.mBannerResList.add(R.drawable.home_advance_1);
        this.mBannerResList.add(R.drawable.home_advance_2);
        this.mBannerResList.add(R.drawable.home_advance_3);
        this.mBannerResList.add(R.drawable.home_advance_1);
    }
    //初始化广告轮播图
    private void initViewPager() {
        this.mAdvancePicList.clear();

        for(int i=0;i<this.mBannerResList.size();i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(this.mBannerResList.get(i));
            image.setLayoutParams(mParams);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            mAdvancePicList.add(image);
        }
        BannerViewPagerAdapter adapter = new BannerViewPagerAdapter(mAdvancePicList);
        mViewBanner.setAdapter(adapter);
        mViewBanner.setCurrentItem(1);
        mViewBanner.addOnPageChangeListener(new GuidePageChangeListener());
        mViewBanner.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        HomeFragment.this.mIsContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        HomeFragment.this.mIsContinue = true;
                        break;
                    default:
                        HomeFragment.this.mIsContinue = true;
                        break;
                }
                return false;
            }
        });
        if (!this.mThread.isAlive()){
            this.mThread.start();
        }


    }
    //初始化轮播图上面的三个点
    private void initDot() {
        this.mDotList.clear();
        this.mLeftDot.setBackgroundResource(R.drawable.dot_select);
        this.mDotList.add(this.mLeftDot);
        this.mCenterDot.setBackgroundResource(R.drawable.dot_normal);
        this.mDotList.add(this.mCenterDot);
        this.mRightDot.setBackgroundResource(R.drawable.dot_normal);
        this.mDotList.add(this.mRightDot);
    }
    private class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(final int arg0) {

            mPosition = arg0;
            if (arg0 == 0) {
                mPosition = mAdvancePicList.size() - 2;
            }
            if (arg0 == mAdvancePicList.size() - 1) {
                mPosition = 1;
            }
            changeDot(mPosition);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (arg0 == 0) {
                        mViewBanner.setCurrentItem(mAdvancePicList.size() - 2, false);
                    }
                    if (arg0 == mAdvancePicList.size() - 1) {
                        mViewBanner.setCurrentItem(1, false);
                    }
                }
            }, 300);
        }

    }
    //初始化下边六个按钮的点击事件
    private void initClickListener() {
        this.mLeaseImage.setOnClickListener(new MyOnClickListener(TYPE_LEASE));
        this.mWithdrawImage.setOnClickListener(new MyOnClickListener(TYPE_WITHDRAW));
        this.mRechargeImage.setOnClickListener(new MyOnClickListener(TYPE_RECHARGE));
        this.mDepositeImage.setOnClickListener(new MyOnClickListener(TYPE_DEPOSIT));
        this.mOrderImage.setOnClickListener(new MyOnClickListener(TYPE_ORDER));
        this.mMoreImage.setOnClickListener(new MyOnClickListener(TYPE_MORE));
    }
    private Thread mThread = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                if (HomeFragment.this.mIsContinue) {
                    viewHandler.sendEmptyMessage(mPosition);
                    changePosition();
                }
            }
        }

    });

    private Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            mViewBanner.setCurrentItem(msg.what, true);
            changeDot(msg.what);
            super.handleMessage(msg);
        }

    };


    private void changePosition() {

        if (mPosition == mAdvancePicList.size()) {
            mPosition = 1;
        } else {
            mPosition++;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }


    public void changeDot(int position) {
        for (int i = 0; i < mDotList.size(); i++) {
            if (position - 1 == i) {
                mDotList.get(i).setBackgroundResource(R.drawable.dot_select);
            } else {
                mDotList.get(i).setBackgroundResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(this.mThread != null) {
            this.mThread.interrupt();
        }
    }
    class MyOnClickListener implements View.OnClickListener {
        private int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (this.mType) {
                case TYPE_LEASE:
                    intent = new Intent(getActivity(), LeaseActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_WITHDRAW:
                    intent = new Intent(getActivity(), WithdrawActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_RECHARGE:
                    break;
                case TYPE_DEPOSIT:
                    break;
                case TYPE_ORDER:
                    break;
                case TYPE_MORE:
                    break;
            }
        }
    }
}
