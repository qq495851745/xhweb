package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor2;
import com.bateng.guestroom.config.interceptor.MD5PropertyEditor;
import com.bateng.guestroom.entity.vo.DownLoadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 处理统计方面的数据
 */
@Controller
@RequestMapping(value = "/ajax/view")
public class ViewAjaxController extends BaseController {

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
     * 获取某本书某个时间段下载量的控制器
     * @return
     */
    @RequestMapping(value = "/downLoad" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getDownLoad(DownLoadVo downLoadVo){
        return  bookDownloadBiz.getDownloadByDownloadDate(downLoadVo.getStart(),downLoadVo.getEnd());
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
