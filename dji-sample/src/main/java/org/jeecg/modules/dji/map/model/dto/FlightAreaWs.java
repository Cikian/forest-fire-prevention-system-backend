package org.jeecg.modules.dji.map.model.dto;

import org.jeecg.modules.dji.map.model.enums.FlightAreaOpertaionEnum;
import org.jeecg.modules.dji.cloudapi.flightarea.GeofenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sean
 * @version 1.9
 * @date 2023/12/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightAreaWs {

    private FlightAreaOpertaionEnum operation;

    private String areaId;

    private String name;

    private GeofenceTypeEnum type;

    private FlightAreaContent content;

    private Boolean status;

    private String username;

    private Long createTime;

    private Long updateTime;

}
