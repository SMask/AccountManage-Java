package com.mask.utils;

import com.mask.controller.ApiController;

/**
 * API Controller 帮助类
 */
public class ApiControllerHelper implements ApiController {

    /**
     * 检查数据 Id
     *
     * @param id id
     * @return 正确则返回 null ，错误则返回 具体错误信息
     */
    public static String checkId(String id) {
        if (BaseUtils.isEmptyString(id)) {
            return TIPS_USER_ID_NULL;
        }

        int blogId = -1;
        try {
            blogId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (blogId <= 0) {
            return TIPS_USER_ID_ERROR;
        }

        return null;
    }

}
