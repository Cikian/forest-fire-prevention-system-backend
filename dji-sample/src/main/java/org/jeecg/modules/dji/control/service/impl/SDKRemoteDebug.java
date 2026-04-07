package org.jeecg.modules.dji.control.service.impl;

import org.jeecg.modules.dji.component.mqtt.model.EventsReceiver;
import org.jeecg.modules.dji.component.websocket.service.IWebSocketMessageService;
import org.jeecg.modules.dji.manage.model.dto.DeviceDTO;
import org.jeecg.modules.dji.manage.model.enums.UserTypeEnum;
import org.jeecg.modules.dji.manage.service.IDeviceRedisService;
import org.jeecg.modules.dji.cloudapi.debug.RemoteDebugProgress;
import org.jeecg.modules.dji.cloudapi.debug.api.AbstractDebugService;
import org.jeecg.modules.dji.mqtt.MqttReply;
import org.jeecg.modules.dji.mqtt.events.EventsDataRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sean
 * @version 1.7
 * @date 2023/7/4
 */
@Service
@Slf4j
public class SDKRemoteDebug extends AbstractDebugService {

    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Override
    public TopicEventsResponse<MqttReply> remoteDebugProgress(TopicEventsRequest<EventsDataRequest<RemoteDebugProgress>> request, MessageHeaders headers) {
        String sn = request.getGateway();

        EventsReceiver<RemoteDebugProgress> eventsReceiver = new EventsReceiver<RemoteDebugProgress>()
                .setOutput(request.getData().getOutput()).setResult(request.getData().getResult());
        eventsReceiver.setBid(request.getBid());
        eventsReceiver.setSn(sn);

        log.info("SN: {}, {} ===> Control progress: {}", sn, request.getMethod(), eventsReceiver.getOutput().getProgress());

        if (!eventsReceiver.getResult().isSuccess()) {
            log.error("SN: {}, {} ===> Error: {}", sn, request.getMethod(), eventsReceiver.getResult());
        }

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(sn);

        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("The device is offline.");
        }

        DeviceDTO device = deviceOpt.get();
        webSocketMessageService.sendBatch(device.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                request.getMethod(), eventsReceiver);

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }
}
