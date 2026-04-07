package org.jeecg.modules.slfh.task.dronetasklog.entity;

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
 * @Description: 任务日志表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Data
@TableName("slfh_drone_task_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="slfh_drone_task_log对象", description="任务日志表")
public class SlfhDroneTaskLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**任务id*/
	@Excel(name = "任务id", width = 15)
    @ApiModelProperty(value = "任务id")
    private String taskId;
	/**排班id*/
	@Excel(name = "排班id", width = 15)
    @ApiModelProperty(value = "排班id")
    private String relId;
	/**航线id*/
	@Excel(name = "航线id", width = 15)
    @ApiModelProperty(value = "航线id")
    private String routeId;
	/**事件：任务开始 TASK_START /航线起飞 ROUTE_START /航线结束 ROUTE_END /异常 ERROR /停止 STOP*/
    @Dict(dicCode = "task_log_event")
	@Excel(name = "事件：任务开始 TASK_START /航线起飞 ROUTE_START /航线结束 ROUTE_END /异常 ERROR /停止 STOP", width = 15)
    @ApiModelProperty(value = "事件：任务开始 TASK_START /航线起飞 ROUTE_START /航线结束 ROUTE_END /异常 ERROR /停止 STOP")
    private int eventType;
	/**INFO/WARN*/
    @Dict(dicCode = "task_log_level")
	@Excel(name = "INFO/WARN", width = 15)
    @ApiModelProperty(value = "INFO/WARN")
    private int level;
	/**信息*/
	@Excel(name = "信息", width = 15)
    @ApiModelProperty(value = "信息")
    private String msg;
}
