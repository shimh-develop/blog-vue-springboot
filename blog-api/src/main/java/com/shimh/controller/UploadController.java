package com.shimh.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.shimh.common.annotation.LogAnnotation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;

@RestController
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final java.util.Set<String> ALLOWED_EXTENSIONS = new java.util.HashSet<>(java.util.Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp", "webp"
    ));

    @Value("${me.upload.path}")
    private String baseFolderPath;

    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isAllowedExtension(String extension) {
        return extension != null && ALLOWED_EXTENSIONS.contains(extension);
    }

    private String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.\\-_]", "_");
    }

    @PostMapping("/upload")
    @RequiresAuthentication
    @LogAnnotation(module = "文件上传", operation = "文件上传")
    public Result upload(HttpServletRequest request, @RequestParam("image") MultipartFile image) {

        Result r = new Result();

        if (image == null || image.isEmpty()) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            r.setResultCode(ResultCode.PARAM_IS_INVALID);
            return r;
        }

        String extension = getFileExtension(originalFilename);
        if (!isAllowedExtension(extension)) {
            r.setResultCode(ResultCode.PARAM_IS_INVALID);
            r.simple().put("errdetail", "不支持的文件类型");
            return r;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        StringBuffer url = new StringBuffer();
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append("/")
                .append(filePath);

        String safeFilename = sanitizeFilename(originalFilename);
        String imgName = UUID.randomUUID().toString() + "_" + safeFilename;

        try {

            File dest = new File(baseFolder, imgName);
            String canonicalPath = dest.getCanonicalPath();
            if (!canonicalPath.startsWith(baseFolder.getCanonicalPath())) {
                r.setResultCode(ResultCode.PARAM_IS_INVALID);
                r.simple().put("errdetail", "非法文件路径");
                return r;
            }

            image.transferTo(dest);

            url.append("/").append(imgName);

            r.setResultCode(ResultCode.SUCCESS);

            r.simple().put("url", url);

        } catch (IOException e) {
            logger.error("文件上传错误 , uri: {} , caused by: ", request.getRequestURI(), e);
            r.setResultCode(ResultCode.UPLOAD_ERROR);
        }

        return r;
    }
}
