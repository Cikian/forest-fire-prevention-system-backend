package org.jeecg.modules.dji.map.service.impl;

import org.jeecg.modules.dji.common.error.CommonErrorEnum;
import org.jeecg.modules.dji.manage.model.dto.DeviceDTO;
import org.jeecg.modules.dji.manage.model.param.DeviceQueryParam;
import org.jeecg.modules.dji.manage.service.IDeviceRedisService;
import org.jeecg.modules.dji.manage.service.IDeviceService;
import org.jeecg.modules.dji.map.model.dto.DeviceDataStatusDTO;
import org.jeecg.modules.dji.map.model.dto.DeviceFlightAreaDTO;
import org.jeecg.modules.dji.map.service.IDeviceDataService;
import org.jeecg.modules.dji.map.service.IDeviceFlightAreaService;
import org.jeecg.modules.dji.cloudapi.device.DeviceDomainEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
@Service
public class DeviceDataServiceImpl implements IDeviceDataService {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Autowired
    private IDeviceFlightAreaService deviceFlightAreaService;

    @Override
    public List<DeviceDataStatusDTO> getDevicesDataStatus(String workspaceId) {
        List<DeviceDTO> devices = deviceService.getDevicesByParams(DeviceQueryParam.builder()
                .domains(List.of(DeviceDomainEnum.DOCK.getDomain())).workspaceId(workspaceId).build());
        if (CollectionUtils.isEmpty(devices)) {
            throw new RuntimeException(CommonErrorEnum.ILLEGAL_ARGUMENT.getMessage());
        }
        return devices.stream().map(device -> DeviceDataStatusDTO.builder()
                        .deviceName(device.getDeviceName())
                        .deviceSn(device.getDeviceSn())
                        .nickname(device.getNickname())
                        .online(deviceRedisService.checkDeviceOnline(device.getDeviceSn()))
                        .flightAreaStatus(getDeviceStatus(workspaceId, device.getDeviceSn()).orElse(null))
                        .build())
                .filter(device -> Objects.nonNull(device.getFlightAreaStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DeviceFlightAreaDTO> getDeviceStatus(String workspaceId, String deviceSn) {
        return deviceFlightAreaService.getDeviceFlightAreaByDevice(workspaceId, deviceSn)
                .map(area -> DeviceFlightAreaDTO.builder().syncStatus(area.getSyncStatus()).syncCode(area.getSyncCode()).syncMsg(area.getSyncMsg()).build());
    }
}
