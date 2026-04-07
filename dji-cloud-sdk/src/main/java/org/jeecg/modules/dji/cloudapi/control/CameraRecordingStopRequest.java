package org.jeecg.modules.dji.cloudapi.control;

import org.jeecg.modules.dji.cloudapi.device.PayloadIndex;
import org.jeecg.modules.dji.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class CameraRecordingStopRequest extends BaseModel {

    @NotNull
    private PayloadIndex payloadIndex;

    public CameraRecordingStopRequest() {
    }

    @Override
    public String toString() {
        return "CameraRecordingStopRequest{" +
                "payloadIndex=" + payloadIndex +
                '}';
    }

    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    public CameraRecordingStopRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }
}
