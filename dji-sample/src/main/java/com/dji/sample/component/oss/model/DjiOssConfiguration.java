package com.dji.sample.component.oss.model;

import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@ConfigurationProperties(prefix = "oss")
@Component
public class DjiOssConfiguration {

    /**
     * @see OssTypeEnum
     */
    public static OssTypeEnum provider;

    /**
     * Whether to use the object storage service.
     */
    public static boolean enable;

    /**
     * The protocol needs to be included at the beginning of the address.
     */
    public static String endpoint;

    public static String accessKey;

    public static String secretKey;

    public static String region;

    public static Long expire;

    public static String roleSessionName;

    public static String roleArn;

    public static String bucket;

    public static String objectDirPrefix;

    public void setProvider(OssTypeEnum provider) {
        DjiOssConfiguration.provider = provider;
    }

    public void setEnable(boolean enable) {
        DjiOssConfiguration.enable = enable;
    }

    public void setEndpoint(String endpoint) {
        DjiOssConfiguration.endpoint = endpoint;
    }

    public void setAccessKey(String accessKey) {
        DjiOssConfiguration.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        DjiOssConfiguration.secretKey = secretKey;
    }

    public void setRegion(String region) {
        DjiOssConfiguration.region = region;
    }

    public void setExpire(Long expire) {
        DjiOssConfiguration.expire = expire;
    }

    public void setRoleSessionName(String roleSessionName) {
        DjiOssConfiguration.roleSessionName = roleSessionName;
    }

    public void setRoleArn(String roleArn) {
        DjiOssConfiguration.roleArn = roleArn;
    }

    public void setBucket(String bucket) {
        DjiOssConfiguration.bucket = bucket;
    }

    public void setObjectDirPrefix(String objectDirPrefix) {
        DjiOssConfiguration.objectDirPrefix = objectDirPrefix;
    }
}



