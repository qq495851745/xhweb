package com.bateng.guestroom.aspect;

import com.bateng.guestroom.entity.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Uploading {

    @Before(value = "execution(* com.bateng.guestroom.biz.BookUploadBiz.uploadBook())")
    public void uploadBookUploading(){
        Object obj = null;
        System.out.println("方法执行");
//        Book book = (Book) joinPoint.getTarget();
        /*try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
            System.out.println("发生异常");
        }*/
        System.out.println("运行完成");
//        return obj;
    }
}
