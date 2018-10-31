package com.mask.controller;

import com.mask.model.UserEntity;
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
public class UserController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    /**
     * 用户管理
     *
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "/admin/users";
    }

    /**
     * get请求，访问添加用户 页面
     *
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "admin/addUser";
    }

    /**
     * post请求，处理添加用户请求，并重定向到用户管理页面
     * <p>
     * // 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult bindingResult接收校验出错信息
     * // 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
     * // @ModelAttribute("user")可以进行数据回显，也就是数据提交后，如果出现错误，将刚才提交的数据回显到刚才的提交页面。
     *
     * @param userEntity userEntity
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@Validated @ModelAttribute("user") UserEntity userEntity, BindingResult bindingResult) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 显示格式等错误信息
        if (bindingResult.hasErrors()) {
            return "admin/addUser";
        }

        // 判断Username是否已注册
        List<UserEntity> userList = userRepository.findAllByUsername(userEntity.getUsername());
        if (userList != null && !userList.isEmpty()) {
            bindingResult.rejectValue("username", "user.username.repeat");
        }

        // 判断两次输入密码是否一致
        if (!userEntity.getPassword().equals(userEntity.getPasswordAgain())) {
            bindingResult.rejectValue("passwordAgain", "user.password.again.illegal");
        }

        // 显示其他错误信息
        if (bindingResult.hasErrors()) {
            return "admin/addUser";
        }

        // 获取注册毫秒数
        long registerTime = System.currentTimeMillis();
        userEntity.setRegisterTime(registerTime);

        // 设置Token
        userEntity.setToken(userEntity.getUsername() + "+" + registerTime);

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        // userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/users";
    }
}
