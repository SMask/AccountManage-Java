package com.mask.utils;

import com.mask.model.UserEntity;
import org.json.JSONObject;

/**
 * User 帮助类
 */
public class UserHelper {

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

}
