package org.jeecg.modules.slfh.fxgl.flyairportrecord.entity;

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
 * @Description: 飞行管理-飞行记录-机场
 * @Author: jeecg-boot
 * @Date:   2026-03-11
 * @Version: V1.0
 */
@Data
@TableName("slfh_fly_airport_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_fly_airport_record对象", description="飞行管理-飞行记录-机场")
public class SlfhFlyAirportRecord implements Serializable {
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**机场名称*/
	@Excel(name = "机场名称", width = 15)
    @ApiModelProperty(value = "机场名称")
    private String airportName;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
    private String taskName;
	/**执行对象*/
	@Excel(name = "执行对象", width = 15)
    @ApiModelProperty(value = "执行对象")
    private String executObject;
	/**总里程*/
	@Excel(name = "总里程", width = 15)
    @ApiModelProperty(value = "总里程")
    private Double mileage;
	/**媒体数量*/
	@Excel(name = "媒体数量", width = 15)
    @ApiModelProperty(value = "媒体数量")
    private Double media;
	/**总耗时*/
	@Excel(name = "总耗时", width = 15)
    @ApiModelProperty(value = "总耗时")
    private Double totalTime;
	/**起飞时间*/
	@Excel(name = "起飞时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "起飞时间")
    private Date departTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "完成时间")
    private Date cmpletTime;
	/**机场位置*/
	@Excel(name = "机场位置", width = 15)
    @ApiModelProperty(value = "机场位置")
    private String airportAddress;
	/**巡检图片*/
	@Excel(name = "巡检图片", width = 15)
    @ApiModelProperty(value = "巡检图片")
    private String image;
	/**巡检视频*/
	@Excel(name = "巡检视频", width = 15)
    @ApiModelProperty(value = "巡检视频")
    private String video;
	/**飞行报告*/
	@Excel(name = "飞行报告", width = 15)
    @ApiModelProperty(value = "飞行报告")
    private String flyReport;
    /**航线ID*/
	@Excel(name = "航线ID", width = 15)
    @ApiModelProperty(value = "航线ID")
    private String routeId;
}
