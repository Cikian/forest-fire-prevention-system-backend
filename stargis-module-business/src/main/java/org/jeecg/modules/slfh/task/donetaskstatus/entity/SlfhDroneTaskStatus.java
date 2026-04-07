package org.jeecg.modules.slfh.task.donetaskstatus.entity;

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
 * @Description: 任务运行态表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Data
@TableName("slfh_drone_task_status")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_drone_task_status对象", description="任务运行态表")
public class SlfhDroneTaskStatus implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**任务id*/
	@Excel(name = "任务id", width = 15)
    @ApiModelProperty(value = "任务id")
    private String taskId;
	/**整体状态：未运行 READY/运行中 RUNNING/完成 SUCCESS/失败 FAILED/停止 STOPPED*/
	@Dict(dicCode = "run_state")
	@Excel(name = "运行状态：准备 READY/运行中 RUNNING/完成 SUCCESS/失败 FAILED/停止 STOPPED/待执行", width = 15)
    @ApiModelProperty(value = "整体状态：准备 READY/运行中 RUNNING/完成 SUCCESS/失败 FAILED/停止 STOPPED/待执行")
    private Integer runState;
	/**当前排班排到谁了*/
	@Excel(name = "当前排班排到谁了", width = 15)
    @ApiModelProperty(value = "当前排班排到谁了")
    private String currentRelId;
	/**当前这个是组里的第几个*/
	@Excel(name = "当前这个是组里的第几个", width = 15)
    @ApiModelProperty(value = "当前这个是组里的第几个")
    private String currentExecOrder;
	/**当前航线id*/
	@Excel(name = "当前航线id", width = 15)
    @ApiModelProperty(value = "当前航线id")
    private String currentRouteId;
	/**下一个排班*/
	@Excel(name = "下一个排班", width = 15)
    @ApiModelProperty(value = "下一个排班")
    private String nextRelId;
	/**下一个航线*/
	@Excel(name = "下一个航线", width = 15)
    @ApiModelProperty(value = "下一个航线")
    private String nextRouteId;
	/**任务实际开始*/
	@Excel(name = "任务实际开始", width = 15)
    @ApiModelProperty(value = "任务实际开始")
    private String startTime;
	/**任务实际结束*/
	@Excel(name = "任务实际结束", width = 15)
    @ApiModelProperty(value = "任务实际结束")
    private String endTime;
	/**失败原因*/
	@Excel(name = "失败原因", width = 15)
    @ApiModelProperty(value = "失败原因")
    private String failReason;
}
