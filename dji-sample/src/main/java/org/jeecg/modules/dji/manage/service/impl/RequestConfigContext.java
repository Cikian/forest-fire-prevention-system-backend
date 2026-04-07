package org.jeecg.modules.dji.manage.service.impl;

import org.jeecg.modules.dji.common.error.CommonErrorEnum;
import org.jeecg.modules.dji.common.util.SpringBeanUtilsTest;
import org.jeecg.modules.dji.manage.model.dto.ProductConfigDTO;
import org.jeecg.modules.dji.manage.model.enums.CustomizeConfigScopeEnum;
import org.jeecg.modules.dji.cloudapi.config.ProductConfigResponse;
import org.jeecg.modules.dji.cloudapi.config.RequestsConfigRequest;
import org.jeecg.modules.dji.cloudapi.config.api.AbstractConfigService;
import org.jeecg.modules.dji.mqtt.MqttReply;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsRequest;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsResponse;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Service
public class RequestConfigContext extends AbstractConfigService {

    @Override
    public TopicRequestsResponse<ProductConfigResponse> requestsConfig(TopicRequestsRequest<RequestsConfigRequest> request, MessageHeaders headers) {
        RequestsConfigRequest configReceiver = request.getData();
        Optional<CustomizeConfigScopeEnum> scopeEnumOpt = CustomizeConfigScopeEnum.find(configReceiver.getConfigScope().getScope());
        if (scopeEnumOpt.isEmpty()) {
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.ILLEGAL_ARGUMENT));
        }

        ProductConfigDTO config = (ProductConfigDTO) SpringBeanUtilsTest.getBean(scopeEnumOpt.get().getClazz()).getConfig();
        return new TopicRequestsResponse<ProductConfigResponse>().setData(
                new ProductConfigResponse()
                        .setNtpServerHost(config.getNtpServerHost())
                        .setAppId(config.getAppId())
                        .setAppKey(config.getAppKey())
                        .setAppLicense(config.getAppLicense()));
    }
}
