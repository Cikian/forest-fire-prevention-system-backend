package org.jeecg.modules.slfh.task.taskrouterel.service;

import org.jeecg.modules.slfh.task.droneroute.vo.SlfhDroneRouteVO;
import org.jeecg.modules.slfh.task.taskrouterel.entity.SlfhTaskRouteRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 航线排班表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
public interface ISlfhTaskRouteRelService extends IService<SlfhTaskRouteRel> {

    List<SlfhDroneRouteVO> getRouteVOsByTaskId(String id);

    Map<String, List<SlfhDroneRouteVO>> getRouteVOsByTaskIds(List<String> taskIds);

    void addRel(String taskId, List<String> routeIds);
}
