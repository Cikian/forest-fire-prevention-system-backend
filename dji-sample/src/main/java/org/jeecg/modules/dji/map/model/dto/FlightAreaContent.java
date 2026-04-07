package org.jeecg.modules.dji.map.model.dto;

import org.jeecg.modules.dji.cloudapi.map.ElementGeometryType;
import org.jeecg.modules.dji.cloudapi.map.ElementProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaContent {

    @NotNull
    @Valid
    private ElementProperty properties;

    @NotNull
    @Valid
    private ElementGeometryType geometry;

}
