package org.jeecg.modules.dji.map.model.param;

import org.jeecg.modules.dji.map.model.dto.FlightAreaContent;
import org.jeecg.modules.dji.cloudapi.flightarea.GeofenceTypeEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
public class PostFlightAreaParam {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private GeofenceTypeEnum type;

    @NotNull
    @Valid
    private FlightAreaContent content;
}
