package org.jeecg.modules.slfh.task.droneroute.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 航线表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Data
@TableName("slfh_drone_route")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_drone_route对象", description="航线表")
public class SlfhDroneRouteVO implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**航线名称*/
	@Excel(name = "航线名称", width = 15)
    @ApiModelProperty(value = "航线名称")
    private String routeName;
	/**航线类型（起飞/作业/返航/自定义）*/
	@Dict(dicCode = "route_type")
	@Excel(name = "航线类型（起飞/作业/返航/自定义）", width = 15)
    @ApiModelProperty(value = "航线类型（起飞/作业/返航/自定义）")
    private int routeType;
	/**路线数据*/
	@Excel(name = "路线数据", width = 15)
    @ApiModelProperty(value = "路线数据")
    private String waypoints;
	/**航线版本*/
	@Excel(name = "航线版本", width = 15)
    @ApiModelProperty(value = "航线版本")
    private String version;
}
