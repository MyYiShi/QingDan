package com.example.administrator.yisihqingdan.http;

/**
 * Created by Yishi on 2016/10/27.
 */

public class Request {
    private String url;
    private String method;
    private Headers headers;
    private RequestBody requestBody;

    private Request(Builder builder){
        this.url = builder.url;
        this.method = builder.method;
        if ( builder.headersbuilder!=null){
            this.headers = builder.headersbuilder.build();
        }
        this.requestBody = builder.requestBody;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Headers getHeaders() {
        return headers;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public static class Builder{
        private String url;
        private String method;
        private Headers.Builder headersbuilder;
        private RequestBody requestBody;

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder get(){
            this.method = "GET";
            return this;
        }

        public Builder post(RequestBody requestBody){
            this.method = "POST";
            this.requestBody = requestBody;
            addHeader("Content-type",requestBody.getContentType());
            return this;
        }

        public Request build(){
            return new Request(this);
        }

        /**
         * 添加头部数据
         */
        public Builder addHeader(String name ,String value){
            if (headersbuilder == null){
                headersbuilder = new Headers.Builder();
            }
            headersbuilder.addHead(name,value);
            return this;
        }

        public Builder addHeaders(Headers headers){
            if (headersbuilder == null){
                headersbuilder = new Headers.Builder();
            }
            headersbuilder.addHeads(headers);
            return this;
        }
    }
}
