package com.yinp.fortunatereader.web.retrofit;

import com.google.gson.JsonElement;

import java.io.Serializable;

public class BaseRetrofitData implements Serializable {
    private JsonElement data;
    private int code;
    private String msg;

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
