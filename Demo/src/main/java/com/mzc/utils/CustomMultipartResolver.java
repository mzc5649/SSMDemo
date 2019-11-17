package com.mzc.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CustomMultipartResolver extends CommonsMultipartResolver {
    @Autowired
    private FileUploadProgressListener progressListener;
    public void setFileUploadProgressListener(FileUploadProgressListener progressListener){
        this.progressListener=progressListener;
    }
    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = this.determineEncoding(request);
        FileUpload fileUpload = this.prepareFileUpload(encoding);
        progressListener.setSession(request.getSession());
        fileUpload.setProgressListener(progressListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
            return this.parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException var5) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), var5);
        } catch (FileUploadException var6) {
            throw new MultipartException("Could not parse multipart servlet request", var6);
        }
    }
}
