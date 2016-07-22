package www.chendanfeng.com.boishixuan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.adapter.MainTabFragmentAdapter;
import www.chendanfeng.com.fragment.HomeFragment;
import www.chendanfeng.com.fragment.NewsFragment;
import www.chendanfeng.com.fragment.SettingFragment;
import www.chendanfeng.com.fragment.WalletFragment;
import www.chendanfeng.com.util.LogUtil;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tab_group)
    RadioGroup mainGroup;
    @Bind(R.id.tab_home)
    RadioButton homeTab;
    @Bind(R.id.tab_news)
    RadioButton newsTab;
    @Bind(R.id.tab_wallet)
    RadioButton walletTab;
    @Bind(R.id.tab_setting)
    RadioButton settingTab;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private SettingFragment mSettingFragment;
    private WalletFragment mWalletFragment;
    MainTabFragmentAdapter mTabFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        initFragments();
    }
    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mSettingFragment = new SettingFragment();
        mWalletFragment = new WalletFragment();

        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mNewsFragment);
        mFragmentList.add(mWalletFragment);
        mFragmentList.add(mSettingFragment);
        homeTab.setChecked(true);
        homeTab.setTextColor(getResources().getColor(R.color.coffee));
        mTabFragmentAdapter = new MainTabFragmentAdapter(this, mFragmentList, R.id.main_tab, mainGroup,1);

    }
}
