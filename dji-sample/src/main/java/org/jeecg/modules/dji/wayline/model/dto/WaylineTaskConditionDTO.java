package org.jeecg.modules.dji.wayline.model.dto;

import org.jeecg.modules.dji.cloudapi.wayline.ExecutableConditions;
import org.jeecg.modules.dji.cloudapi.wayline.ReadyConditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean
 * @version 1.3
 * @date 2023/2/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaylineTaskConditionDTO {

    private ReadyConditions readyConditions;

    private ExecutableConditions executableConditions;
}
