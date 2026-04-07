package org.jeecg.modules.dji.cloudapi.debug;

import org.jeecg.modules.dji.cloudapi.device.BatteryStoreModeEnum;
import org.jeecg.modules.dji.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class BatteryStoreModeSwitchRequest extends BaseModel {

    @NotNull
    private BatteryStoreModeEnum action;

    public BatteryStoreModeSwitchRequest() {
    }

    @Override
    public String toString() {
        return "BatteryStoreModeSwitchRequest{" +
                "action=" + action +
                '}';
    }

    public BatteryStoreModeEnum getAction() {
        return action;
    }

    public BatteryStoreModeSwitchRequest setAction(BatteryStoreModeEnum action) {
        this.action = action;
        return this;
    }
}
