package com.mask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    /**
     * 首页
     *
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 测试日期选择器
     *
     * @return 打开的页面路径
     */
    @RequestMapping(value = "/datetimepicker", method = RequestMethod.GET)
    public String dateTimePicker() {
        return "demo/datetimepicker";
    }

}
