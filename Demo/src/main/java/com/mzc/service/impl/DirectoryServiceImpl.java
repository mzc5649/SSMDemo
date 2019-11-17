package com.mzc.service.impl;

import com.mzc.dao.IDirectoryDao;
import com.mzc.domain.Directory;
import com.mzc.service.IDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "directoryService")
public class DirectoryServiceImpl implements IDirectoryService {
    @Autowired
    IDirectoryDao directoryDao;


    @Override
    public void updateDirectory(Directory directory) {

    }

    @Override
    public void deleteDirectoryByUidAndPathAndName(int u_id, String path) {

    }

    @Override
    public boolean addDirectory(Directory directory) {
        try {
            boolean b = directoryDao.addDirectory(directory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int findDirectoryIdByPath(String path) {
        try {
            return directoryDao.findDirectoryIdByPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
