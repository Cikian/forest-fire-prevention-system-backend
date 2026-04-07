package org.jeecg.modules.dji.component;

import org.jeecg.modules.dji.component.redis.RedisConst;
import org.jeecg.modules.dji.component.redis.RedisOpsUtils;
import org.jeecg.modules.dji.manage.model.dto.DeviceDTO;
import org.jeecg.modules.dji.manage.service.IDeviceService;
import org.jeecg.modules.dji.cloudapi.device.DeviceDomainEnum;
import org.jeecg.modules.dji.mqtt.IMqttTopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author sean.zhou
 * @date 2021/11/24
 * @version 0.1
 */
@Component
@Slf4j
public class GlobalScheduleService {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IMqttTopicService topicService;

    @Autowired
    private ObjectMapper mapper;
    /**
     * Check the status of the devices every 30 seconds. It is recommended to use cache.
     */
    @Scheduled(initialDelay = 10, fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    private void deviceStatusListen() {
        int start = RedisConst.DEVICE_ONLINE_PREFIX.length();

        RedisOpsUtils.getAllKeys(RedisConst.DEVICE_ONLINE_PREFIX + "*").forEach(key -> {
            long expire = RedisOpsUtils.getExpire(key);
            if (expire <= 30) {
                DeviceDTO device = (DeviceDTO) RedisOpsUtils.get(key);
                if (null == device) {
                    return;
                }
                if (DeviceDomainEnum.DRONE == device.getDomain()) {
                    deviceService.subDeviceOffline(key.substring(start));
                } else {
                    deviceService.gatewayOffline(key.substring(start));
                }
                RedisOpsUtils.del(key);
            }
        });

        log.info("Subscriptions: {}", Arrays.toString(topicService.getSubscribedTopic()));
    }

}