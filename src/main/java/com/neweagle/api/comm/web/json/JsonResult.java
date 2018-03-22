package com.neweagle.api.comm.web.json;

/**
 * 描述：返回给客户端封装的对象
 * 作者：tjp
 * 时间：2017/6/30 0030 19:57
 */
public class JsonResult {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Object data;
    private Object subData;

    public Object getSubData() {
        return subData;
    }

    public void setSubData(Object subData) {
        this.subData = subData;
    }

    public JsonResult() {
    }

    public JsonResult(int code, String msg, Object data, Object subData) {
        this.code = code;
        this.data = data;
        this.subData = subData;
        this.msg=msg;
    }
    public static JsonResult success(Object data) {
        return new JsonResult(JsonResultType.Success.code, JsonResultType.Success.msg, data,null);
    }

    public static JsonResult success(Object data,String msg, Object subData) {
        return new JsonResult(JsonResultType.Success.code, msg, data,subData);
    }

    public static JsonResult success(Object data, Object subData) {
        return new JsonResult(JsonResultType.Success.code, JsonResultType.Success.msg, data,subData);
    }

    public static JsonResult success() {
        return new JsonResult(JsonResultType.Success.code, JsonResultType.Success.msg, null,null);
    }
    public static JsonResult success(String content) {
        return new JsonResult(JsonResultType.Success.code, content, null,null);
    }
    public static JsonResult error() {
        return new JsonResult(JsonResultType.Fail.code, JsonResultType.Fail.msg, null,null);
    }
    public static JsonResult serverError() {
        return new JsonResult(JsonResultType.ServerError.code, JsonResultType.ServerError.msg, null,null);
    }

    public static JsonResult success(String content, Object data) {
        return new JsonResult(JsonResultType.Success.code, content, data,null);
    }

    public static JsonResult error(int code, String content) {
        return new JsonResult(code, content, null,null);
    }

    public static JsonResult error(String content) {
        return new JsonResult(JsonResultType.Fail.code, content, null,null);
    }

    public static JsonResult serverError(String content, Object data) {
        return new JsonResult(JsonResultType.ServerError.code, content, data,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
