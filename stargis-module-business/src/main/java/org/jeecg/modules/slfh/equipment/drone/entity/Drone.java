package org.jeecg.modules.slfh.equipment.drone.entity;

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
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:20
 */

@Data
@TableName("slfh_equipment_drone")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "slfh_equipment_drone对象", description = "设备管理-无人机")
public class Drone implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**
     * 无人机名称
     */
    @Excel(name = "无人机名称", width = 15)
    @ApiModelProperty(value = "无人机名称")
    private String name;
    /**
     * 所属组织
     */
    @Excel(name = "所属组织", width = 15)
    @ApiModelProperty(value = "所属组织")
    private String zz;
    /**
     * 无人机序列号
     */
    @Excel(name = "无人机序列号", width = 15)
    @ApiModelProperty(value = "无人机序列号")
    private String xlId;
    /**
     * 绑定时间
     */
    @Excel(name = "绑定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "绑定时间")
    private Date bindingTime;
    /**
     * 设备状态
     */
    @Excel(name = "设备状态", width = 15)
    @ApiModelProperty(value = "设备状态")
    @Dict(dicCode = "dev_status")
    private String status;
}
