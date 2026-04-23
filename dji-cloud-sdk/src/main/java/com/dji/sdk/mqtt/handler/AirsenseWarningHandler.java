package com.dji.sdk.mqtt.handler;

import com.dji.sdk.cloudapi.airsense.AirsenseWarning;
import com.dji.sdk.mqtt.ChannelName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-04-23 10:22
 */
@Component
@Slf4j
public class AirsenseWarningHandler {

    /**
     * 订阅并处理 AirSense 预警消息
     * inputChannel 必须指向你在 MqttMessageChannel 中定义的 Bean 名称
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_AIRSENSE_WARNING)
    public void handleAirsenseWarning(Message<List<AirsenseWarning>> message) {
        List<AirsenseWarning> warnings = message.getPayload();

        log.info("=== 收到载人机预警 (AirSense) ===");
        for (AirsenseWarning warning : warnings) {
            log.warn("检测到周边载人机! ICAO: {}, 距离机场: {} 米, 海拔: {} 米, 预警等级: {}",
                    warning.getIcao(),
                    warning.getDistance(),
                    warning.getAltitude(),
                    warning.getWarningLevel());
        }
    }
}
