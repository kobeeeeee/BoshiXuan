package www.chendanfeng.com.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.HashMap;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class WebServiceApi {
    public static void sendVerifyCode(HashMap<String,Object> map) {
        String path = CommonUtil.getHttpRequestPath(map,Config.BSX_VERIFY_CODE);
        LogUtil.i("WebServiceApi","path = " + path);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(path, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtil.i(this,"verifyCode onSuccess responseBody：" + responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i(this,"verifyCode onFailure responseBody：" + responseBody);
            }
        });
    }

}
