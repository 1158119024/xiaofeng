package com.xiaofeng.blogs.file.service;

import com.qcloud.cos.model.PutObjectResult;
import com.xiaofeng.blogs.file.dto.ImageFileDto;
import com.xiaofeng.blogs.file.entity.ImageFileEntity;
import com.xiaofeng.blogs.file.repository.FileRepository;
import com.xiaofeng.config.Constant;
import com.xiaofeng.config.CosAndOssUser;
import com.xiaofeng.utils.CosFileConfig;
import com.xiaofeng.utils.FileCompress;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/7 21:54
 * @Description: 文件操作
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileRepository fileRepository;

    /**
     *
     * @param file：文件
     * @param quality：清晰度
     * @param userId
     * @return
     * @throws IOException
     */
    @Override
    public ImageFileDto fileUpload(MultipartFile file, Float quality, Integer userId) throws IOException {
        InputStream inputStream = file.getInputStream();
        if( inputStream.available() == 0 ){
            return null;
        }
        String fileName = file.getOriginalFilename();
        // 拼接临时文件地址，压缩文件
        String tempPath = Constant.fileTempPath + fileName;
        File targetFile = new File(tempPath);
        // 压缩
        FileCompress.fileCompress(inputStream, targetFile, quality);

        // 拼接上传cos所需要的key
        StringBuffer cosKeySb = new StringBuffer();
        String cosKey = cosKeySb.append(CosAndOssUser.keyPrefix).append(userId).append("/").append(fileName).toString();
        // 上传cos
        PutObjectResult cosPutResult = CosFileConfig.uploadFile(cosKey, targetFile);

        fileRepository.delete(userId, cosKey);
        ImageFileEntity imageFileEntity = new ImageFileEntity();
        imageFileEntity.setUserId(userId);
        imageFileEntity.setETag(cosPutResult.getETag());
//        imageFileEntity.setDomain(CosAndOssUser.domain);
        imageFileEntity.setFileName(fileName);
        imageFileEntity.setCosKey(cosKey);
        imageFileEntity.setQuality(quality);
        imageFileEntity.setSize(targetFile.length());
        imageFileEntity.setType(fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()));
        fileRepository.add(imageFileEntity);

        String imageUrl = CosAndOssUser.domain + cosKey;
        ImageFileDto imageFileDto = new ImageFileDto();
        imageFileDto.setImageUrl(imageUrl).setSize(targetFile.length()).setFileName(fileName);
        return imageFileDto;
    }
}
