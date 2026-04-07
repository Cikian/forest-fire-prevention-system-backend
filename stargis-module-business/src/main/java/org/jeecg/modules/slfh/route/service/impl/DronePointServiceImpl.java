package org.jeecg.modules.slfh.route.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.slfh.route.entity.DronePoint;
import org.jeecg.modules.slfh.route.mapper.DronePointMapper;
import org.jeecg.modules.slfh.route.service.IDronePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:16
 */

@Service
public class DronePointServiceImpl extends ServiceImpl<DronePointMapper, DronePoint> implements IDronePointService {

    @Autowired
    private DronePointMapper pointMapper;

    @Override
    public List<DronePoint> selectByMainId(String mainId) {
        return pointMapper.selectByMainId(mainId);
    }
}
