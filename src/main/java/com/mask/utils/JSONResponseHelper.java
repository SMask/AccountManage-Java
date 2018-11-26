package com.mask.utils;

import org.json.JSONObject;

/**
 * JSON 返回值 帮助类
 */
public class JSONResponseHelper {

    private final static int CODE_SUCCESS = 200;// 成功返回码
    private final static int CODE_ERROR = 500;// 错误返回码

    /**
     * 获取结果 (错误)
     * 默认 code {@link #CODE_ERROR}
     *
     * @param message message
     * @return String
     */
    public static String getResultError(String message) {
        return getResultError(CODE_ERROR, message);
    }

    /**
     * 获取结果 (错误)
     *
     * @param code    code
     * @param message message
     * @return String
     */
    public static String getResultError(int code, String message) {
        return getResult(code, message, null);
    }

    /**
     * 获取结果
     * 默认 code {@link #CODE_SUCCESS} message ""
     *
     * @param data JSONObject
     * @return String
     */
    public static String getResult(JSONObject data) {
        return getResult(CODE_SUCCESS, "", data);
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
        result.put("data", data);
        return result.toString();
    }

}
