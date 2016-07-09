package www.chendanfeng.com.network;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import www.chendanfeng.com.config.Config;
import www.chendanfeng.com.util.CommonUtil;
import www.chendanfeng.com.util.LogUtil;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class WebServiceApi {
    public static void sendVerifyCode(JSONObject obj,Context context) {
//        String path = CommonUtil.getHttpRequestPath(Config.BSX_VERIFY_CODE);

//        LogUtil.i("WebServiceApi","path = " + path);
//        RequestParams params = new RequestParams();
//        params.add("REQ_MESSAGE",obj.toJSONString());
//        LogUtil.i("REQ_MESSAGE","params = " + params);
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.setConnectTimeout(5000);
//        HttpEntity httpEntity = null;
//
//        client.post(context,path,params,new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                LogUtil.i(this,"verifyCode onSuccess responseBody：" + responseBody);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                LogUtil.i(this,"verifyCode onFailure responseBody：" + headers);
//            }
//        });
    }

}
