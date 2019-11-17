package com.mzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mzc.domain.Directory;
import com.mzc.domain.User;
import com.mzc.service.IDirectoryService;
import com.mzc.utils.Constants;
import com.mzc.utils.DateTimeNow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;


@Controller
public class JumpController {
    @Autowired
    IDirectoryService directoryService;
    @RequestMapping(value = "/testServlet")
    public void testServlet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("test","test123");
        System.out.println("Session="+session.getAttribute("test"));
        ServletContext servletContext = session.getServletContext();
        System.out.println("servletContext="+servletContext);
        System.out.println("response="+response);
    }
    @RequestMapping(value = "toHome")
    public String toIndex(){
        return "home";
    }
    @RequestMapping(value = "toDisk")
    public String toDisk(HttpSession session){
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        int u_id = user.getId();
        session.setAttribute("currentDirectory",username+File.separatorChar);
        //创建用户文件夹
        String realPath = Constants.ROOT_DIR+File.separatorChar+username+File.separatorChar;
        String parent_path = Constants.ROOT_DIR+File.separatorChar;
        File f1=new File(realPath);
        if(!f1.exists()){
            //若无用户目录 则创建目录
            f1.mkdirs();
            //添加目录信息到目录表
            Directory directory=new Directory();
            directory.setU_id(u_id);
            directory.setPath(realPath);
            directory.setParent_path(parent_path);
            directory.setDir(username);
            directory.setCreate_time(DateTimeNow.getCreateTime());
            boolean b = directoryService.addDirectory(directory);
            if(b){
                System.out.println("添加目录成功："+realPath);
            }else{
                System.out.println("!添加目录失败："+realPath);
            }
        }
        return "disk";
    }
    @RequestMapping(value = "toChat")
    public String toChat(){
        return "chat";
    }

}
