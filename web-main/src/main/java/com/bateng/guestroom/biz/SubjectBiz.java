package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.*;

import java.util.List;

public interface SubjectBiz {

    /**
     * 通过subject.id查询图书
     * */
    public List<Book> findBookById(int id);
    /**
     * 查询所有数据
     * @return 类目对象
     */
    public List<Subject> findSubject();

    /**
     * 分页查询 subject
     * @param pageVo 分页
     * @param subject 类目
     * @return 分页对象
     */
    public PageVo<Subject> findSubjectByPage(PageVo<Subject> pageVo, Subject subject);
    public String findSubjectAjax();

    /**
     * 添加数据
     */
    public void addSubject(Subject subject);

    /**
     *  通过name查询所有值
     * @return 类目
     */

    public List<Subject> findAllByName(Subject subject);


    public Subject getSubjectById(int id);


    public List<Subject> findAllByPid(int pid);

    /**
     * 做删除操作
     * @param id
     */
    public void deleteSubjectById(int id);

    public void updateSubject(Subject subject);


}
