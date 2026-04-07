package org.jeecg.modules.slfh.fxgl.fly.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 飞行管理-事件列表
 * @Author: jeecg-boot
 * @Date:   2026-03-09
 * @Version: V1.0
 */
@Data
@TableName("slfh_fly_list")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_fly_list对象", description="飞行管理-事件列表")
public class SlfhFlyList implements Serializable {
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
	/**事件名称*/
	@Excel(name = "事件名称", width = 15)
    @ApiModelProperty(value = "事件名称")
    private String name;
	/**事件类型*/
	@Excel(name = "事件类型", width = 15, dicCode = "event_type")
	@Dict(dicCode = "event_type")
    @ApiModelProperty(value = "事件类型")
    private String type;
	/**告警位置*/
	@Excel(name = "告警位置", width = 15)
    @ApiModelProperty(value = "告警位置")
    private String address;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private Double longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
    private Double latitude;
	/**问题描述*/
	@Excel(name = "问题描述", width = 15)
    @ApiModelProperty(value = "问题描述")
    private String description;
	/**告警等级*/
	@Excel(name = "告警等级", width = 15, dicCode = "alarm_level")
	@Dict(dicCode = "alarm_level")
    @ApiModelProperty(value = "告警等级")
    private String level;
	/**下发时间*/
	@Excel(name = "下发时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下发时间")
    private Date issuingTime;
	/**处理人*/
	@Excel(name = "处理人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "处理人")
    private String handler;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15, dicCode = "state")
	@Dict(dicCode = "state")
    @ApiModelProperty(value = "处理状态")
    private Integer status;
	/**起飞时间*/
	@Excel(name = "起飞时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "起飞时间")
    private Date departureTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "完成时间")
    private Date completionTime;
	/**任务总耗时*/
	@Excel(name = "任务总耗时", width = 15)
    @ApiModelProperty(value = "任务总耗时")
    private Double duration;
	/**任务总里程*/
	@Excel(name = "任务总里程", width = 15)
    @ApiModelProperty(value = "任务总里程")
    private Double mileage;
	/**机场位置*/
	@Excel(name = "机场位置", width = 15)
    @ApiModelProperty(value = "机场位置")
    private String airportAdress;
	/**巡检视频*/
	@Excel(name = "巡检视频", width = 15)
    @ApiModelProperty(value = "巡检视频")
    private String video;
	/**巡检图片*/
	@Excel(name = "巡检图片", width = 15)
    @ApiModelProperty(value = "巡检图片")
    private String image;
	/**设备*/
	@Excel(name = "设备", width = 15)
    @ApiModelProperty(value = "设备")
    private String equip;
	/**设备ID*/
	@Excel(name = "设备ID", width = 15)
    @ApiModelProperty(value = "设备ID")
    private String equipId;
}
