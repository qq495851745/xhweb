package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.dao.DeclarationFormPhotoDao;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.DeclarationFormPhoto;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomOption;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("declarationFormBiz")
public class DeclarationFormBizImpl implements DeclarationFormBiz {

    @Autowired
    private DeclarationFormDao declarationFormDao;
    @Autowired
    private DeclarationFormPhotoDao declarationFormPhotoDao;

    @Override
    public PageVo findByRoomOptionCountByPage(PageVo<DeclarationForm> pageVo,RoomOptionVo roomOptionVo) {
        PageVo pv = declarationFormDao.findByRoomOptionCountByPage(pageVo,roomOptionVo);
        List<RoomOptionVo> roomOptionVos = new ArrayList<RoomOptionVo>();
        for(Object obj:pv.getContents()){
            Object[] objs= (Object[]) obj;
            RoomOptionVo vo =new RoomOptionVo();
            vo.setId((Integer) objs[0]);
            vo.setCreateDate((Date) objs[1]);
            vo.setFlag((Integer) objs[2]);
            vo.setName((String) objs[3]);
            vo.setUpdateDate((Date) objs[4]);
            vo.setDesprition((String) objs[6]);
            vo.setCount(new Integer(((BigInteger) objs[8]).toString()));
            roomOptionVos.add(vo);
        }
        pv.setContents(roomOptionVos);

        return pv;
    }

    @Override
    @Transactional
    public void updateFinishDate(Integer declarationFormId, Date finishDate) {
        declarationFormDao.updateFinishDate(declarationFormId,finishDate);
    }

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {

        return declarationFormDao.findDeclarationFormByPage(pageVo,declarationForm);
    }

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage4(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {
        return declarationFormDao.findDeclarationFormByPage4(pageVo,declarationForm);
    }

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage1(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {
        return declarationFormDao.findDeclarationFormByPage1(pageVo,declarationForm);
    }

    @Override
    public List<DeclarationForm> findDeclarationForms(DeclarationForm declarationForm) {
        return declarationFormDao.findDeclarationForms(declarationForm);
    }

    @Override
    public List<DeclarationForm> findDeclarationForms1(DeclarationForm declarationForm) {
        return declarationFormDao.findDeclarationForms1(declarationForm);
    }

    @Override
    public List<DeclarationForm> findDeclarationForms4(DeclarationForm declarationForm) {
        return declarationFormDao.findDeclarationForms4(declarationForm);
    }

    @Override
    @Transactional
    public void saveDeclarationForm(DeclarationForm declarationForm) {
        declarationFormDao.save(declarationForm);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        /*//删除图片
        DeclarationForm declarationForm=declarationFormDao.getOne(id);
        List<DeclarationFormPhoto> photos=declarationForm.getDeclarationFormPhotos();
        for(DeclarationFormPhoto photo:photos){
            FastDFSClient.deleteFile(photo.getPath());
        }
        declarationFormDao.deleteById(id);*/
        //假删除
//        declarationFormDao.updateByFlag(0,id);
        declarationFormDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateDeclarationForm(DeclarationForm declarationForm) {
        declarationFormDao.updateDeclaration(declarationForm.getFormName(),new Date(),declarationForm.getDescription(),declarationForm.getRoomOption().getId(),declarationForm.getRoom().getId(),declarationForm.getId(),declarationForm.getFinishDate());
        for(DeclarationFormPhoto photo:declarationForm.getDeclarationFormPhotos()){
            declarationFormPhotoDao.save(photo);
        }
    }

    @Override
    @Transactional
    public void updateStatus(DeclarationForm declarationForm) {
        declarationFormDao.updateDeclarationForm2(declarationForm.getDeclarationFormStatus().getId(),declarationForm.getId());
    }

    @Override
    public DeclarationForm getDeclarationFormById(int id) {
        return declarationFormDao.getOne(id);
    }



    public DeclarationFormDao getDeclarationFormDao() {
        return declarationFormDao;
    }

    public void setDeclarationFormDao(DeclarationFormDao declarationFormDao) {
        this.declarationFormDao = declarationFormDao;
    }

    public DeclarationFormPhotoDao getDeclarationFormPhotoDao() {
        return declarationFormPhotoDao;
    }

    public void setDeclarationFormPhotoDao(DeclarationFormPhotoDao declarationFormPhotoDao) {
        this.declarationFormPhotoDao = declarationFormPhotoDao;
    }
}
