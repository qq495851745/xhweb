package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.BookCommentRepository;
import com.bateng.guestroom.entity.Comment;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Repository("bookCommentRepository")
public class BookCommentDaoImpl implements BookCommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageVo<Comment> findCommentAuditByPage(PageVo<Comment> pageVo, Comment comment) {
        StringBuilder sb=new StringBuilder("from Comment c where 1=1 ");
        Map<String,Object> paramsMap=new HashMap<String, Object>();//查询参数
        if(comment.getFlag()!=null && comment.getFlag()!=-1){
            paramsMap.put("flag",comment.getFlag());
            sb.append(" and c.flag = :flag");
        }
        sb.append(" order by c.commentDate");
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

    @Override
    public PageVo<Comment> findLookCommentAuditByPage(PageVo<Comment> pageVo, Comment comment) {
        StringBuilder sb=new StringBuilder("from Comment c where c.flag=1 ");
        Map<String,Object> paramsMap=new HashMap<String, Object>();//查询参数
        if(comment!=null && comment.getCommentContent()!=null && !comment.getCommentContent().equals("")){
            paramsMap.put("commentContent","%"+comment.getCommentContent()+"%");
            sb.append("and c.commentContent like :commentContent ");
        }
        if(comment.getBook().getId()!=0){
            paramsMap.put("bid",comment.getBook().getId());
            sb.append(" and  c.book.id = :bid");
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
