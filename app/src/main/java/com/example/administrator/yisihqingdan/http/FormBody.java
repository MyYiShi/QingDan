package com.example.administrator.yisihqingdan.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yishi on 2016/10/28.
 */

public class FormBody extends RequestBody{
    private final List<String> encodeNames;
    private final List<String> encodeValues;

    public FormBody(FormBody.Builder builder) {
        super(formContentType);
        this.encodeNames = builder.names;
        this.encodeValues = builder.values;
    }

    public List<String> getEncodeNames() {
        return encodeNames;
    }

    public List<String> getEncodeValues() {
        return encodeValues;
    }

    @Override
    public byte[] getBodyBytes() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encodeNames.size(); i++) {
            sb.append(encodeNames.get(i));
            sb.append("=");
            sb.append(encodeValues.get(i));
            sb.append("&");
        }
        if (sb.length() != 0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString().getBytes();
    }

    public static class Builder{
        private final List<String> names = new ArrayList<>();
        private final List<String> values = new ArrayList<>();

        public Builder add(String name,String value){
            names.add(name);
            values.add(value);
            return this;
        }

        public Builder addEncode(String name ,String value){
            try {
                names.add(URLEncoder.encode(name,"utf-8"));
                values.add(URLEncoder.encode(value,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return this;
        }

        public RequestBody build(){
            return new FormBody(this);
        }
    }
}
