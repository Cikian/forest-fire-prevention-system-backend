package org.jeecg.modules.slfh.route.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.slfh.route.entity.DronePoint;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

public interface IDronePointService extends IService<DronePoint> {

    /**
     * 通过主表id查询子表数据
     *
     * @param mainId 主表id
     * @return List<DronePoint>
     */
    public List<DronePoint> selectByMainId(String mainId);
}
