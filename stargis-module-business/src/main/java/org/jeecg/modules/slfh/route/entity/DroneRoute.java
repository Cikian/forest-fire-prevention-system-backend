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

@ApiModel(value = "slfh_drone_route对象", description = "航线表")
@Data
@TableName("slfh_drone_route")
public class DroneRoute implements Serializable {
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
     * 航线名称
     */
    @Excel(name = "航线名称", width = 15)
    @ApiModelProperty(value = "航线名称")
    private String routeName;
    /**
     * 航线名称
     */
    @Excel(name = "所属组织", width = 15)
    @ApiModelProperty(value = "所属组织")
    private String organization;
    /**
     * 路线数据
     */
    @Excel(name = "路线数据", width = 15)
    @ApiModelProperty(value = "路线数据")
    private String waypoints;
    /**
     * 起飞点
     */
    @Excel(name = "起飞点", width = 15)
    @ApiModelProperty(value = "起飞点")
    private String takeOff;
    /**
     * 航线算法
     */
    @Excel(name = "航线算法", width = 15)
    @ApiModelProperty(value = "航线算法")
    private String algorithm;
    /**
     * 高度类型
     */
    @Excel(name = "高度类型", width = 15)
    @ApiModelProperty(value = "高度类型")
    private String routeHeightType;
    /**
     * 起飞高度
     */
    @Excel(name = "起飞高度", width = 15)
    @ApiModelProperty(value = "起飞高度")
    private Double takeOffAltitude;
    /**
     * 返航高度
     */
    @Excel(name = "返航高度", width = 15)
    @ApiModelProperty(value = "返航高度")
    private Double returnAltitude;
    /**
     * 全局飞行速度
     */
    @Excel(name = "全局飞行速度", width = 15)
    @ApiModelProperty(value = "全局飞行速度")
    private Double flightSpeed;
    /**
     * 拍照设置
     */
    @Excel(name = "拍照设置", width = 15)
    @ApiModelProperty(value = "拍照设置")
    private String photoConf;
    /**
     * 直播设置
     */
    @Excel(name = "直播设置", width = 15)
    @ApiModelProperty(value = "直播设置")
    private String liveConf;
    /**
     * 直播设置
     */
    @Excel(name = "直播设置", width = 15)
    @ApiModelProperty(value = "直播设置")
    private String outcontrolAction;
    /**
     * 航线类型
     */
    @Excel(name = "航线类型", width = 15)
    @ApiModelProperty(value = "航线类型")
    private String waylineType;
    /**
     * 旁向重叠率
     */
    @Excel(name = "旁向重叠率", width = 15)
    @ApiModelProperty(value = "旁向重叠率")
    private Integer lateralOverlap;
    /**
     * 航向重叠率
     */
    @Excel(name = "航向重叠率", width = 15)
    @ApiModelProperty(value = "航向重叠率")
    private Integer headingOverlap;
    /**
     * 相对被摄地面高度
     */
    @Excel(name = "相对被摄地面高度", width = 15)
    @ApiModelProperty(value = "相对被摄地面高度")
    private Integer groundHeight;
    /**
     * 拍照模式
     */
    @Excel(name = "拍照模式", width = 15)
    @ApiModelProperty(value = "拍照模式")
    private String photoMode;
    /**
     * 机场（是否需要）
     */
    @Excel(name = "机场", width = 15)
    @ApiModelProperty(value = "机场")
    private String airport;
}
