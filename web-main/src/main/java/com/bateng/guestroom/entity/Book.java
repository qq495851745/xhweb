package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    private int id;

    @Column(name = "bname")
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "author")
    private String author;

    @Column(name = "bdesc")
    private String desc;

    @Column(name = "bpath")
    private String path;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "suffix_name")
    private String suffixName;

    @Column(name = "is_delete")
    private Boolean deleteFlag;

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "delflag")
    private Integer flag;//删除标记

    @Column(name = "reason")
    private String rea;

    public String getRea() {
        return rea;
    }

    public void setRea(String rea) {
        this.rea = rea;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
