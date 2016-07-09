package www.chendanfeng.com.boishixuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.network.RegisterResponse;
import www.chendanfeng.com.network.RequestListener;
import www.chendanfeng.com.network.RequestManager;
import www.chendanfeng.com.network.WebServiceApi;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by yangln on 2016/7/3.
 */
public class RegisterActivity extends BaseActivity {
    public static final int TYPE_SEND_VERIFY_CODE = 1;
    public static final int TYPE_REGISTER = 2;
    @Bind(R.id.inputPhone)
    EditText phoneEditText;
    @Bind(R.id.inputPassword)
    EditText passwordEditText;
    @Bind(R.id.confirmPassword)
    EditText confirmPasswordEditText;
    @Bind(R.id.inputCode)
    EditText CodeEditText;
    @Bind(R.id.buttonCode)
    ImageView codeButton;
    @Bind(R.id.checkagree)
    CheckBox checkagreeBox;
    @Bind(R.id.register)
    ImageView registerButton;
    public NetWorkCallBack mNetWorkCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        codeButton.setOnClickListener(new MyOnClickListener(TYPE_SEND_VERIFY_CODE));
        this.mNetWorkCallBack = new NetWorkCallBack();
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
                case TYPE_SEND_VERIFY_CODE:
                    Map<String,Object> map = new HashMap<>();
                    map.put("user_phone","18757118127");
                    RequestManager.getInstance().post(Config.URL + Config.SLASH, Config.BSX_VERIFY_CODE,map,RegisterActivity.this.mNetWorkCallBack, RegisterResponse.class);
                    break;
                case TYPE_REGISTER:
                    break;
            }
        }
    }
    private class NetWorkCallBack implements RequestListener {

        @Override
        public void onBegin() {

        }

        @Override
        public void onResponse(Object object) {
            if (object != null && object instanceof RegisterResponse) {
                RegisterResponse registerResponse = (RegisterResponse)object;
                LogUtil.i(this,"registerResponse = " + registerResponse);
            }
        }

        @Override
        public void onFailure(Object message) {

        }
    }
}

