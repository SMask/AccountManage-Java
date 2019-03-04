package com.mask.controller;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import com.mask.repository.BlogRepository;
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
 * Blog API Controller
 */
@Controller
@RequestMapping("/api/blog")
public class BlogApiController implements ApiController {

    private final BlogRepository blogRepository;

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    public BlogApiController(BlogRepository blogRepository) {
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

        // 查询blog表中所有记录
        List<BlogEntity> blogList = blogRepository.findAll();
        for (BlogEntity blogEntity : blogList) {
            list.put(ModelHelper.getJSONObject(blogEntity));
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

        BlogEntity blogEntity = ModelHelper.findById(blogRepository, id);

        if (blogEntity == null) {
            return JSONResponseHelper.getResultError(TIPS_BLOG_NULL);
        }

        JSONObject data = new JSONObject();

        data.put("blog", ModelHelper.getJSONObject(blogEntity, true));

        return JSONResponseHelper.getResult(data);
    }

    /* ********************************************* Api接口 **********************************************/

}
