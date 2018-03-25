package com.neweagle.api.comm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 签名工具类
 */
public class SignUtil {
    /**
     * 生成签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createSign(Map<String, Object> params, boolean encode)
            throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }

        return MD5Utils.MD5(temp.toString()).toUpperCase();
    }

    /**
     * 验签
     * @param params
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySign(Map<String, Object> params,String sign,long expire) throws Exception{
        long nowTime = DateHelper.getCurrentTimeMillis();
        long requestTime = Long.parseLong(params.get("timestamp").toString());
        if (nowTime-requestTime<=expire&&nowTime-requestTime>0){
            if (createSign(params,true).endsWith(sign)){
                return true;
            }
        }
        return false;

    }

}
