package www.chendanfeng.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class BaseFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(getActivity(),"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(getActivity(),"onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(getActivity(),"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(getActivity(),"onResume");

    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(getActivity(),"onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(getActivity(),"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(getActivity(),"onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(getActivity(),"onDestroy");
    }
}
