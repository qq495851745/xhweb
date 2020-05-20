package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author 张伟金
 * @date 2020/5/13-10:17
 */
@Controller
@RequestMapping("bookDownload")
public class BookDownloadController extends WebConfig {
    @Autowired
    private BookUploadBiz bookUploadBiz;

    @Autowired
    private BookDownloadBiz bookDownloadBiz;

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<Book> pageVo, Book book, Model model) {
        pageVo = bookUploadBiz.findBookByPage(pageVo,book);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("book", book);
        model.addAttribute("subject",book.getSubject());
        return "book/bookDownload_index";
    }

    @RequestMapping(value = "download/{id}",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") int bid, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Book book = bookUploadBiz.findBookById(bid);
        System.out.println(book.getId());
        String filePath = getBookPath()+book.getPath();
        File file =new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String(book.getFileName().getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        bookDownloadBiz.addBookDownloadInfo(book,user);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
