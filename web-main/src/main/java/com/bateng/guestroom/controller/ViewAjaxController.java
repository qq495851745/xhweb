package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.BookDownloadBiz;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookDownloadBiz bookDownloadBiz;
    @Autowired
    private BookCommentBiz bookCommentBiz;

    /**
     * 获取某本书下载量的控制器
     * @return
     */
    @RequestMapping(value = "/getData01" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getData01(){
        return  bookDownloadBiz.getDownloadNum01();
    }

    /**
     * 获取某本书评论(审核通过)的控制器
     * @return
     */
    @RequestMapping(value = "/lineSimple" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String line_simple(){
        return bookCommentBiz.getBookCommentNum();
    }
}
