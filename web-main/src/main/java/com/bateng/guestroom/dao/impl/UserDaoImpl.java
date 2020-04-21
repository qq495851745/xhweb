package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.UserRepository;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Repository("userRepository")
public class UserDaoImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageVo<User> findUserByPage(PageVo<User> pageVo, User user) {
        StringBuilder sb=new StringBuilder("from User u where 1=1 ");
        Map<String,Object> paramsMap=new HashMap<String, Object>();

        if(user!=null && user.getUsername()!=null&& !user.getUsername().equals("")){
            paramsMap.put("username","%"+user.getUsername()+"%");
            sb.append(" and u.username  like :username ");
        }

        if(user!=null&&user.getUserLevel()!=null&&user.getUserLevel().getId()!=null){
            paramsMap.put("userLevelId",user.getUserLevel().getId());
            sb.append(" and u.userLevel.id=:userLevelId ");
        }

        if(user!=null&&user.getRole()!=null&&user.getRole().getId()!=null && user.getRole().getId()!=-1){
            paramsMap.put("roleId",user.getRole().getId());
            sb.append(" and u.role.id=:roleId ");
        }

        if(user.getFlag()!=null && user.getFlag()!=-1){
            paramsMap.put("flag",user.getFlag());
            sb.append(" and u.flag = :flag");
        }
           sb.append(" order by u.updateDate desc ");
        Query query=entityManager.createQuery(sb.toString());

        for(Map.Entry<String,Object> param:paramsMap.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }

        int max=query.getResultList().size();
        int page=max%pageVo.getNumPerPage()==0?max/pageVo.getNumPerPage():(max/pageVo.getNumPerPage()+1);

        query.setMaxResults(pageVo.getNumPerPage());
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());


        pageVo.setTotalPages(page);
        pageVo.setTotalCount(max);
        pageVo.setContents(query.getResultList());
        return pageVo;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
