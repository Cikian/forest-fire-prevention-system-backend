package org.jeecg.modules.dji.map.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
@TableName("flight_area_property")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightAreaPropertyEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("element_id")
    private String elementId;

    @TableField("type")
    private String type;

    @TableField("enable")
    private Boolean enable;

    @TableField("sub_type")
    private String subType;

    @TableField("radius")
    private Integer radius;

}
