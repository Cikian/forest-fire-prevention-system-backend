package org.jeecg.modules.slfh.task.dronetask.dto;

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
import java.util.List;
import java.util.Map;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Data
@TableName("slfh_drone_task")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_drone_task对象", description="任务表")
public class SlfhDroneTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;

	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
    private String name;
	/**执行模式（立即执行/循环执行）*/
	@Dict(dicCode = "execution_mode")
	@Excel(name = "执行模式（立即执行/循环执行）", width = 15)
    @ApiModelProperty(value = "执行模式（立即执行/循环执行）")
    private Integer executionMode;
	/**任务类型：巡检/航拍/应急…（可选字段）*/
    @Dict(dicCode = "task_type")
	@Excel(name = "任务类型：巡检/航拍/应急…（可选字段）", width = 15)
    @ApiModelProperty(value = "任务类型：巡检/航拍/应急…（可选字段）")
    private Integer taskType;
	/**优先级（可选字段）*/
	@Excel(name = "优先级（可选字段）", width = 15)
    @ApiModelProperty(value = "优先级（可选字段）")
    private String priority;
	/**任务类型（PLAN计划 / ADHOC临时）*/
	@Dict(dicCode = "source_type")
	@Excel(name = "任务类型（PLAN计划 / ADHOC临时）", width = 15)
    @ApiModelProperty(value = "任务类型（PLAN计划 / ADHOC临时）")
    private Integer sourceType;
	/**任务id（计划任务填，临时任务不填）*/
	@Excel(name = "任务id（计划任务填，临时任务不填）", width = 15)
    @ApiModelProperty(value = "任务id（计划任务填，临时任务不填）")
    private String planId;
	/**无人机id*/
	@Excel(name = "无人机id", width = 15)
    @ApiModelProperty(value = "无人机id")
    private String droneId;
	/**下发对象id*/
	@Excel(name = "下发对象id", width = 15)
    @ApiModelProperty(value = "下发对象id")
    private String operatorId;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15)
    @ApiModelProperty(value = "开始时间")
    private String startTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15)
    @ApiModelProperty(value = "完成时间")
    private String endTime;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;

	// 航线id列表
	private List<String> routeIds;

}
