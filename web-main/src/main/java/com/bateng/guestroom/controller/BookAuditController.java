package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.BookAuditBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guestroom")
public class BookAuditController {
    @Autowired
    private BookAuditBiz bookAuditBiz;

    @RequestMapping(value = "/bookAudit/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<Book> pageVo, Book book, Model model) {
        pageVo = bookAuditBiz.findBookAuditByPage(pageVo,book);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("book", book);
        model.addAttribute("subject",book.getSubject());
        return "book/book_audit_index";
    }


    //跳转修改页面
    @RequestMapping(value = "/bookAudit/{id}",method = {RequestMethod.GET})
    public String toEdit(@PathVariable("id") int id, Model model) {
        Book book = bookAuditBiz.getBookById(id);
        model.addAttribute("book",book);
        return "book/book_audit_edit";
    }

    //做修改
    @RequestMapping(value = "/bookAudit",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(Book book){
        bookAuditBiz.updateBook(book);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "审核完成!");
        jsonObject.put("navTabId", "w_45");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }
}
