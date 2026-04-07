package org.jeecg.modules.dji.manage.service.impl;

import org.jeecg.modules.dji.manage.model.dto.CapacityVideoDTO;
import org.jeecg.modules.dji.manage.model.receiver.CapacityVideoReceiver;
import org.jeecg.modules.dji.manage.service.ICameraVideoService;
import org.jeecg.modules.dji.cloudapi.livestream.VideoTypeEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/19
 */
@Service
public class CameraVideoServiceImpl implements ICameraVideoService {

    @Override
    public CapacityVideoDTO receiver2Dto(CapacityVideoReceiver receiver) {
        if (null == receiver) {
            return null;
        }
        CapacityVideoDTO.CapacityVideoDTOBuilder builder = CapacityVideoDTO.builder()
                .id(UUID.randomUUID().toString())
                .index(receiver.getVideoIndex())
                .type(receiver.getVideoType().getType());
        if (null != receiver.getSwitchableVideoTypes()) {
            builder.switchVideoTypes(receiver.getSwitchableVideoTypes().stream()
                    .map(VideoTypeEnum::getType).collect(Collectors.toList()));
        }
        return builder.build();
    }
}