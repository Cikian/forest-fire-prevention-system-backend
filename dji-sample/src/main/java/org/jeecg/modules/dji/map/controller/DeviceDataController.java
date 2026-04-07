package org.jeecg.modules.dji.map.controller;

import org.jeecg.modules.dji.map.model.dto.DeviceDataStatusDTO;
import org.jeecg.modules.dji.map.service.IDeviceDataService;
import org.jeecg.modules.dji.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */

@RestController
@RequestMapping("${url.map.prefix}${url.map.version}/workspaces")
public class DeviceDataController {

    @Autowired
    private IDeviceDataService deviceDataService;

    @GetMapping("/{workspace_id}/device-status")
    public HttpResultResponse<List<DeviceDataStatusDTO>> getDeviceFlightAreaStatus(@PathVariable(name = "workspace_id") String workspaceId) {
        return HttpResultResponse.success(deviceDataService.getDevicesDataStatus(workspaceId));
    }
}
