package org.jeecg.modules.slfh.equipment.airport.entity;

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
@TableName("slfh_equipment_airport")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "slfh_equipment_airport对象", description = "设备管理-机场")
public class Airport implements Serializable {
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
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
    /**
     * 设备名称
     */
    @Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private String name;
    /**
     * 设备位置
     */
    @Excel(name = "设备位置", width = 15)
    @ApiModelProperty(value = "设备位置")
    private String location;
    /**
     * 设备序列号
     */
    @Excel(name = "设备序列号", width = 15)
    @ApiModelProperty(value = "设备序列号")
    private String xlId;
    /**
     * 所属组织
     */
    @Excel(name = "所属组织", width = 15)
    @ApiModelProperty(value = "所属组织")
    private String zz;
    /**
     * 固件版本号
     */
    @Excel(name = "固件版本号", width = 15)
    @ApiModelProperty(value = "固件版本号")
    private String version;
    /**
     * 固件升级
     */
    @Excel(name = "固件升级", width = 15)
    @ApiModelProperty(value = "固件升级")
    private String sj;
    /**
     * 设备状态
     */
    @Excel(name = "设备状态", width = 15)
    @ApiModelProperty(value = "设备状态")
    @Dict(dicCode = "dev_status")
    private String status;
}
