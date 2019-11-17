package com.mzc.service.impl;

import com.mzc.dao.IFileDao;
import com.mzc.domain.File;
import com.mzc.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl implements IFileService {
    @Autowired
    IFileDao fileDao;
    @Override
    public void updateFile(File file) {
        fileDao.updateFile(file);
    }

    @Override
    public void deleteFileByUidAndPath(int u_id, String path) {
        fileDao.deleteFileByUidAndPath(u_id, path);
    }

    @Override
    public boolean addFile(File file) {

        try {
            boolean isSuc= fileDao.addFile(file);
            return isSuc;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List findFileByU_idAndPath(File file) {
        return fileDao.findFileByU_idAndPath(file);
    }

    @Override
    public List findFileByU_idAndD_id(File file) {
        return fileDao.findFileByU_idAndD_id(file);
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public List findFileByCustom(File file) {
        try {
            return fileDao.findFileByCustom(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deleteFileByCustom(File file) {
        try {
            return fileDao.deleteFileByCustom(file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
