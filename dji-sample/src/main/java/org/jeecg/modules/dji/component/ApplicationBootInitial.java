package org.jeecg.modules.dji.component;

import org.jeecg.modules.dji.component.redis.RedisConst;
import org.jeecg.modules.dji.component.redis.RedisOpsUtils;
import org.jeecg.modules.dji.manage.model.dto.DeviceDTO;
import org.jeecg.modules.dji.manage.service.IDeviceRedisService;
import org.jeecg.modules.dji.manage.service.IDeviceService;
import org.jeecg.modules.dji.cloudapi.device.DeviceDomainEnum;
import org.jeecg.modules.dji.common.SDKManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author sean.zhou
 * @date 2021/11/24
 * @version 0.1
 */
@Component
public class ApplicationBootInitial implements CommandLineRunner {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * Subscribe to the devices that exist in the redis when the program starts,
     * to prevent the data from being different from the pilot side due to program interruptions.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        int start = RedisConst.DEVICE_ONLINE_PREFIX.length();

        RedisOpsUtils.getAllKeys(RedisConst.DEVICE_ONLINE_PREFIX + "*")
                .stream()
                .map(key -> key.substring(start))
                .map(deviceRedisService::getDeviceOnline)
                .map(Optional::get)
                .filter(device -> DeviceDomainEnum.DRONE != device.getDomain())
                .forEach(device -> deviceService.subDeviceOnlineSubscribeTopic(
                        SDKManager.registerDevice(device.getDeviceSn(), device.getChildDeviceSn(), device.getDomain(),
                                device.getType(), device.getSubType(), device.getThingVersion(),
                                deviceRedisService.getDeviceOnline(device.getChildDeviceSn()).map(DeviceDTO::getThingVersion).orElse(null))));

    }
}