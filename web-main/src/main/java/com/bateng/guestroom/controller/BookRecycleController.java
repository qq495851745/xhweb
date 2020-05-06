package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author 张伟金
 * @date 2020/5/5-16:53
 */
@Controller
@RequestMapping("/guestroom")
public class BookRecycleController extends WebConfig {
    @Autowired
    BookUploadBiz bookUploadBiz;

    @RequestMapping(value = "/bookRecycle/index", method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
    public String index(PageVo<Book> pageVo, Book book, Model model) {
        pageVo = bookUploadBiz.findRecycleBookByPage(pageVo, book);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("book", book);
        model.addAttribute("subject", book.getSubject());
        return "book/bookRecycle_index";
    }

    @RequestMapping(value = "/book/recycleDelete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doRecycleDelete(@PathVariable("id") int bid) {
        Book book = bookUploadBiz.findBookById(bid);
        String filePath = getBookPath() + book.getPath();
        File file = new File(filePath);
        file.delete();
        bookUploadBiz.deleteBookById(bid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("navTabId", "w_44");
        jsonObject.put("message", "删除成功");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/book/restore/{id}", method = {RequestMethod.PUT,RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doRestore(@PathVariable("id") int bid) {
        Book book = bookUploadBiz.findBookById(bid);
        book.setDeleteFlag(false);
        bookUploadBiz.updateBook(book);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("navTabId", "w_44");
        jsonObject.put("message", "还原成功");
        return jsonObject.toJSONString();
    }
}