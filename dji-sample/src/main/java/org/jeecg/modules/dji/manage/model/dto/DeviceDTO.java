package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.manage.model.enums.DeviceFirmwareStatusEnum;
import org.jeecg.modules.dji.cloudapi.device.ControlSourceEnum;
import org.jeecg.modules.dji.cloudapi.device.DeviceDomainEnum;
import org.jeecg.modules.dji.cloudapi.device.DeviceSubTypeEnum;
import org.jeecg.modules.dji.cloudapi.device.DeviceTypeEnum;
import org.jeecg.modules.dji.cloudapi.tsa.DeviceIconUrl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTO {

    private String deviceSn;

    private String deviceName;

    private String workspaceId;

    private ControlSourceEnum controlSource;

    private String deviceDesc;

    private String childDeviceSn;

    private DeviceDomainEnum domain;

    private DeviceTypeEnum type;

    private DeviceSubTypeEnum subType;

    private List<DevicePayloadDTO> payloadsList;

    private DeviceIconUrl iconUrl;

    private Boolean status;

    private Boolean boundStatus;

    private LocalDateTime loginTime;

    private LocalDateTime boundTime;

    private String nickname;

    private String userId;

    private String firmwareVersion;

    private String workspaceName;

    private DeviceDTO children;

    private DeviceFirmwareStatusEnum firmwareStatus;

    private Integer firmwareProgress;

    private String parentSn;

    private String thingVersion;
}