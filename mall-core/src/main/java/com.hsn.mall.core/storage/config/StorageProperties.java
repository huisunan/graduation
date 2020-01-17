package com.hsn.mall.core.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huisunan
 * @date 2020/1/17 14:27
 */
@Data
@ConfigurationProperties(prefix = "mall.storage")
public class StorageProperties {
    private String active;
    private Local local;
    private Qiniu qiniu;

    @Data
    public static class Qiniu {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private String bucketName;
    }

    @Data
    public static class Local {
        private String address;
        private String storagePath;
    }
}
