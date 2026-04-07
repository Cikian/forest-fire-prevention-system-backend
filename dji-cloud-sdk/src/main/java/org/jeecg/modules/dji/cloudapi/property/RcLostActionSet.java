package org.jeecg.modules.dji.cloudapi.property;

import org.jeecg.modules.dji.cloudapi.device.RcLostActionEnum;
import org.jeecg.modules.dji.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public class RcLostActionSet extends BaseModel {

    @NotNull
    @JsonProperty("rc_lost_action")
    private RcLostActionEnum rcLostAction;

    public RcLostActionSet() {
    }

    @Override
    public String toString() {
        return "RcLostActionSet{" +
                "rcLostAction=" + rcLostAction +
                '}';
    }

    public RcLostActionEnum getRcLostAction() {
        return rcLostAction;
    }

    public RcLostActionSet setRcLostAction(RcLostActionEnum rcLostAction) {
        this.rcLostAction = rcLostAction;
        return this;
    }
}
