package com.mask.controller;

import com.mask.model.UserEntity;
import com.mask.repository.UserRepository;
import com.mask.utils.JSONResponseHelper;
import com.mask.utils.TimeUtils;
import com.mask.utils.UserHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User Web Controller
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    /* ********************************************* Api接口 **********************************************/

    @ResponseBody
    @RequestMapping(value = "/list")
    public String getList() {
        JSONObject data = new JSONObject();

        JSONArray list = new JSONArray();

        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();
        for (UserEntity userEntity : userList) {
            list.put(UserHelper.getJSONObject(userEntity));
        }

        data.put("list", list);

        return JSONResponseHelper.getResult(data);
    }

    /* ********************************************* Api接口 **********************************************/

}
