package org.jeecg.modules.dji.manage.model.dto;

import org.jeecg.modules.dji.control.model.enums.DroneAuthorityEnum;
import org.jeecg.modules.dji.cloudapi.device.ControlSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthorityDTO {

    private String sn;

    private DroneAuthorityEnum type;

    private ControlSourceEnum controlSource;

}
