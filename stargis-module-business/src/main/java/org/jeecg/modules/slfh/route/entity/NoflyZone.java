package org.jeecg.modules.slfh.route.entity;

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
 * @since 2026-04-03 14:50
 */

@Data
@TableName("slfh_nofly_zone")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "slfh_nofly_zone对象", description = "限飞区管理")
public class NoflyZone implements Serializable {
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
     * 区域名称
     */
    @Excel(name = "区域名称", width = 15)
    @ApiModelProperty(value = "区域名称")
    private String zoneName;
    /**
     * 区域类型
     */
    @Excel(name = "区域类型", width = 15, dicCode = "nofly_type")
    @Dict(dicCode = "nofly_type")
    @ApiModelProperty(value = "区域类型")
    private String zoneType;
    /**
     * 状态
     */
    @Excel(name = "状态", width = 15, dicCode = "depart_status")
    @Dict(dicCode = "depart_status")
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 数据类型
     */
    @Excel(name = "数据类型", width = 15, dicCode = "data_type")
    @Dict(dicCode = "data_type")
    @ApiModelProperty(value = "数据类型")
    private String dataType;
    /**
     * geo数据
     */
    @Excel(name = "geo数据", width = 15)
    @ApiModelProperty(value = "geo数据")
    private String geojson;
}
