package org.jeecg.modules.dji.control.service.impl;

import org.jeecg.modules.dji.control.model.param.DronePayloadParam;
import org.jeecg.modules.dji.cloudapi.device.CameraStateEnum;

import java.util.Objects;

/**
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraModeSwitchImpl extends PayloadCommandsHandler {

    public CameraModeSwitchImpl(DronePayloadParam param) {
        super(param);
    }

    @Override
    public boolean valid() {
        return Objects.nonNull(param.getCameraMode());
    }

    @Override
    public boolean canPublish(String deviceSn) {
        super.canPublish(deviceSn);
        return param.getCameraMode() != osdCamera.getCameraMode()
                && CameraStateEnum.IDLE == osdCamera.getPhotoState()
                && CameraStateEnum.IDLE == osdCamera.getRecordingState();
    }
}
