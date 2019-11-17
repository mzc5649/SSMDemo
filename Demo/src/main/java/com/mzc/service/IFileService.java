package com.mzc.service;

import com.mzc.domain.File;
import com.mzc.domain.User;

import java.util.List;

public interface IFileService {
    /**
     * 更新文件信息
     * @param file
     */
    void updateFile(File file);

    /**
     * 根据UId与Path删除文件信息
     * @param u_id
     * @param path
     */
    void deleteFileByUidAndPath(int u_id,String path);
    /**
     * 添加文件
     * @param file
     */
    boolean addFile(File file);

    /**
     * 根据U_Id与Path查询文件信息
     *
     */
    List findFileByU_idAndPath(File file);
    /**
     * 根据U_Id与d_id查询文件信息
     *
     */
    List findFileByU_idAndD_id(File file);
    /**
     * 根据自定义条件返回文件
     *
     */
    List findFileByCustom(File file);
    /**
     * 根据自定义条件删除文件
     *
     */
    boolean deleteFileByCustom(File file);
}
