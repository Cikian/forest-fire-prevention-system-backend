package org.jeecg.modules.slfh.task.droneroute.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class SlfhDroneRoute implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**航线名称*/
	@Excel(name = "航线名称", width = 15)
    @ApiModelProperty(value = "航线名称")
    private String routeName;
	/**航线类型（起飞/作业/返航/自定义）*/
	@Dict(dicCode = "route_type")
	@Excel(name = "航线类型（起飞/作业/返航/自定义）", width = 15)
    @ApiModelProperty(value = "航线类型（起飞/作业/返航/自定义）")
    private Integer routeType;
	/**路线数据*/
	@Excel(name = "路线数据", width = 15)
    @ApiModelProperty(value = "路线数据")
    private String waypoints;
	/**航线版本*/
	@Excel(name = "航线版本", width = 15)
    @ApiModelProperty(value = "航线版本")
    private String version;
}
