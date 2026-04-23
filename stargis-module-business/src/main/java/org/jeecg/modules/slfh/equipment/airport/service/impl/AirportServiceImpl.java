package org.jeecg.modules.slfh.equipment.airport.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.slfh.common.service.BusCommonService;
import org.jeecg.modules.slfh.equipment.airport.entity.Airport;
import org.jeecg.modules.slfh.equipment.airport.mapper.AirportMapper;
import org.jeecg.modules.slfh.equipment.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-03-29 22:20
 */

@Service
public class AirportServiceImpl extends ServiceImpl<AirportMapper, Airport> implements AirportService {
    @Autowired
    private BusCommonService commonService;

    @Override
    public JSONObject getHomeStatistics() {
        JSONObject res = new JSONObject();
        List<Airport> allAirPorts = this.list();
        if (allAirPorts.isEmpty()) {
            return res;
        }
        List<DictModel> devStatus = commonService.getDictItems("dev_status");
        Map<String, JSONObject> map = new HashMap<>();
        for (DictModel dictModel : devStatus) {
            JSONObject obj = new JSONObject();
            obj.put("name", dictModel.getText());
            obj.put("value", dictModel.getValue());
            obj.put("num", 0);

            map.put(dictModel.getValue(), obj);
        }

        res.put("total", allAirPorts.size());

        for (Airport airport : allAirPorts) {
            JSONObject obj = map.get(airport.getStatus());
            if (obj != null) {
                obj.put("num", obj.getInteger("num") + 1);
            }
        }

        JSONArray arr = new JSONArray();
        arr.addAll(map.values());

        // 遍历map
        res.put("list", arr);

        return res;
    }
}
