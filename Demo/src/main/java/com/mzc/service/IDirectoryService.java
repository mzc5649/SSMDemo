package com.mzc.service;

import com.mzc.domain.Directory;
import com.mzc.domain.File;

public interface IDirectoryService {
    /**
     * 更新目录信息
     * @param directory
     */
    void updateDirectory(Directory directory);

    /**
     * 根据UId与Path删除目录信息
     * @param u_id
     * @param path
     */
    void deleteDirectoryByUidAndPathAndName(int u_id, String path);
    /**
     * 添加目录
     * @param directory
     */
    boolean addDirectory(Directory directory);
    /**
     * 根据Path查询目录信息id
     *
     * @param path 目录位置
     */
    int findDirectoryIdByPath(String path);

}
