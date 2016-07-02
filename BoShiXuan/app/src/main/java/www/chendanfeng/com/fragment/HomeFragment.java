package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class HomeFragment extends BaseFragment{
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, this.mView);
        return this.mView;
    }
}
