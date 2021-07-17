package com.blog.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * alioss
 *
 * @author Leeqingyi
 * @time 2021/7/12
 */

public interface AliOssService {
    void createBucket();
    String upload(MultipartFile file);
    void download(String fileName)throws IOException;
    void listFile();
    void deleteFile(String fileName);
}
