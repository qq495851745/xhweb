package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.BookAuditRepository;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Repository("bookAuditRepository")
public class BookAuditDaoImpl implements BookAuditRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageVo<Book> findBookAuditByPage(PageVo<Book> pageVo, Book book) {
        StringBuilder sb=new StringBuilder("from Book b where 1=1");
        Map<String,Object> paramsMap=new HashMap<String, Object>();//查询参数

        if(book!=null && book.getName()!=null && !book.getName().equals("")){
            paramsMap.put("name","%"+book.getName()+"%");
            sb.append("and b.name like :name ");
        }
        if(book!=null && book.getSubject()!=null && book.getSubject().getId()!=null
                && book.getSubject().getId()!=-1){
            paramsMap.put("s_id",book.getSubject().getId());
            sb.append("and b.subject.id = :s_id ");
        }
        if(book.getFlag()!=null && book.getFlag()!=-1){
            paramsMap.put("flag",book.getFlag());
            sb.append(" and b.flag = :flag");
        }
        sb.append(" order by b.createDate ");
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
