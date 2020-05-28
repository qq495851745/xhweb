package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor2;
import com.bateng.guestroom.entity.vo.DownLoadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.crypto.Data;
import java.util.Date;

@Controller
@RequestMapping("/guestroom")
public class ViewController extends BaseController {
    @Autowired
    private BookDownloadBiz downloadBiz;

    @RequestMapping(value = "/bookCommentView/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String bookCommentView() {
        return "view/bookCommentView";
    }

    @RequestMapping(value = "/bookDownloadView/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String bookDownloadIndex(DownLoadVo downLoadVo, Model model) {

        model.addAttribute("downLoadVo",downLoadVo);
        return "view/sectionView";
    }



}
