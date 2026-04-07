package org.jeecg.modules.dji.mqtt.drc;

import org.jeecg.modules.dji.config.version.GatewayManager;
import org.jeecg.modules.dji.mqtt.IMqttTopicService;
import org.jeecg.modules.dji.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class DrcUpSubscribe {

    @Resource
    private IMqttTopicService topicService;

    public void subscribe(GatewayManager gateway) {
        String drc = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + "%s" + TopicConst.DRC + TopicConst.UP;
        topicService.subscribe(String.format(drc, gateway.getGatewaySn()));
    }
}
