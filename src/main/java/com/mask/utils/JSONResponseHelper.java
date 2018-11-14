package com.mask.utils;

import org.json.JSONObject;

/**
 * JSON 返回值 帮助类
 */
public class JSONResponseHelper {

    /**
     * 获取结果
     * 默认 code 200 message ""
     *
     * @param data JSONObject
     * @return String
     */
    public static String getResult(JSONObject data) {
        return getResult(200, "", data);
    }

    /**
     * 获取结果
     *
     * @param code    结果码
     * @param message 返回信息
     * @param data    JSONObject
     * @return String
     */
    public static String getResult(int code, String message, JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        result.put("date", data);
        return result.toString();
    }

}
