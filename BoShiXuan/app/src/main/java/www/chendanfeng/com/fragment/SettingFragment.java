package www.chendanfeng.com.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import www.chendanfeng.com.boishixuan.UsehelpActivity;
import www.chendanfeng.com.view.CustomDialog;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class SettingFragment extends BaseFragment{
    private View mView;
    public static final int TYPE_ABOUT = 1;
    public static final int TYPE_SHARE = 2;
    public static final int TYPE_PHONE = 3;
    public static final int TYPE_LOGOUT = 4;
    public static final int TYPE_USEHELP = 5;
    public static final int TYPE_CLEAN = 6;
    @Bind(R.id.aboutLayout)
    RelativeLayout aboutus;
    @Bind(R.id.phoneLayout)
    RelativeLayout phone;
    @Bind(R.id.phone)
    TextView call;
    @Bind(R.id.logout)
    ImageView logoutButton;
    @Bind(R.id.usehelpLayout)
    RelativeLayout usehelp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, this.mView);
        aboutus.setOnClickListener(new MyOnClickListener(TYPE_ABOUT));
        phone.setOnClickListener(new MyOnClickListener(TYPE_PHONE));
        logoutButton.setOnClickListener(new MyOnClickListener(TYPE_LOGOUT));
        usehelp.setOnClickListener(new MyOnClickListener(TYPE_USEHELP));
        return this.mView;
    }

    class MyOnClickListener implements  View.OnClickListener {
        public int mType;
        public MyOnClickListener(int type) {
            this.mType = type;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (this.mType) {
                case TYPE_ABOUT:
                    intent = new Intent(getActivity(),AboutActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_SHARE:
                    break;
                case TYPE_PHONE:
                    final String callPhone =  call.getText().toString();
                    StringBuffer message = new StringBuffer();
                    message.append("确定要拨打").append(callPhone).append("么？");
                    CustomDialog.Builder builder=new CustomDialog.Builder(getActivity());
                    builder.setTitle("温馨提示");
                    builder.setMessage(message.toString());
                    builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                       }
                   });
                    builder.setPositiveButton("拨打",new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                           Intent intent = new Intent();
                           intent.setAction(Intent.ACTION_CALL);
                           intent.setData(Uri.parse("tel:" + callPhone));
                           //开启系统拨号器
                           startActivity(intent);
                       }
                   });
                    builder.create().show();
                    break;
                case TYPE_LOGOUT:
                    getActivity().finish();
                    break;
                case TYPE_USEHELP:
                    intent = new Intent(getActivity(),UsehelpActivity.class);
                    startActivity(intent);
                    break;
                case TYPE_CLEAN:
                    break;
            }
        }
    }
}
