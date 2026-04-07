package org.jeecg.modules.dji.control.service.impl;

import org.jeecg.modules.dji.common.util.SpringBeanUtilsTest;
import org.jeecg.modules.dji.manage.service.IDeviceService;
import org.jeecg.modules.dji.cloudapi.device.DockModeCodeEnum;

/**
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class RemoteDebugHandler {

    public boolean valid() {
        return true;
    }

    public boolean canPublish(String sn) {
        IDeviceService deviceService = SpringBeanUtilsTest.getBean(IDeviceService.class);
        DockModeCodeEnum dockMode = deviceService.getDockMode(sn);
        return DockModeCodeEnum.REMOTE_DEBUGGING == dockMode;
    }
}
