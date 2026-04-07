package org.jeecg.modules.dji.cloudapi.control;

import org.jeecg.modules.dji.cloudapi.device.PayloadIndex;
import org.jeecg.modules.dji.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class PayloadAuthorityGrabRequest extends BaseModel {

    @NotNull
    private PayloadIndex payloadIndex;

    public PayloadAuthorityGrabRequest() {
    }

    @Override
    public String toString() {
        return "PayloadAuthorityGrabRequest{" +
                "payloadIndex=" + payloadIndex +
                '}';
    }

    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    public PayloadAuthorityGrabRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }
}
