package com.hsn.mall.core.storage.config;

import com.hsn.mall.core.storage.LocalStorage;
import com.hsn.mall.core.storage.QiniuStorage;
import com.hsn.mall.core.storage.StorageProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huisunan
 * @date 2020/1/17 14:33
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {
    private final StorageProperties properties;

    private static final String LOCAL = "local";

    private static final String QINIU = "qiniu";

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StorageProvider storageProvider() {
        StorageProvider storageService = new StorageProvider();
        String active = this.properties.getActive();
        storageService.setActive(active);
        switch (active){
            case LOCAL:
                storageService.setStorage(localStorage());
                break;
            case QINIU:
                storageService.setStorage(qiniuStorage());
                break;
            default:
                throw new RuntimeException("当前存储模式 " + active + " 不支持");
        }
        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }

    @Bean
    public QiniuStorage qiniuStorage() {
        QiniuStorage qiniuStorage = new QiniuStorage();
        StorageProperties.Qiniu qiniu = this.properties.getQiniu();
        qiniuStorage.setAccessKey(qiniu.getAccessKey());
        qiniuStorage.setSecretKey(qiniu.getSecretKey());
        qiniuStorage.setBucketName(qiniu.getBucketName());
        qiniuStorage.setEndpoint(qiniu.getEndpoint());
        return qiniuStorage;
    }
}
