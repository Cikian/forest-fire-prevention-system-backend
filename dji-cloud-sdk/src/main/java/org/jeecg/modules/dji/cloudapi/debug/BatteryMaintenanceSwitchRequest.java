package org.jeecg.modules.dji.cloudapi.debug;

import org.jeecg.modules.dji.cloudapi.device.SwitchActionEnum;
import org.jeecg.modules.dji.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class BatteryMaintenanceSwitchRequest extends BaseModel {

    @NotNull
    private SwitchActionEnum action;

    public BatteryMaintenanceSwitchRequest() {
    }

    @Override
    public String toString() {
        return "BatteryMaintenanceSwitchRequest{" +
                "action=" + action +
                '}';
    }

    public SwitchActionEnum getAction() {
        return action;
    }

    public BatteryMaintenanceSwitchRequest setAction(SwitchActionEnum action) {
        this.action = action;
        return this;
    }
}
