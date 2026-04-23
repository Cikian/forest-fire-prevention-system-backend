package org.jeecg.modules.slfh.fxgl.fly.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.slfh.common.service.BusCommonService;
import org.jeecg.modules.slfh.equipment.airport.entity.Airport;
import org.jeecg.modules.slfh.fxgl.fly.entity.SlfhFlyList;
import org.jeecg.modules.slfh.fxgl.fly.mapper.SlfhFlyListMapper;
import org.jeecg.modules.slfh.fxgl.fly.service.ISlfhFlyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 飞行管理-事件列表
 * @Author: jeecg-boot
 * @Date:   2026-03-09
 * @Version: V1.0
 */
@Service
public class SlfhFlyListServiceImpl extends ServiceImpl<SlfhFlyListMapper, SlfhFlyList> implements ISlfhFlyListService {
    @Autowired
    private BusCommonService commonService;

    @Override
    public JSONObject getStatistics(Date startTime, Date endTime) {
        JSONObject res = new JSONObject();
        
        QueryWrapper<SlfhFlyList> queryWrapper = new QueryWrapper<>();
        if (startTime != null) {
            queryWrapper.ge("departure_time", startTime);
        }
        if (endTime != null) {
            queryWrapper.le("departure_time", endTime);
        }
        
        List<SlfhFlyList> allList = this.list(queryWrapper);

        List<DictModel> devStatus = commonService.getDictItems("event_type");
        Map<String, JSONObject> map = new HashMap<>();
        for (DictModel dictModel : devStatus) {
            JSONObject obj = new JSONObject();
            obj.put("name", dictModel.getText());
            obj.put("value", dictModel.getValue());
            obj.put("num", 0);

            map.put(dictModel.getValue(), obj);
        }

        res.put("total", allList.size());

        for (SlfhFlyList event : allList) {
            JSONObject obj = map.get(event.getType());
            if (obj != null) {
                obj.put("num", obj.getInteger("num") + 1);
            }
        }

        JSONArray arr = new JSONArray();
        arr.addAll(map.values());

        res.put("list", arr);

        return res;
    }
}
