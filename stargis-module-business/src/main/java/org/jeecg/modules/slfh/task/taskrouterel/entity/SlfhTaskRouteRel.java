package org.jeecg.modules.slfh.task.taskrouterel.entity;

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
 * @Description: 航线排班表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Data
@TableName("slfh_task_route_rel")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_task_route_rel对象", description="航线排班表")
public class SlfhTaskRouteRel implements Serializable {
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
	/**任务id*/
	@Excel(name = "任务id", width = 15)
    @ApiModelProperty(value = "任务id")
    private String taskId;
	/**航线id*/
	@Excel(name = "航线id", width = 15)
    @ApiModelProperty(value = "航线id")
    private String routeId;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
    private Integer execOrder;
	/**阶段（起飞 TAKEOFF/作业 WORK/返航 RETURN）*/
	@Dict(dicCode = "task_router_stage")
	@Excel(name = "阶段（起飞 TAKEOFF/作业 WORK/返航 RETURN）", width = 15)
    @ApiModelProperty(value = "阶段（起飞 TAKEOFF/作业 WORK/返航 RETURN）")
    private int stage;
	/**任务状态（未启动 PENDING/运行中 RUNNING/成功 SUCCESS/失败 FAILED/跳过 SKIPPED）*/
	@Dict(dicCode = "task_state")
	@Excel(name = "任务状态（未启动 PENDING/运行中 RUNNING/成功 SUCCESS/失败 FAILED/跳过 SKIPPED）", width = 15)
    @ApiModelProperty(value = "任务状态（未启动 PENDING/运行中 RUNNING/成功 SUCCESS/失败 FAILED/跳过 SKIPPED）")
    private int taskState;
}
