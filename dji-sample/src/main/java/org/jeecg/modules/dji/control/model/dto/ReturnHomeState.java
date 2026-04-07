package org.jeecg.modules.dji.control.model.dto;

import org.jeecg.modules.dji.common.util.SpringBeanUtilsTest;
import org.jeecg.modules.dji.control.service.impl.RemoteDebugHandler;
import org.jeecg.modules.dji.manage.model.dto.DeviceDTO;
import org.jeecg.modules.dji.manage.service.IDeviceRedisService;
import org.jeecg.modules.dji.cloudapi.device.DroneModeCodeEnum;
import org.jeecg.modules.dji.cloudapi.device.OsdDockDrone;

/**
 * @author sean
 * @version 1.4
 * @date 2023/4/19
 */

public class ReturnHomeState extends RemoteDebugHandler {

    @Override
    public boolean canPublish(String sn) {
        IDeviceRedisService deviceRedisService = SpringBeanUtilsTest.getBean(IDeviceRedisService.class);
        return deviceRedisService.getDeviceOnline(sn)
                .map(DeviceDTO::getChildDeviceSn)
                .flatMap(deviceSn -> deviceRedisService.getDeviceOsd(deviceSn, OsdDockDrone.class))
                .map(osd -> osd.getElevation() > 0 && modeCodeCanReturnHome(osd.getModeCode()))
                .orElse(false);
    }

    private boolean modeCodeCanReturnHome(DroneModeCodeEnum modeCode) {
        return DroneModeCodeEnum.TAKEOFF_FINISHED == modeCode || DroneModeCodeEnum.TAKEOFF_AUTO == modeCode
                || DroneModeCodeEnum.WAYLINE == modeCode || DroneModeCodeEnum.PANORAMIC_SHOT == modeCode
                || DroneModeCodeEnum.ACTIVE_TRACK == modeCode || DroneModeCodeEnum.APAS == modeCode
                || DroneModeCodeEnum.VIRTUAL_JOYSTICK == modeCode || DroneModeCodeEnum.MANUAL == modeCode;
    }
}
