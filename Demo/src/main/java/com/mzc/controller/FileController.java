package com.mzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mzc.domain.File;
import com.mzc.domain.User;
import com.mzc.service.IDirectoryService;
import com.mzc.service.IFileService;
import com.mzc.utils.Constants;
import com.mzc.utils.DeleteFileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/file")
public class FileController {
    @Autowired
    IFileService fileService;
    @Autowired
    IDirectoryService directoryService;

    /**
     * 列出文件
     * @param session
     * @return
     */
    @RequestMapping(value = "/listFileByUidAndDid",produces = "application/json;charset=utf-8")
    public @ResponseBody String listFileByUidAndDid(HttpSession session){
        String rootDir= Constants.ROOT_DIR;
        User user = (User) session.getAttribute("user");
        int u_id = user.getId();
        String currentDirectory = (String) session.getAttribute("currentDirectory");
       //获得当前目录
        String realPath=rootDir+ java.io.File.separatorChar +currentDirectory;
        //获取目录id
        int d_id=directoryService.findDirectoryIdByPath(realPath);
        //System.out.println(u_id+"--"+d_id);
        File file=new File();
        file.setU_id(u_id);
        file.setD_id(d_id);
        ArrayList<File> list = (ArrayList<File>) fileService.findFileByU_idAndD_id(file);
        System.out.println(JSONObject.toJSONString(list));
        return JSONObject.toJSONString(list);
    }

    /**
     * 删除文件
     */
    @RequestMapping(value = "/deleteFile")
    public @ResponseBody String deleteFile(@RequestBody String param){
        Map<String,Object> map=new HashMap<>();
        map.put("status",true);
        JSONObject jsonObject = JSONObject.parseObject(param);
        int file_id = (int) jsonObject.get("file_id");
        System.out.println("接收到文件id"+file_id);
        //查询文件信息
        File find_file=new File();
        find_file.setId(file_id);
        List<File> fileList = fileService.findFileByCustom(find_file);
        if(fileList==null||fileList.size()==0){
            map.put("status",false);
            System.out.println("list为空，删除文件id:"+file_id+"失败");
            return JSONObject.toJSONString(map);
        }
        File file = (File) fileList.get(0);

        String filePath = file.getPath();
        //删除真实文件
        System.out.println("执行删除文件id:"+file_id);
        boolean boo = DeleteFileUtil.deleteFile(filePath);
        if(!boo){
            map.put("status",false);
            System.out.println("删除文件id:"+file_id+"失败");
            return JSONObject.toJSONString(map);
        }
        System.out.println("删除真实文件id:"+file_id+"成功");
        //删除数据库
        File del_file=new File();
        del_file.setId(file_id);
        System.out.println("fel_file为"+del_file);
        boolean boo1 = fileService.deleteFileByCustom(del_file);

        if(!boo1){
            map.put("status",false);
            System.out.println("删除数据库文件id:"+file_id+"失败");
            return JSONObject.toJSONString(map);
        }
        System.out.println("删除数据库文件id:"+file_id+"成功");
        return JSONObject.toJSONString(map);
    }

    /**
     * 下载文件
     * @return ResponseEntity<byte[]>
     */
    @RequestMapping(path = "/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(name = "file_id") int file_id,@RequestHeader("User-Agent") String userAgent) throws Exception {
        System.out.println("要下载的文件id:"+file_id);
        //查询文件信息
        File find_file=new File();
        find_file.setId(file_id);
        List<File> fileList = fileService.findFileByCustom(find_file);
        find_file=fileList.get(0);
        System.out.println("文件信息:"+find_file);
        String filePath = find_file.getPath();
        String filename = find_file.getFilename();
        //文件
        java.io.File file=new java.io.File(filePath);
        filename= URLEncoder.encode(filename,"UTF-8");
        //200 状态码

        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentLength(file.length());
        // application/octet-stream 二进制数据流（最常见的文件下载）
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 根据浏览器类型，决定处理方式
        if (userAgent.indexOf("MSIE") > 0) {
            builder.header("Content-Disposition", "attachment; filename=" + filename);
        } else {
           builder.header("Content-Disposition", "attacher; filename*=UTF-8''" + filename);
        }

        return builder.body(FileUtils.readFileToByteArray(file));
    }

}
