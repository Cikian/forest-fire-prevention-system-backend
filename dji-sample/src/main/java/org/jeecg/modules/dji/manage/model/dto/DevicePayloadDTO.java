package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.cloudapi.device.ControlSourceEnum;
import org.jeecg.modules.dji.cloudapi.device.PayloadIndex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevicePayloadDTO {

    private String payloadSn;

    private String payloadName;

    private Integer index;

    private String payloadDesc;

    private ControlSourceEnum controlSource;

    private PayloadIndex payloadIndex;
}