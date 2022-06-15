package com.mx.odms_be.entity;

import lombok.Data;

@Data
public class R {
    private Integer code;
    private String massage;
    private Object data;
    private String token;

    public static R success(Integer code, Object data) {
        R result = new R();
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static R failure(Integer code, String message) {
        R result = new R();
        result.setCode(code);
        result.setMassage(message);
        return result;
    }

    public static R userSuccess(Integer code, String message, Object data, String token) {
        R result = new R();
        result.setCode(code);
        result.setMassage(message);
        result.setData(data);
        result.setToken(token);
        return result;
    }
}
