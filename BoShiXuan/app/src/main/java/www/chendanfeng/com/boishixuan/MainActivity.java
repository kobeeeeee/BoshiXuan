package www.chendanfeng.com.boishixuan;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import www.chendanfeng.com.fragment.HomeFragment;
import www.chendanfeng.com.fragment.NewsFragment;
import www.chendanfeng.com.fragment.SettingFragment;
import www.chendanfeng.com.fragment.WalletFragment;
import www.chendanfeng.com.util.LogUtil;

public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private SettingFragment mSettingFragment;
    private WalletFragment mWalletFragment;
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

    }
    private void showFragments() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

    }
}
