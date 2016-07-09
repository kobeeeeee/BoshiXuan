package www.chendanfeng.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.boishixuan.AboutActivity;
import www.chendanfeng.com.boishixuan.LoginActivity;
import www.chendanfeng.com.boishixuan.MainActivity;
import www.chendanfeng.com.boishixuan.PasswordActivity;
import www.chendanfeng.com.boishixuan.R;
import www.chendanfeng.com.boishixuan.RegisterActivity;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class SettingFragment extends BaseFragment{
    private View mView;
    public static final int TYPE_ABOUT = 1;
    @Bind(R.id.aboutus)
    TextView aboutusText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, this.mView);
        aboutusText.setOnClickListener(new MyOnClickListener(TYPE_ABOUT));
        return this.mView;
    }

    class MyOnClickListener implements  View.OnClickListener {
        public int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (this.mType) {
                case TYPE_ABOUT:
                    //TODO 测试跳转主页面代码
                    intent = new Intent(getActivity(),AboutActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
