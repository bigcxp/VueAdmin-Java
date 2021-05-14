package com.cxp.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cxp
 * @Date: 2021/5/14
 * @Time: 13:26
 * @Description: 请求统一返回
 */
@Data
public class HttpResult implements Serializable {
    private int code;//状态码
    private String msg;//信息
    private Object data;//数据


    public static HttpResult succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static HttpResult succ(int code, String msg, Object data) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static HttpResult fail(String msg) {
        return fail(400, msg, null);
    }

    public static HttpResult fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static HttpResult fail(int code, String msg, Object data) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
