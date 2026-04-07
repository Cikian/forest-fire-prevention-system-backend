package org.jeecg.modules.dji.mqtt;

import org.jeecg.modules.dji.common.BaseModel;
import org.jeecg.modules.dji.common.Common;
import org.jeecg.modules.dji.mqtt.events.TopicEventsRequest;
import org.jeecg.modules.dji.mqtt.events.TopicEventsResponse;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsRequest;
import org.jeecg.modules.dji.mqtt.requests.TopicRequestsResponse;
import org.jeecg.modules.dji.mqtt.state.TopicStateRequest;
import org.jeecg.modules.dji.mqtt.state.TopicStateResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author sean
 * @version 1.7
 * @date 2023/5/22
 */
@Aspect
@Component
public class MqttReplyHandler {

    @AfterReturning(value = "execution(public org.jeecg.modules.dji.mqtt.CommonTopicResponse+ org.jeecg.modules.dji.cloudapi.*.api.*.*(org.jeecg.modules.dji.mqtt.CommonTopicRequest+, org.springframework.messaging.MessageHeaders))", returning = "result")
    public Object validateReturnValue(JoinPoint point, CommonTopicResponse result) {
        if (Objects.isNull(result)) {
            return null;
        }
        CommonTopicRequest request = (CommonTopicRequest) point.getArgs()[0];
        result.setBid(request.getBid()).setTid(request.getTid()).setTimestamp(System.currentTimeMillis());
        if (result instanceof TopicEventsResponse) {
            fillEvents((TopicEventsResponse) result, (TopicEventsRequest) request);
        } else if (result instanceof TopicRequestsResponse) {
            validateRequests((TopicRequestsResponse) result, (TopicRequestsRequest) request);
        } else if (result instanceof TopicStateResponse) {
            fillState((TopicStateResponse) result, (TopicStateRequest) request);
        }
        return result;
    }

    private void fillEvents(TopicEventsResponse response, TopicEventsRequest request) {
        if (!request.isNeedReply()) {
            response.setData(null);
            return;
        }
        response.setMethod(request.getMethod()).setData(MqttReply.success());
    }

    private void validateRequests(TopicRequestsResponse response, TopicRequestsRequest request) {
        response.setMethod(request.getMethod());
        Object data = response.getData();
        if (data instanceof MqttReply) {
            MqttReply mqttData = (MqttReply) data;
            if (MqttReply.CODE_SUCCESS != mqttData.getResult()) {
                return;
            }
            data = mqttData.getOutput();
        }
        Common.validateModel((BaseModel) data);
    }

    private void fillState(TopicStateResponse response, TopicStateRequest request) {
        response.setData(request.isNeedReply() ? MqttReply.success() : null);
    }
}
