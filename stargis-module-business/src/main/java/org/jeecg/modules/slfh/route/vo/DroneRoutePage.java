package org.jeecg.modules.slfh.route.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecg.modules.slfh.route.entity.DronePoint;
import org.jeecg.modules.slfh.route.entity.DroneRoute;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "slfh_drone_routePage对象", description = "航线表")
public class DroneRoutePage extends DroneRoute {
    @ExcelCollection(name = "航点表")
    @ApiModelProperty(value = "航点表")
    private List<DronePoint> dronePointList;
}
