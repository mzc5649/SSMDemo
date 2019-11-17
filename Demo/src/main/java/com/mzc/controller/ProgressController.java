package com.mzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mzc.domain.Progress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProgressController {
    @RequestMapping(path = "/disk/progress")
    public @ResponseBody String progress(HttpServletRequest request)
    {
        //System.out.println("监听-------------");
        Map<String,Object> map=new HashMap<>();
        Progress progress=(Progress) request.getSession().getAttribute("status");
        long read = progress.getpBytesRead();
        long length = progress.getpContentLength();
        double dnum=(double)read/(double)length*100;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        System.out.println(read+"---"+length);
        String s = decimalFormat.format(dnum);
        s+="%";
        System.out.println(s);
        map.put("percent",s);

//        if(s.equals("100.00%")){
//            System.out.println("到达100 移除");
//            request.getSession().removeAttribute("status");
//        }
        return JSONObject.toJSONString(map);
    }
}
