package com.bateng.guestroom.entity.vo;

import com.bateng.guestroom.config.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadVo {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
