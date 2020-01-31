package com.hsn.mall.core.storage;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author huisunan
 * @date 2020/1/17 15:40
 */
@Data
public class StorageProvider {
    private String active;
    private Storage storage;

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public MallStorage store(InputStream inputStream, long contentLength, String contentType, String fileName){
        String key = generateKey(fileName);
        storage.store(inputStream, contentLength, contentType, key);

        String url = generateUrl(key);
        MallStorage storageInfo = new MallStorage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) contentLength);
        storageInfo.setType(contentType);
        storageInfo.setKey(key);
        storageInfo.setUrl(url);

        return storageInfo;
    }

    private String generateKey(String fileName) {
        int index = fileName.lastIndexOf('.');
        String suffix = fileName.substring(index);
        return UUID.randomUUID().toString().replace("-","") + suffix;
    }


    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    public Path load(String keyName) {
        return storage.load(keyName);
    }

    public Resource loadAsResource(String keyName) {
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }

    @Data
    public static class MallStorage{
        private String name;
        private Integer size;
        private String type;
        private String key;
        private String url;
    }

}
