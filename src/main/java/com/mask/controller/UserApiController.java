package com.mask.controller;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import com.mask.repository.BlogRepository;
import com.mask.repository.UserRepository;
import com.mask.utils.BaseUtils;
import com.mask.utils.JSONResponseHelper;
import com.mask.utils.ModelHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User Web Controller
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    private final static String TIPS_USER_NULL = "该用户不存在";
    private final static String TIPS_USER_ID_NULL = "id不能为空";
    private final static String TIPS_USER_ID_ERROR = "id参数错误";

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    public UserApiController(UserRepository userRepository, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    /* ********************************************* Api接口 **********************************************/

    /**
     * 用户列表
     *
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public String getList() {
        JSONObject data = new JSONObject();

        JSONArray list = new JSONArray();

        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();
        for (UserEntity userEntity : userList) {
            list.put(ModelHelper.getJSONObject(userEntity));
        }

        data.put("list", list);

        return JSONResponseHelper.getResult(data);
    }

    /**
     * 用户详情
     *
     * @param id id
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public String getDetail(@RequestParam(value = "id", required = false) String id) {
        if (BaseUtils.isEmptyString(id)) {
            return JSONResponseHelper.getResultError(TIPS_USER_ID_NULL);
        }

        int userId = -1;
        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (userId <= 0) {
            return JSONResponseHelper.getResultError(TIPS_USER_ID_ERROR);
        }

        JSONObject data = new JSONObject();

        UserEntity userEntity = ModelHelper.findById(userRepository, userId);

        if (userEntity == null) {
            return JSONResponseHelper.getResultError(TIPS_USER_NULL);
        }

        List<BlogEntity> blogList = blogRepository.findAllByUserByUserId(userEntity);
        userEntity.setBlogsById(blogList);

        data.put("user", ModelHelper.getJSONObject(userEntity, true));

        return JSONResponseHelper.getResult(data);
    }

    /* ********************************************* Api接口 **********************************************/

}
