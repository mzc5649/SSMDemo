package com.mzc.utils;

import com.mzc.domain.Progress;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;
    public void setSession(HttpSession session){
        this.session=session;
        Progress status = new Progress();
        session.setAttribute("status", status);
    }
    /*
     * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件
     */
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
       // System.out.println("正在更新：pBytesRead="+pBytesRead+",pContentLength="+pContentLength+",pItems="+pItems);
        Progress status = (Progress) session.getAttribute("status");
        status.setpBytesRead(pBytesRead);
        status.setpContentLength(pContentLength);
        status.setpItems(pItems);
        Progress status1 = (Progress) session.getAttribute("status");
        //System.out.println("session更新了：pBytesRead="+status1.getpBytesRead()+",pContentLength="+status1.getpContentLength()+",pItems="+status1.getpItems());
       // session.setAttribute("status",status);
    }
}
