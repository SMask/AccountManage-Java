package com.mask.controller;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import com.mask.repository.BlogRepository;
import com.mask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 博客管理
     *
     * @param modelMap modelMap
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/blogs", method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap) {
        List<BlogEntity> blogList = blogRepository.findAll();
        modelMap.addAttribute("blogList", blogList);
        return "admin/blog/blogList";
    }

    /**
     * 添加博文
     *
     * @param modelMap modelMap
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap) {
        List<UserEntity> userList = userRepository.findAll();
        // 向jsp注入用户列表
        modelMap.addAttribute("userList", userList);
        return "admin/blog/blogAdd";
    }

    /**
     * post请求，处理添加博文请求，并重定向到博客管理页面
     *
     * @param blogEntity    blogEntity
     * @param bindingResult bindingResult
     * @param modelMap      modelMap
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/blogs/addP", method = RequestMethod.POST)
    public String addBlogPost(@Validated @ModelAttribute("blog") BlogEntity blogEntity, BindingResult bindingResult, ModelMap modelMap) {
        // 拦截错误信息
        if (isIntercept(blogEntity, bindingResult)) {
            List<UserEntity> userList = userRepository.findAll();
            // 向jsp注入用户列表
            modelMap.addAttribute("userList", userList);
            return "admin/blog/blogAdd";
        }

//        // 打印博客标题
//        System.out.println("------------------------");
//        System.out.println(blogEntity.getTitle());
//        // 打印博客作者
//        System.out.println(blogEntity.getUserByUserId().getId());
//        System.out.println(blogEntity.getUserByUserId().getUsername());
//        System.out.println(blogEntity.getUserByUserId().getNickname());
//        System.out.println("------------------------");

        // 获取发布毫秒数
        long publishTime = System.currentTimeMillis();
        blogEntity.setPublishTime(publishTime);

        blogRepository.saveAndFlush(blogEntity);

        return "redirect:/admin/blogs";
    }

    /**
     * 是否拦截用户信息
     *
     * @param blogEntity    blogEntity
     * @param bindingResult bindingResult
     * @return 是否拦截
     */
    private boolean isIntercept(BlogEntity blogEntity, BindingResult bindingResult) {
        // 显示格式等错误信息
        if (bindingResult.hasErrors()) {
            return true;
        }

        // 判断是否已选择Author
        if (blogEntity.getUserByUserId() == null) {
            bindingResult.rejectValue("userByUserId.id", "blog.user.illegal");
        }

//        // 显示其他错误信息
//        if (bindingResult.hasErrors()) {
//            return true;
//        }

        return false;
    }

}
