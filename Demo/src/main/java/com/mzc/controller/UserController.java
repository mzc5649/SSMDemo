package com.mzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mzc.domain.User;
import com.mzc.service.IUserService;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {
    @Autowired
     @Qualifier("userService")
    IUserService userService;

    @RequestMapping("/loginValidate")
    public  @ResponseBody String loginValidate(@RequestBody String param, HttpSession session){
        JSONObject jsonObject=JSONObject.parseObject(param);
        Map<String,Object> map=new HashMap<>();
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        List<User> u = userService.findUserByUsername(username);
        if(u==null){
            map.put("status","false");
        }else{
            User user = u.get(0);
            String psw = user.getPassword();
            if(psw.equals(password)){
                map.put("status","true");
                session.setAttribute("user",user);
               /* Enumeration<String> attributeNames = session.getAttributeNames();
                while(attributeNames.hasMoreElements()){
                    String key= attributeNames.nextElement().toString();
                    Object value = session.getAttribute(key);
                    System.out.println("session:"+key+"@"+value);
                }*/
            }else{
                map.put("status","false");
            }

        }
        return JSONObject.toJSONString(map);
    }
    @RequestMapping("/registerValidate")
    public @ResponseBody String registerValidate(@RequestBody String param){
        //System.out.println("registerValidate已运行");
        JSONObject jsonObject=JSONObject.parseObject(param);
        Map<String,Object> map=new HashMap<>();
        String username = (String) jsonObject.get("reg_username");
        String password = (String) jsonObject.get("reg_password");
        System.out.println("要注册的"+username+"--"+password);
        List<User> u = userService.findUserByUsername(username);
        if(u==null){//为空或为0返回null
            User newUser=new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            boolean isSuccess = userService.addUser(newUser);
            if(isSuccess){
                map.put("status","true");
            }else{
                map.put("status","false");
            }
        }else{
            map.put("status","exists");
        }
        return JSONObject.toJSONString(map);
    }
    @RequestMapping("/exit")
    public  String exit(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }
    @RequestMapping("/getUserInfo")
    public @ResponseBody String getSession(HttpSession session){
        User user=null;
        User newUser= (User) session.getAttribute("user");
        user=newUser;
        return JSONObject.toJSONString(user);
    }
    @RequestMapping("/getTime")
    public @ResponseBody String getTime(){
        Map<String,Object> map=new HashMap<>();
        DateFormat df =new SimpleDateFormat("HH:mm:ss");
        String time = df.format(new Date());
        map.put("time",time);
        return JSONObject.toJSONString(map);
    }

    /**
     * 返回当前路径目录
     * @param session
     * @return
     */
    @RequestMapping("/getCurrentDirectory")
    public @ResponseBody String getCurrentDirectory(HttpSession session){

        String currentDirectory = (String) session.getAttribute("currentDirectory");
        Map<String,Object> map=new HashMap<>();
        map.put("currentDirectory",currentDirectory);
        return JSONObject.toJSONString(map);
    }

}
