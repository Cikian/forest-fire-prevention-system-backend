package org.jeecg.modules.dji.cloudapi.config.api;

import org.jeecg.modules.dji.cloudapi.config.ProductConfigResponse;
import org.jeecg.modules.dji.cloudapi.config.RequestsConfigRequest;
import org.jeecg.modules.dji.mqtt.ChannelName;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsRequest;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public abstract class AbstractConfigService {

    /**
     * Inform of file uploading progress
     * @param request  data
     * @param headers   The headers for a {@link Message}.
     * @return requests_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_CONFIG, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<ProductConfigResponse> requestsConfig(TopicRequestsRequest<RequestsConfigRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("requestsConfig not implemented");
    }

}
