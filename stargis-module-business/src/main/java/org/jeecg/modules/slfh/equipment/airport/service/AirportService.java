package org.jeecg.modules.slfh.equipment.airport.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.slfh.equipment.airport.entity.Airport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:20
 */

public interface AirportService extends IService<Airport> {
    JSONObject getHomeStatistics();
}
