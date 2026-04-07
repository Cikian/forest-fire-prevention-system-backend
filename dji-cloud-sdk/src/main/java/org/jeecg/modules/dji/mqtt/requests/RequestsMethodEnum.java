package org.jeecg.modules.dji.mqtt.requests;

import org.jeecg.modules.dji.cloudapi.config.RequestsConfigRequest;
import org.jeecg.modules.dji.cloudapi.flightarea.FlightAreasGetRequest;
import org.jeecg.modules.dji.cloudapi.map.OfflineMapGetRequest;
import org.jeecg.modules.dji.cloudapi.media.StorageConfigGet;
import org.jeecg.modules.dji.cloudapi.organization.AirportBindStatusRequest;
import org.jeecg.modules.dji.cloudapi.organization.AirportOrganizationBindRequest;
import org.jeecg.modules.dji.cloudapi.organization.AirportOrganizationGetRequest;
import org.jeecg.modules.dji.cloudapi.wayline.FlighttaskResourceGetRequest;
import org.jeecg.modules.dji.mqtt.ChannelName;

import java.util.Arrays;

/**
 * @author sean
 * @version 1.0
 * @date 2022/5/25
 */
public enum RequestsMethodEnum {

    STORAGE_CONFIG_GET("storage_config_get", ChannelName.INBOUND_REQUESTS_STORAGE_CONFIG_GET, StorageConfigGet.class),

    AIRPORT_BIND_STATUS("airport_bind_status", ChannelName.INBOUND_REQUESTS_AIRPORT_BIND_STATUS, AirportBindStatusRequest.class),

    AIRPORT_ORGANIZATION_BIND("airport_organization_bind", ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_BIND, AirportOrganizationBindRequest.class),

    AIRPORT_ORGANIZATION_GET("airport_organization_get", ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_GET, AirportOrganizationGetRequest.class),

    FLIGHT_TASK_RESOURCE_GET("flighttask_resource_get", ChannelName.INBOUND_REQUESTS_FLIGHTTASK_RESOURCE_GET, FlighttaskResourceGetRequest.class),

    CONFIG("config", ChannelName.INBOUND_REQUESTS_CONFIG, RequestsConfigRequest.class),

    FLIGHT_AREAS_GET("flight_areas_get", ChannelName.INBOUND_REQUESTS_FLIGHT_AREAS_GET, FlightAreasGetRequest.class),

    OFFLINE_MAP_GET("offline_map_get", ChannelName.INBOUND_REQUESTS_OFFLINE_MAP_GET, OfflineMapGetRequest.class),

    UNKNOWN("", ChannelName.DEFAULT, Object.class);

    private final String method;

    private final String channelName;

    private final Class classType;

    RequestsMethodEnum(String method, String channelName, Class classType) {
        this.method = method;
        this.channelName = channelName;
        this.classType = classType;
    }

    public String getMethod() {
        return method;
    }

    public String getChannelName() {
        return channelName;
    }

    public Class getClassType() {
        return classType;
    }

    public static RequestsMethodEnum find(String method) {
        return Arrays.stream(RequestsMethodEnum.values())
                .filter(methodEnum -> methodEnum.method.equals(method))
                .findAny().orElse(UNKNOWN);
    }
}
