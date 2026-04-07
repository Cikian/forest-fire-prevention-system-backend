package org.jeecg.modules.dji.manage.model.param;

import org.jeecg.modules.dji.cloudapi.log.LogModuleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
public class DeviceLogsGetParam {

    @JsonProperty("domain_list")
    List<LogModuleEnum> domainList;
}
