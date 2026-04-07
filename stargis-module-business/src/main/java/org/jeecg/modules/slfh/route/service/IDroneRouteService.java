package org.jeecg.modules.slfh.route.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.slfh.route.entity.DronePoint;
import org.jeecg.modules.slfh.route.entity.DroneRoute;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

public interface IDroneRouteService extends IService<DroneRoute> {

    /**
     * 添加一对多
     *
     * @param droneRoute
     * @param dronePointList
     */
    public void saveMain(DroneRoute droneRoute, List<DronePoint> dronePointList);

    /**
     * 修改一对多
     *
     * @param droneRoute
     * @param dronePointList
     */
    public void updateMain(DroneRoute droneRoute, List<DronePoint> dronePointList);

    /**
     * 删除一对多
     *
     * @param id
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     *
     * @param idList
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

}
