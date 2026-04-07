package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.cloudapi.device.VideoId;
import org.jeecg.modules.dji.cloudapi.livestream.LensChangeVideoTypeEnum;
import org.jeecg.modules.dji.cloudapi.livestream.UrlTypeEnum;
import org.jeecg.modules.dji.cloudapi.livestream.VideoQualityEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Receive live parameters.
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
@Data
public class LiveTypeDTO {

    @JsonProperty("url_type")
    private UrlTypeEnum urlType;

    @JsonProperty("video_id")
    private VideoId videoId;

    @JsonProperty("video_quality")
    private VideoQualityEnum videoQuality;

    private LensChangeVideoTypeEnum videoType;

}