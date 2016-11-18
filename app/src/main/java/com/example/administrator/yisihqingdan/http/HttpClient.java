package com.example.administrator.yisihqingdan.http;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

/**
 * Created by lenovo on 2016-10-30.
 */

public class HttpClient {
    private static String authorization;
    private static long validTime;

    public static void excute(final Request.Builder builder,final HttpUtils.Callback callback ){
        if (!checkAuthorization()){
            String json = "{\"client_id\":\"herEv4O9tg4PuviM\",\"client_secret\":\"v20ulmkZP5ykQMn9uUwNbyEiuTkzFfPn\",\"grant_type\":\"client_credentials\"}";
            RequestBody body = new StringBody(json);

            Request requestToken = new Request.Builder()
                .url("http://api.eqingdan.com/v1/oauth2/access-token")
                .post(body)
                .build();

            Log.i("msg",requestToken.toString());

            HttpUtils.getInstance().execute(requestToken, new HttpUtils.Callback() {
                @Override
                public void onResponse(String response) {

                    String str = UnicodeParser.decodeUnicode(response);
                    Log.d("msg2", str);

                    AuthoriaztionObj authoriaztionObj = JSON.parseObject(response,AuthoriaztionObj.class);
                    AuthoriaztionObj.DataBean data = authoriaztionObj.getData();
                    authorization = data.getToken_type()+" " + data.getAccess_token();

                    Log.d("HttpClient", authorization);

                    validTime = System.currentTimeMillis()+data.getExpires_in()*1000;
                    builder.addHeader("authorization",authorization);
                    HttpUtils.getInstance().execute(builder.build(),callback);
                }

                @Override
                public void onError() {

                }
            });
        }else{
            builder.addHeader("authorization",authorization);
            HttpUtils.getInstance().execute(builder.build(),callback);
        }
    }

    /**
     * 检测authorization是否有效
     * @return
     */
    private static boolean checkAuthorization(){
        if (TextUtils.isEmpty(authorization) || isTokenInvalid()){
            return false;
        }
        return true;
    }

    /**
     * 判断authorization是否已失效
     * @return
     */
    private static boolean isTokenInvalid() {
        return System.currentTimeMillis() > validTime;
    }
}
