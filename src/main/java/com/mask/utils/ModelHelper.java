package com.mask.utils;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import org.json.JSONObject;

/**
 * Model 帮助类
 */
public class ModelHelper {

    /**
     * 获取 User JSONObject
     *
     * @param userEntity userEntity
     * @return JSONObject
     */
    public static JSONObject getJSONObject(UserEntity userEntity) {
        JSONObject temp = new JSONObject();

        temp.put("id", userEntity.getId());
        temp.put("username", userEntity.getUsername());
        temp.put("password", userEntity.getPassword());
        temp.put("nickname", userEntity.getNickname());
        temp.put("firstName", userEntity.getFirstName());
        temp.put("lastName", userEntity.getLastName());
        temp.put("birthday", TimeUtils.getDate(userEntity.getBirthday()));
        temp.put("registerTime", TimeUtils.getDateTime(userEntity.getRegisterTime()));
        temp.put("updateTime", TimeUtils.getDateTime(userEntity.getUpdateTime()));
        temp.put("token", userEntity.getToken());

        return temp;
    }

    /**
     * 获取 Blog JSONObject
     *
     * @param blogEntity blogEntity
     * @return JSONObject
     */
    public static JSONObject getJSONObject(BlogEntity blogEntity) {
        JSONObject temp = new JSONObject();

        temp.put("id", blogEntity.getId());
        temp.put("title", blogEntity.getTitle());
        temp.put("content", blogEntity.getContent());
        temp.put("author", getJSONObject(blogEntity.getUserByUserId()));
        temp.put("publishTime", TimeUtils.getDate(blogEntity.getPublishTime()));
        temp.put("updateTime", TimeUtils.getDateTime(blogEntity.getUpdateTime()));

        return temp;
    }

}
