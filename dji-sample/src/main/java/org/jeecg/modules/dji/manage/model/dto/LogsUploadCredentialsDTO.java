package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.cloudapi.log.FileUploadStartParam;
import org.jeecg.modules.dji.cloudapi.storage.CredentialsToken;
import org.jeecg.modules.dji.cloudapi.storage.OssTypeEnum;
import org.jeecg.modules.dji.cloudapi.storage.StsCredentialsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogsUploadCredentialsDTO {

    private String bucket;

    private CredentialsToken credentials;

    private String endpoint;

    @JsonProperty("file_store_dir")
    private String objectKeyPrefix;

    private OssTypeEnum provider;

    @Builder.Default
    private String fileType = "text_log";

    private FileUploadStartParam params;

    private String region;

    public LogsUploadCredentialsDTO(StsCredentialsResponse sts) {
        this.bucket = sts.getBucket();
        long expire = sts.getCredentials().getExpire();
        sts.getCredentials().setExpire(System.currentTimeMillis() + (expire - 60) * 1000);
        this.credentials = sts.getCredentials();
        this.endpoint = sts.getEndpoint();
        this.objectKeyPrefix = sts.getObjectKeyPrefix();
        this.provider = sts.getProvider();
        this.region = sts.getRegion();
    }
}
