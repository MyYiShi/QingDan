package com.example.administrator.yisihqingdan.http;

/**
 * Created by Yishi on 2016/10/28.
 */

public class StringBody extends RequestBody{
    private String body;

    public StringBody(String body) {
        super(jsonContentType);
        this.body = body;
    }

    @Override
    public byte[] getBodyBytes() {
        return body.getBytes();
    }
}
