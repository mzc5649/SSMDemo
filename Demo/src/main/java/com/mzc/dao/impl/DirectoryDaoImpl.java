package com.mzc.dao.impl;

import com.mzc.dao.IDirectoryDao;
import com.mzc.domain.Directory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "directoryDao")
public class DirectoryDaoImpl implements IDirectoryDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Override
    public void updateDirectory(Directory directory) {

    }

    @Override
    public void deleteDirectoryByUidAndPathAndName(int u_id, String path) {

    }

    @Override
    public boolean addDirectory(Directory directory) {

        try {
            sqlSessionTemplate.insert("com.mzc.dao.IDirectoryDao.addDirectory",directory);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public int findDirectoryIdByPath(String path) {
        try {
            List<Directory> list = sqlSessionTemplate.selectList("com.mzc.dao.IDirectoryDao.findDirectoryIdByPath", path);
            if(null==list||list.size()==0){
                return  -1;
            }
            if(list.size()>1){
                throw new RuntimeException("数据不唯一，错误");
            }
            return list.get(0).getId();
        } catch (Exception e) {
            throw new RuntimeException("数据不唯一，错误");
        }



    }
}
