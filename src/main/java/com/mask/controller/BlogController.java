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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

/**
 * Blog Web Controller
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    private final BlogRepository blogRepository;

    private final UserRepository userRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    /* ********************************************* Web页面 **********************************************/

    /**
     * 博客管理
     *
     * @param modelMap modelMap
     * @return 打开的页面路径
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
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
    @RequestMapping(value = "/add", method = RequestMethod.GET)
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
    @RequestMapping(value = "/addP", method = RequestMethod.POST)
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
     * 查看博文详情
     * <p>
     * 默认使用GET方法时，method可以缺省
     *
     * @param blogId   blogId
     * @param modelMap modelMap
     * @return 打开的页面路径
     */
    @RequestMapping("/show/{id}")
    public String showBlog(@PathVariable("id") int blogId, ModelMap modelMap) {
        BlogEntity blogEntity = findById(blogId);
        modelMap.addAttribute("blog", blogEntity);
        return "admin/blog/blogDetail";
    }

    /**
     * get请求，访问 更新博文 页面
     *
     * @param blogId   blogId
     * @param modelMap modelMap
     * @return 打开的页面路径
     */
    @RequestMapping("/update/{id}")
    public String updateBlog(@PathVariable("id") int blogId, ModelMap modelMap) {
        BlogEntity blogEntity = findById(blogId);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("blog", blogEntity);
        modelMap.addAttribute("userList", userList);
        return "admin/blog/blogUpdate";
    }

    /**
     * post请求，处理更新博文请求，并重定向到博客管理页面
     *
     * @param blogEntity    blogEntity
     * @param bindingResult bindingResult
     * @param modelMap      modelMap
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/updateP", method = RequestMethod.POST)
    public String updateBlogPost(@Validated @ModelAttribute("blog") BlogEntity blogEntity, BindingResult bindingResult, ModelMap modelMap) {
        // 拦截错误信息
        if (isIntercept(blogEntity, bindingResult)) {
            List<UserEntity> userList = userRepository.findAll();
            // 向jsp注入用户列表
            modelMap.addAttribute("userList", userList);
            return "admin/blog/blogUpdate";
        }

        // 可分字段更新
        // 获取数据
        int id = blogEntity.getId();
        String title = blogEntity.getTitle();
        int userId = blogEntity.getUserByUserId().getId();
        String content = blogEntity.getContent();
        // 更新毫秒数
        long updateTime = System.currentTimeMillis();
        // 更新博文信息
        blogRepository.update(title, userId, content, updateTime, id);
        // 刷新缓冲区
        blogRepository.flush();

        return "redirect:/admin/blogs";
    }

    /**
     * 删除博文
     *
     * @param blogId blogId
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable("id") int blogId) {
        // 找到 blogId 所表示的博文
        BlogEntity blogEntity = findById(blogId);
        // 判断是否存在
        if (blogEntity != null) {
            // 删除id为userId的博文
            blogRepository.deleteById(blogId);

            // 立即刷新
            blogRepository.flush();
        }

        return "redirect:/admin/blogs";
    }
    /* ********************************************* Web页面 **********************************************/

    /* ********************************************* 公共方法 **********************************************/

    /**
     * 是否拦截博文信息
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

        // 显示其他错误信息
        if (bindingResult.hasErrors()) {
            return true;
        }

        return false;
    }

    /**
     * 查找博文
     *
     * @param blogId blogId
     * @return BlogEntity
     */
    private BlogEntity findById(int blogId) {
        BlogEntity blogEntity = null;
        Optional<BlogEntity> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            blogEntity = blogOptional.get();
        }
        return blogEntity;
    }
    /* ********************************************* 公共方法 **********************************************/

}
