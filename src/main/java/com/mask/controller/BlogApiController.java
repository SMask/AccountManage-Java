package com.mask.controller;

import com.mask.model.BlogEntity;
import com.mask.repository.BlogRepository;
import com.mask.utils.JSONResponseHelper;
import com.mask.utils.ModelHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Blog Web Controller
 */
@Controller
@RequestMapping("/api/blog")
public class BlogApiController {

    private final BlogRepository blogRepository;

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    public BlogApiController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /* ********************************************* Api接口 **********************************************/

    /**
     * Blog列表
     *
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public String getList() {
        JSONObject data = new JSONObject();

        JSONArray list = new JSONArray();

        // 查询blog表中所有记录
        List<BlogEntity> blogList = blogRepository.findAll();
        for (BlogEntity blogEntity : blogList) {
            list.put(ModelHelper.getJSONObject(blogEntity));
        }

        data.put("list", list);

        return JSONResponseHelper.getResult(data);
    }

    /* ********************************************* Api接口 **********************************************/

}
