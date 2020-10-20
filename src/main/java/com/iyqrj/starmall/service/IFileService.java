package com.iyqrj.starmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by UsingLove on 2017/8/17.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
