package com.sorrowphage.czp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: SorrowPhage
 * @date: 2023/11/1
 */
@Component
public class FileUploadUtil {

    @Value("${file.avatar.base}")
    private  String basePath;

    @Value("${file.avatar.serve}")
    private  String servePath;


    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    public  String uploadAvatar(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newFile = uuid + originalFilename;
        File image = new File(basePath, newFile);
        file.transferTo(image);
        return servePath + newFile;
    }

    /**
     * 删除图片
     * @param filePath
     * @return
     */
    public boolean deleteAvatar(String filePath) {
        if (filePath == null) {
            return true;
        }
        String deleteUrl = filePath.replace(servePath, basePath);
        File file = new File(deleteUrl);
        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }
    }



}
