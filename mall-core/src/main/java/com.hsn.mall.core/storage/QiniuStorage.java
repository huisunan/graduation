package com.hsn.mall.core.storage;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author huisunan
 * @date 2020/1/17 14:34
 */
@Slf4j
@Data
public class QiniuStorage implements Storage {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String link;
    private String bucketName;
    private Auth auth;
    private UploadManager uploadManager;
    private BucketManager bucketManager;

    /**
     * 七牛云OSS对象存储简单上传实现
     */
    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        if (uploadManager == null) {
            if (auth == null) {
                auth = Auth.create(accessKey, secretKey);
            }
            uploadManager = new UploadManager(new Configuration());
        }

        try {
            String upToken = auth.uploadToken(bucketName);
            uploadManager.put(inputStream, keyName, upToken, null, contentType);
        } catch (QiniuException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String keyName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            URL url = new URL(generateUrl(keyName));
            Resource resource = new UrlResource(url);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(String keyName) {
        if (bucketManager == null) {
            if (auth == null) {
                auth = Auth.create(accessKey, secretKey);
            }
            bucketManager = new BucketManager(auth, new Configuration());
        }
        try {
            bucketManager.delete(bucketName, keyName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return link + keyName;
    }
}
