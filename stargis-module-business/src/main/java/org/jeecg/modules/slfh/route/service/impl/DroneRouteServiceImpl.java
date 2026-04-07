package org.jeecg.modules.slfh.route.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.slfh.route.entity.DronePoint;
import org.jeecg.modules.slfh.route.entity.DroneRoute;
import org.jeecg.modules.slfh.route.mapper.DronePointMapper;
import org.jeecg.modules.slfh.route.mapper.DroneRouteMapper;
import org.jeecg.modules.slfh.route.service.IDroneRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class DroneRouteServiceImpl extends ServiceImpl<DroneRouteMapper, DroneRoute> implements IDroneRouteService {

    @Autowired
    private DroneRouteMapper routeMapper;
    @Autowired
    private DronePointMapper pointMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(DroneRoute droneRoute, List<DronePoint> dronePointList) {
        routeMapper.insert(droneRoute);
        if (dronePointList != null && !dronePointList.isEmpty()) {
            for (DronePoint entity : dronePointList) {
                //外键设置
                entity.setMainId(droneRoute.getId());
                pointMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(DroneRoute droneRoute, List<DronePoint> dronePointList) {
        routeMapper.updateById(droneRoute);

        //1.先删除子表数据
        pointMapper.deleteByMainId(droneRoute.getId());

        //2.子表数据重新插入
        if (dronePointList != null && !dronePointList.isEmpty()) {
            for (DronePoint entity : dronePointList) {
                //外键设置
                entity.setMainId(droneRoute.getId());
                pointMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        pointMapper.deleteByMainId(id);
        routeMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            pointMapper.deleteByMainId(id.toString());
            routeMapper.deleteById(id);
        }
    }

}
