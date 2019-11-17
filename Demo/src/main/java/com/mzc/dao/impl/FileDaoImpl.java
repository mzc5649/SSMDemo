package com.mzc.dao.impl;

import com.mzc.dao.IFileDao;
import com.mzc.domain.File;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fileDao")
public class FileDaoImpl implements IFileDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Override
    public void updateFile(File file) {

    }

    @Override
    public void deleteFileByUidAndPath(int u_id, String path) {

    }

    @Override
    public boolean addFile(File file)
    {
        try {
            sqlSessionTemplate.insert("com.mzc.dao.IFileDao.addFile",file);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List findFileByU_idAndPath(File file) {
        List<File> list=new ArrayList<>();
                list=sqlSessionTemplate.selectList("com.mzc.dao.IFileDao.findFileByU_idAndPath",file);
        if(list==null||list.size()==0){
            return null;
        }
        return list;
    }

    @Override
    public List findFileByU_idAndD_id(File file) {

        List<File> list=null;
        list=sqlSessionTemplate.selectList("com.mzc.dao.IFileDao.findFileByU_idAndD_id",file);
        if(list==null||list.size()==0){
            return null;
        }
        return list;
    }

    @Override
    public List findFileByCustom(File file) {
        List<File> list=null;
        try {
            list=sqlSessionTemplate.selectList("com.mzc.dao.IFileDao.findFileByCustom",file);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean deleteFileByCustom(File file) {
        try {
            sqlSessionTemplate.delete("com.mzc.dao.IFileDao.deleteFileByCustom",file);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
