package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.SubjectDao;
import com.bateng.guestroom.dao.repository.SubjectRepository;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class SubjectDaoImpl implements SubjectRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public PageVo<Subject> findSubjectByPage(PageVo<Subject> pageVo, Subject subject) {
        StringBuilder sb=new StringBuilder("from Subject s where 1=1 ");
        Map<String,Object> paramsMap=new HashMap<String, Object>();

        if(subject!=null && subject.getName()!=null && !subject.getName().equals("")){
            paramsMap.put("name","%"+subject.getName()+"%");
            sb.append("and s.name like :name ");
        }

//        if(subject.getSubject()!=null && subject.getSubject().getId()!=0){
        if(subject.getId()!=0){
            paramsMap.put("pId",subject.getId());
            sb.append(" and  s.subject.id = :pId");
        }
        Query query=entityManager.createQuery(sb.toString());

        //查询对象赋值
        for(Map.Entry<String,Object> param:paramsMap.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }


        //组装PageVo对象
        int max=query.getResultList().size();//数据总数
        int page=max%pageVo.getNumPerPage()==0?max/pageVo.getNumPerPage():(max/pageVo.getNumPerPage()+1);//总页数

        query.setMaxResults(pageVo.getNumPerPage());
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());


        pageVo.setTotalPages(page);
        pageVo.setTotalCount(max);
        pageVo.setContents(query.getResultList());
        return pageVo;
    }

}
