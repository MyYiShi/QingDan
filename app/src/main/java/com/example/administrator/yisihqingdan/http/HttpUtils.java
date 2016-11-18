package com.example.administrator.yisihqingdan.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Yishi on 2016/10/28.
 */

public class HttpUtils {
    /**线程池**/
    private ExecutorService executorService;
    private int maxThreadCount = 5;
    private int connectTimeout = 6000;
    private int readTimeout = 6000;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseCallback responseCallback = (ResponseCallback) msg.obj;
            switch (msg.what){
                case 1:
                    responseCallback.getCallback().onResponse(responseCallback.getResult());
                    break;
                case 2:
                    responseCallback.getCallback().onError();
                    break;
            }
        }
    };

    private static HttpUtils instance;
    private HttpUtils(){
        executorService = Executors.newFixedThreadPool(maxThreadCount);
    }

    /**
     * 同步单例模式
     */
    public static HttpUtils getInstance() {
        if (instance == null){
            synchronized (HttpUtils.class){
                if (instance == null){
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    public void execute(Request request, Callback callback){
        HttpRunnable runnable = new HttpRunnable(request,callback);
        executorService.execute(runnable);
    }


    public interface Callback {
        void onResponse(String response);
        void onError();
    }

    public class HttpRunnable implements Runnable{
        private Request request;
        private Callback callback;

        public HttpRunnable(Request request, Callback callback) {
            this.request = request;
            this.callback = callback;
        }

        @Override
        public void run() {
            String strrequest = null;
            try {
                URL url = new URL(request.getUrl());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(readTimeout);
                conn.setConnectTimeout(connectTimeout);
                conn.setRequestMethod(request.getMethod());
                //添加头部数据
                Headers headers1 = request.getHeaders();
                if (headers1 != null){
                    List<String> headers = headers1.getNameAndValues();
                    for (int i = 0; i < headers.size(); i+=2) {
                        conn.addRequestProperty(headers.get(i),headers.get(i+1));
                    }
                }
                //设置请求的主体
                if (request.getRequestBody() != null){
                    conn.setDoOutput(true);//允许像服务器写入数据
                    OutputStream os = conn.getOutputStream();
                    os.write(request.getRequestBody().getBodyBytes());
                }
                //读取服务器的返回
                if (conn.getResponseCode() == 200){
                    InputStream is = conn.getInputStream();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = bf.readLine())!=null){
                        sb.append(line);
                    }
                    strrequest = sb.toString();
                }
                if (callback == null){
                    return;
                }
                ResponseCallback responseCallback = new ResponseCallback(request.getUrl(),callback,strrequest);
                handler.sendMessage(handler.obtainMessage(1,responseCallback));
                return;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (callback == null){
                return;
            }
            ResponseCallback responseCallback = new ResponseCallback(request.getUrl(),callback,strrequest);
            handler.sendMessage(handler.obtainMessage(2,responseCallback));
        }
    }

    public static class ResponseCallback {
        private Object tag;
        private Callback callback;
        private String result;

        public Object getTag() {
            return tag;
        }

        public Callback getCallback() {
            return callback;
        }

        public String getResult() {
            return result;
        }

        public ResponseCallback(Object tag, Callback callback, String result) {
            this.tag = tag;
            this.callback = callback;
            this.result = result;
        }
    }
}
