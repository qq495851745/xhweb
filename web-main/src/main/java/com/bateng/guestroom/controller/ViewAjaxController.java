package com.bateng.guestroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理统计方面的数据
 */
@Controller
@RequestMapping(value = "/ajax/view")
public class ViewAjaxController {

    /**
     * 获取某本书下载量的控制器
     * @return
     */
    @RequestMapping(value = "/getData01" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getData01(){

        return  "";
    }
}
