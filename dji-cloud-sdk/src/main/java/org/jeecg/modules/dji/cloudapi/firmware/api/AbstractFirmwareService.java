package org.jeecg.modules.dji.cloudapi.firmware.api;

import org.jeecg.modules.dji.cloudapi.firmware.FirmwareMethodEnum;
import org.jeecg.modules.dji.cloudapi.firmware.OtaCreateRequest;
import org.jeecg.modules.dji.cloudapi.firmware.OtaCreateResponse;
import org.jeecg.modules.dji.cloudapi.firmware.OtaProgress;
import org.jeecg.modules.dji.config.version.GatewayManager;
import org.jeecg.modules.dji.mqtt.ChannelName;
import org.jeecg.modules.dji.mqtt.MqttReply;
import org.jeecg.modules.dji.mqtt.events.EventsDataRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsResponse;
import org.jeecg.modules.dji.mqtt.services.ServicesPublish;
import org.jeecg.modules.dji.mqtt.services.ServicesReplyData;
import org.jeecg.modules.dji.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/28
 */
public abstract class AbstractFirmwareService {

    @Resource
    private ServicesPublish servicesPublish;

    /**
     * Firmware upgrade progress
     * @param request  data
     * @param headers   The headers for a {@link Message}.
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_OTA_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> otaProgress(TopicEventsRequest<EventsDataRequest<OtaProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("otaProgress not implemented");
    }

    /**
     * Firmware upgrade
     * @param gateway
     * @param request   data
     * @return  services_reply
     */
    public TopicServicesResponse<ServicesReplyData<OtaCreateResponse>> otaCreate(GatewayManager gateway, OtaCreateRequest request) {
        return servicesPublish.publish(
                new TypeReference<OtaCreateResponse>() {},
                gateway.getGatewaySn(),
                FirmwareMethodEnum.OTA_CREATE.getMethod(),
                request);
    }

}
