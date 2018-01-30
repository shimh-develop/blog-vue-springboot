package com.shimh.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;

@RestController
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@PostMapping("/upload")
	@RequiresAuthentication
	public Result upload(HttpServletRequest request, MultipartFile image){
		
		Result r = new Result();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
        StringBuffer url = new StringBuffer();
        String filePath = "/blogFile/" + sdf.format(new Date());
        String imgFolderPath = request.getServletContext().getRealPath(filePath);
        System.out.println(imgFolderPath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        
        try {
        	
        	File dest = new File(imgFolder, imgName);
        	image.transferTo(dest);
        	
            url.append("/").append(imgName);
            
            System.out.println(url);
            
            r.setResultCode(ResultCode.SUCCESS);
            
            r.simple().put("url", url);
            
        } catch (IOException e) {
        	logger.error("文件上传错误 , uri: {} , caused by: ", request.getRequestURI(), e);
        	r.setResultCode(ResultCode.UPLOAD_ERROR);
        }
		
		return r;
	}
}
