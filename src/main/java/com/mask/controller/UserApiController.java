package com.mask.controller;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import com.mask.repository.BlogRepository;
import com.mask.repository.UserRepository;
import com.mask.utils.ApiControllerHelper;
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
 * User API Controller
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController implements ApiController {

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
     * 列表
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
     * 详情
     *
     * @param id id
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public String getDetail(@RequestParam(value = "id", required = false) String id) {
        String errorMsg = ApiControllerHelper.checkId(id);
        if (!BaseUtils.isEmptyString(errorMsg)) {
            return JSONResponseHelper.getResultError(errorMsg);
        }

        UserEntity userEntity = ModelHelper.findById(userRepository, id);

        if (userEntity == null) {
            return JSONResponseHelper.getResultError(TIPS_USER_NULL);
        }

        JSONObject data = new JSONObject();

        List<BlogEntity> blogList = blogRepository.findAllByUserByUserId(userEntity);
        userEntity.setBlogsById(blogList);

        data.put("user", ModelHelper.getJSONObject(userEntity, true));

        return JSONResponseHelper.getResult(data);
    }

    /**
     * 删除
     *
     * @param id id
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "id", required = false) String id) {
        String errorMsg = ApiControllerHelper.checkId(id);
        if (!BaseUtils.isEmptyString(errorMsg)) {
            return JSONResponseHelper.getResultError(errorMsg);
        }

        UserEntity userEntity = ModelHelper.findById(userRepository, id);

        if (userEntity == null) {
            return JSONResponseHelper.getResultError(TIPS_USER_NULL);
        }

        JSONObject data = new JSONObject();

        // 删除用户
        userRepository.deleteById(userEntity.getId());

        // 立即刷新
        userRepository.flush();

        return JSONResponseHelper.getResult(data);
    }


    /**
     * 添加
     *
     * @param username  username
     * @param password  password
     * @param nickname  nickname
     * @param firstName firstName
     * @param lastName  lastName
     * @param birthday  lastName
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "username", required = false) String username,
                      @RequestParam(value = "password", required = false) String password,
                      @RequestParam(value = "nickname", required = false) String nickname,
                      @RequestParam(value = "firstName", required = false) String firstName,
                      @RequestParam(value = "lastName", required = false) String lastName,
                      @RequestParam(value = "birthday", required = false) String birthday) {
        JSONObject data = new JSONObject();

        return JSONResponseHelper.getResult(data);
    }

    /* ********************************************* Api接口 **********************************************/

}
