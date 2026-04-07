package org.jeecg.modules.dji.control.model.dto;

import org.jeecg.modules.dji.common.util.SpringBeanUtilsTest;
import org.jeecg.modules.dji.control.service.impl.RemoteDebugHandler;
import org.jeecg.modules.dji.manage.service.IDeviceService;
import org.jeecg.modules.dji.cloudapi.device.DockModeCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sean
 * @version 1.4
 * @date 2023/4/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoteDebugOpenState extends RemoteDebugHandler {

    @Override
    public boolean canPublish(String sn) {
        IDeviceService deviceService = SpringBeanUtilsTest.getBean(IDeviceService.class);
        DockModeCodeEnum dockMode = deviceService.getDockMode(sn);
        return DockModeCodeEnum.IDLE == dockMode;
    }
}
