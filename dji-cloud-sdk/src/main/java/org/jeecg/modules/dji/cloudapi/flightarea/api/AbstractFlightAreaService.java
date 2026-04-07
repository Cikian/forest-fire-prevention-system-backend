package org.jeecg.modules.dji.cloudapi.flightarea.api;

import org.jeecg.modules.dji.annotations.CloudSDKVersion;
import org.jeecg.modules.dji.cloudapi.flightarea.*;
import org.jeecg.modules.dji.config.version.CloudSDKVersionEnum;
import org.jeecg.modules.dji.config.version.GatewayManager;
import org.jeecg.modules.dji.config.version.GatewayTypeEnum;
import org.jeecg.modules.dji.mqtt.ChannelName;
import org.jeecg.modules.dji.mqtt.MqttReply;
import org.jeecg.modules.dji.mqtt.events.TopicEventsRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsResponse;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsRequest;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsResponse;
import org.jeecg.modules.dji.mqtt.services.ServicesPublish;
import org.jeecg.modules.dji.mqtt.services.ServicesReplyData;
import org.jeecg.modules.dji.mqtt.services.TopicServicesResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public abstract class AbstractFlightAreaService {

    @Resource
    private ServicesPublish servicesPublish;

    /**
     * Update command
     * @param gateway   gateway device
     * @return  services_reply
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC, include = {GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2})
    public TopicServicesResponse<ServicesReplyData> flightAreasUpdate(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                FlightAreaMethodEnum.FLIGHT_AREAS_UPDATE.getMethod());
    }

    /**
     * Progress of custom flight area file synchronize from the Cloud to the Device. Used for further defining flight area.
     * @param request  data
     * @param headers  The headers for a {@link Message}.
     * @return events_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FLIGHT_AREAS_SYNC_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> flightAreasSyncProgress(TopicEventsRequest<FlightAreasSyncProgress> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flightAreasSyncProgress not implemented");
    }

    /**
     * Push warning information
     * @param request  data
     * @param headers  The headers for a {@link Message}.
     * @return events_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FLIGHT_AREAS_DRONE_LOCATION, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> flightAreasDroneLocation(TopicEventsRequest<FlightAreasDroneLocation> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flightAreasDroneLocation not implemented");
    }

    /**
     * Get custom flight area file
     * @param request  data
     * @param headers  The headers for a {@link Message}.
     * @return requests_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_FLIGHT_AREAS_GET, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicRequestsResponse<MqttReply<FlightAreasGetResponse>> flightAreasGet(TopicRequestsRequest<FlightAreasGetRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flightAreasGet not implemented");
    }
}
