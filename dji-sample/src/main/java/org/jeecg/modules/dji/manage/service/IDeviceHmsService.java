package org.jeecg.modules.dji.manage.service;

import org.jeecg.modules.dji.manage.model.dto.DeviceHmsDTO;
import org.jeecg.modules.dji.manage.model.param.DeviceHmsQueryParam;
import org.jeecg.modules.dji.common.PaginationData;

/**
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
public interface IDeviceHmsService {

    /**
     * Query hms data by paging according to query parameters.
     * @param param
     * @return
     */
    PaginationData<DeviceHmsDTO> getDeviceHmsByParam(DeviceHmsQueryParam param);

    /**
     * Read message handling.
     * @param deviceSn
     */
    void updateUnreadHms(String deviceSn);
}
