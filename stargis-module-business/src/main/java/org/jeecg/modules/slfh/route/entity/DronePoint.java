package org.jeecg.modules.slfh.route.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

@ApiModel(value = "slfh_drone_point对象", description = "航点表")
@Data
@TableName("slfh_drone_point")
public class DronePoint implements Serializable {
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
     * 经度
     */
    @Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private java.math.BigDecimal longitude;
    /**
     * 纬度
     */
    @Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
    private java.math.BigDecimal latitude;
    /**
     * 高度
     */
    @Excel(name = "高度", width = 15)
    @ApiModelProperty(value = "高度")
    private java.math.BigDecimal altitude;
    /**
     * 飞行速度
     */
    @Excel(name = "飞行速度", width = 15)
    @ApiModelProperty(value = "飞行速度")
    private Double speed;
    /**
     * 航点类型
     */
    @Excel(name = "航点类型", width = 15)
    @ApiModelProperty(value = "航点类型")
    private String pointType;
    /**
     * 俯仰角
     */
    @Excel(name = "俯仰角", width = 15)
    @ApiModelProperty(value = "俯仰角")
    private Integer pitch;
    /**
     * 偏航角
     */
    @Excel(name = "偏航角", width = 15)
    @ApiModelProperty(value = "偏航角")
    private Integer yaw;
    /**
     * 排序
     */
    @Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer sortOrder;
    /**
     * 外键
     */
    @ApiModelProperty(value = "外键")
    private String mainId;
}
