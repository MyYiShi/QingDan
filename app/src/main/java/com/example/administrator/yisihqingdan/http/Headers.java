package com.example.administrator.yisihqingdan.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yishi on 2016/10/27.
 */

public class Headers {
    private List<String> nameAndValues;

    private Headers(Builder builder) {
        this.nameAndValues = builder.nameAndValues;
    }

    public List<String> getNameAndValues() {
        return nameAndValues;
    }

    public static class Builder{
        private List<String> nameAndValues;
        public Builder(){
            nameAndValues = new ArrayList<>();
        }

        public Builder addHead(String name ,String values){
            nameAndValues.add(name);
            nameAndValues.add(values);
            return this;
        }

        public Builder addHeads(Headers headers){
            nameAndValues.addAll(headers.nameAndValues);
            return this;
        }

        public Headers build(){
            return new Headers(this);
        }
    }
}
