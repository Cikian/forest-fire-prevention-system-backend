package org.jeecg.modules.dji.control.model.dto;

import org.jeecg.modules.dji.control.service.impl.RemoteDebugHandler;
import org.jeecg.modules.dji.cloudapi.device.SwitchActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmState extends RemoteDebugHandler {

    private SwitchActionEnum action;

}
