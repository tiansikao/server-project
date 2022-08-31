package com.zyg.me.commons;

import lombok.Builder;

/**
 * @author CHENCHEN
 * @company XJA
 * @create 2019-09-21 14:34
 */

@Builder
public class JsonBean {
    private int code;

    private Object data;

    private String msg;

    public JsonBean() {
    }

    public JsonBean(int code, Object object, String msg) {
        this.code = code;
        this.data = object;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
