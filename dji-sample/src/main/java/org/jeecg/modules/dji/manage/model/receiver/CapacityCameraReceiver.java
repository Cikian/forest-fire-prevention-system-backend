package org.jeecg.modules.dji.manage.model.receiver;

import org.jeecg.modules.dji.cloudapi.device.PayloadIndex;
import lombok.Data;

import java.util.List;

/**
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
public class CapacityCameraReceiver {

    private Integer availableVideoNumber;

    private Integer coexistVideoNumberMax;

    private PayloadIndex cameraIndex;

    private List<CapacityVideoReceiver> videoList;

}