package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.RoleRepository;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Repository("roleRepository")
public class RoleDaoImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public PageVo<Role> findRoleByPage(PageVo<Role> pageVo, Role role) {
        StringBuilder sb=new StringBuilder("from Role r where 1=1 ");
        Map<String,Object> paramsMap=new HashMap<String, Object>();//查询参数

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
