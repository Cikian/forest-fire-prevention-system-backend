package com.dji.sample.service;


import com.dji.sdk.cloudapi.airsense.AirsenseWarning;
import com.dji.sdk.cloudapi.airsense.api.AbstractAirsenseService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-04-23 14:04
 */
@Slf4j
@Service
public class AirsenseService extends AbstractAirsenseService {

    @Override
    public TopicEventsResponse<MqttReply> airsenseWarning(TopicEventsRequest<List<AirsenseWarning>> request, MessageHeaders headers) {
        List<AirsenseWarning> warnings = request.getData();

        log.info("=== 收到载人机预警 (AirSense) ===");
        for (AirsenseWarning warning : warnings) {
            log.warn("检测到周边载人机! ICAO: {}, 距离机场: {} 米, 海拔: {} 米, 预警等级: {}",
                    warning.getIcao(),
                    warning.getDistance(),
                    warning.getAltitude(),
                    warning.getWarningLevel());
        }
        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }
}
