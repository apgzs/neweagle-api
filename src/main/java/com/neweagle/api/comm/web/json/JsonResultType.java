package com.neweagle.api.comm.web.json;

/**
 * 描述：json数据返回类型
 * 作者：tjp
 * 时间：2017/6/30 0030 20:00
 */
public enum JsonResultType {

    Success(1000, "success"),//成功
    Fail(1001,"fail:business error"),//失败
    FailVip(1002,"fail:vip limit"),//会员限制
    FailCertification(1003,"fail:certification limit"),//实名认证限制
    ServerError(2000,"fail:server error");//服务端错误

    public Integer code;
    public String msg;

    JsonResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
