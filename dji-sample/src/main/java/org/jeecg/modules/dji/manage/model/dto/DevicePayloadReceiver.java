package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.cloudapi.device.ControlSourceEnum;
import org.jeecg.modules.dji.cloudapi.device.PayloadIndex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevicePayloadReceiver {

    private String deviceSn;

    private ControlSourceEnum controlSource;

    private PayloadIndex payloadIndex;

    private String sn;

}