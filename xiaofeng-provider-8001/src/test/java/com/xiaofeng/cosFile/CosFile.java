package com.xiaofeng.cosFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.xiaofeng.config.CosAndOssUser;
import com.xiaofeng.utils.CosFileConfig;
import com.xiaofeng.utils.FileCompress;
import com.xiaofeng.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 14:45
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CosFile {

    @Test
    public void cosFile(){
//        String upFilePath = "C:\\Users\\root\\Desktop\\fengye.jpg";
//        String upFileKey = "喂你好吗.jpg";
//        String key = UploadFile(upFilePath, upFileKey);
//        System.out.println(key);
//
//        String downFilePath = "C:\\Users\\root\\Desktop\\fengye123.jpg";
//        downloadFile(downFilePath, key);

        deleteFile("upload/1/background.png");
    }

    public COSClient getUserMsg(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(CosAndOssUser.accessKey, CosAndOssUser.secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(CosAndOssUser.regionName));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    /**
     * 上传文件
     */
    public String UploadFile(String path, String key){
        COSClient cosClient = getUserMsg();
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = new File(path);
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        PutObjectRequest putObjectRequest = new PutObjectRequest(CosAndOssUser.bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        return key;
    }

    /**
     * 下载文件
     */
    public void downloadFile(String path, String key){
        COSClient cosClient = getUserMsg();
        // 指定要下载到的本地路径
        File downFile = new File(path);
        // 指定要下载的文件所在的 bucket 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(CosAndOssUser.bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
        cosClient.shutdown();
    }

    /**
     * 删除
     * @throws IOException
     */
    public void deleteFile(String key){
        COSClient cosClient = getUserMsg();
        // 指定要删除的文件所在的 bucket 和对象键
        cosClient.deleteObject(CosAndOssUser.bucketName, key);
        cosClient.shutdown();
    }

    @Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\root\\Desktop\\fengye.jpg");

        File targetFile = new File("C:\\Users\\root\\Desktop\\fengye9999.jpg");

        FileCompress.fileCompress(file, targetFile, FileCompress.qualityBad);

        PutObjectResult putObjectResult = CosFileConfig.uploadFile("123/" + UUIDUtils.randomUUID() + ".jpg", targetFile);
        System.out.println(putObjectResult);

    }
}
