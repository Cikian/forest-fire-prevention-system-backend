package org.jeecg.modules.dji.cloudapi.airsense.api;

import org.jeecg.modules.dji.annotations.CloudSDKVersion;
import org.jeecg.modules.dji.cloudapi.airsense.AirsenseWarning;
import org.jeecg.modules.dji.config.version.CloudSDKVersionEnum;
import org.jeecg.modules.dji.mqtt.ChannelName;
import org.jeecg.modules.dji.mqtt.MqttReply;
import org.jeecg.modules.dji.mqtt.events.TopicEventsRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.List;

/**
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public abstract class AbstractAirsenseService {


    /**
     * cloud-custom data transmit from psdk
     * @param request  data
     * @param headers  The headers for a {@link Message}.
     * @return events_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_AIRSENSE_WARNING, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> airsenseWarning(TopicEventsRequest<List<AirsenseWarning>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("airsenseWarning not implemented");
    }

}
