package com.bateng.guestroom.aspect;

import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.vo.UploadVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Aspect
@Component
public class Uploading extends BaseController{

    @Autowired
    BookUploadBiz bookUploadBiz;

    @Around(value = "execution(* com.bateng.guestroom.biz.BookUploadBiz.uploadBook(..))")
    public void uploadBookUploading(ProceedingJoinPoint joinPoint){
        Book book = null;
        UploadVo uploadVo = null;
        System.out.println("方法执行");
        Object[] args =  joinPoint.getArgs();
        for (int i=0;i<args.length;i++){
             book = (Book) args[0];
             uploadVo = (UploadVo) args[1];
        }
        File dest = new File(getWebConfig().getBookPath()+book.getPath());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
                bookUploadBiz.deleteBookByName(book);
                dest.delete();
        }finally {
            System.out.println(book.getName());
        }
        System.out.println("运行完成");
    }
}
