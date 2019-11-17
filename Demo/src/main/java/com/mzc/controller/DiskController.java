package com.mzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mzc.domain.User;
import com.mzc.service.IDirectoryService;
import com.mzc.service.IFileService;
import com.mzc.utils.Constants;
import com.mzc.utils.DateTimeNow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(path = "/disk")
public class DiskController {
    @Autowired
    IDirectoryService directoryService;
    @Autowired
    IFileService fileService;
    /**
     * 上传文件成功后 添加文件信息到数据库
     * @param request
     * @param uploads
     * @return
     */
    @RequestMapping(value = "/fileUpload")
    public @ResponseBody String fileUpload(HttpServletRequest request, MultipartFile uploads)  {
        System.out.println("fileUpload执行了");
        //获取当前用户信息
        User user = (User) request.getSession().getAttribute("user");
        // 当前目录
        String currentDirectory = (String) request.getSession().getAttribute("currentDirectory");
       //用户Id
        int u_id=user.getId();
        Map map=new HashMap<String,Object>();
        map.put("status","success");
        //String realPath = request.getSession().getServletContext().getRealPath("/uploads/"+username+"/");
        //linux 加/
        String realPath = Constants.ROOT_DIR+File.separatorChar+currentDirectory;
        String filename = uploads.getOriginalFilename();
        //暂时不用uuid 显示文件真实 避免重复文件在同一个目录下
        //String uuid = UUID.randomUUID().toString().replace("-","");
        //filename=uuid+"_"+filename;
        //System.out.println("realPath:"+realPath);
        int d_id = directoryService.findDirectoryIdByPath(realPath);
        if(d_id==-1){
            map.put("status","notExist");
            System.out.println("文件目录不存在");
            return JSONObject.toJSONString(map);
        }
        try {
            com.mzc.domain.File file2=new com.mzc.domain.File();
            file2.setU_id(u_id);
            file2.setD_id(d_id);
            file2.setFilename(filename);
            System.out.println("要查询的file:"+file2);
            //查询当前用户 目录下 是否有相同文件
            ArrayList<File> fileByCustom = (ArrayList<File>) fileService.findFileByCustom(file2);
            if(fileByCustom.size()>0){
                map.put("status","same");
                System.out.println("该目录下存在相同文件名");
                return JSONObject.toJSONString(map);
            }

            uploads.transferTo(new File(realPath,filename));
            //文件上传成功后 添加文件信息到文件表
            com.mzc.domain.File file1=new com.mzc.domain.File();
            file1.setU_id(u_id);
            file1.setFilename(filename);
            file1.setPath(realPath+filename);
            file1.setSize(1);
            file1.setD_id(d_id);
            file1.setUpload_time(DateTimeNow.getCreateTime());
            boolean b = fileService.addFile(file1);
            if(b){
                map.put("status","success");
            }else{
                map.put("status","error");
                System.out.println("文件信息添加失败："+file1);
            }

        } catch (Exception e) {
            map.put("status","exception");
            e.printStackTrace();
        }
        return JSONObject.toJSONString(map);
    }




}
