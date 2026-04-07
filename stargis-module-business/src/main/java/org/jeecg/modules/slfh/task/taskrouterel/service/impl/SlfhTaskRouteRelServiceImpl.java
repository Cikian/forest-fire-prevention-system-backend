package org.jeecg.modules.slfh.task.taskrouterel.service.impl;

import cn.hutool.core.date.DateTime;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.slfh.task.taskrouterel.mapper.SlfhTaskRouteRelMapper;
import org.jeecg.modules.slfh.task.taskrouterel.relenum.TaskStateEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.jeecg.modules.slfh.task.droneroute.entity.SlfhDroneRoute;
import org.jeecg.modules.slfh.task.droneroute.service.ISlfhDroneRouteService;
import org.jeecg.modules.slfh.task.droneroute.vo.SlfhDroneRouteVO;
import org.jeecg.modules.slfh.task.taskrouterel.entity.SlfhTaskRouteRel;
import org.jeecg.modules.slfh.task.taskrouterel.service.ISlfhTaskRouteRelService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 航线排班表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Service
public class SlfhTaskRouteRelServiceImpl extends ServiceImpl<SlfhTaskRouteRelMapper, SlfhTaskRouteRel> implements ISlfhTaskRouteRelService {

    @Autowired
    private ISlfhDroneRouteService routeService;

    @Override
    public List<SlfhDroneRouteVO> getRouteVOsByTaskId(String taskId) {
        // 1) 查关联表（只拿 routeId 就够了）
        List<SlfhTaskRouteRel> rels = this.lambdaQuery()
                .eq(SlfhTaskRouteRel::getTaskId, taskId)
                .list();

        if (rels == null || rels.isEmpty()) {
            return Collections.emptyList();
        }

        // 2) 提取 routeIds（去重）
        List<String> routeIds = rels.stream()
                .map(SlfhTaskRouteRel::getRouteId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (routeIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 3) 批量查 routes
        List<SlfhDroneRoute> routes = routeService.listByIds(routeIds);
        if (routes == null || routes.isEmpty()) {
            return Collections.emptyList();
        }

        // 4) routeId -> route 映射（用于保持关联关系 & 快速定位）
        Map<String, SlfhDroneRoute> routeMap = routes.stream()
                .collect(Collectors.toMap(SlfhDroneRoute::getId, r -> r, (a, b) -> a));

        // 5) 组装 VO（按关联表顺序输出；route 不存在则跳过）
        List<SlfhDroneRouteVO> res = new ArrayList<>(rels.size());
        for (SlfhTaskRouteRel rel : rels) {
            SlfhDroneRoute route = routeMap.get(rel.getRouteId());
            if (route == null) {
                continue;
            }
            SlfhDroneRouteVO vo = new SlfhDroneRouteVO();
            BeanUtils.copyProperties(route, vo);
            res.add(vo);
        }
        return res;
    }

    @Override
    public Map<String, List<SlfhDroneRouteVO>> getRouteVOsByTaskIds(List<String> taskIds) {
        if (taskIds == null || taskIds.isEmpty()) {
            return Collections.emptyMap();
        }

        // 1) 查关联表：task_id IN (...)
        List<SlfhTaskRouteRel> rels = this.lambdaQuery()
                .in(SlfhTaskRouteRel::getTaskId, taskIds)
                // 如果你有排序字段，比如 sort/seq，建议加上：
                // .orderByAsc(SlfhTaskRouteRel::getSort)
                .list();

        if (rels == null || rels.isEmpty()) {
            return Collections.emptyMap();
        }

        // 2) 提取 routeIds 去重
        List<String> routeIds = rels.stream()
                .map(SlfhTaskRouteRel::getRouteId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (routeIds.isEmpty()) {
            return Collections.emptyMap();
        }

        // 3) 批量查 routes
        List<SlfhDroneRoute> routes = routeService.listByIds(routeIds);
        if (routes == null || routes.isEmpty()) {
            return Collections.emptyMap();
        }

        // 4) routeId -> route 映射
        Map<String, SlfhDroneRoute> routeMap = routes.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        SlfhDroneRoute::getId,
                        r -> r,
                        (a, b) -> a
                ));

        // 5) 按 taskId 组装 VO 列表（按 rel 顺序添加）
        Map<String, List<SlfhDroneRouteVO>> result = new HashMap<>();
        for (SlfhTaskRouteRel rel : rels) {
            String taskId = rel.getTaskId();
            String routeId = rel.getRouteId();
            if (taskId == null || routeId == null) {
                continue;
            }

            SlfhDroneRoute route = routeMap.get(routeId);
            if (route == null) {
                continue; // 关联存在但路线被删/不存在，直接跳过
            }

            SlfhDroneRouteVO vo = new SlfhDroneRouteVO();
            BeanUtils.copyProperties(route, vo);

            result.computeIfAbsent(taskId, k -> new ArrayList<>()).add(vo);
        }

        return result;
    }

    @Override
    public void addRel(String taskId, List<String> routeIds) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        for (String routeId : routeIds) {
            SlfhTaskRouteRel taskRouteRel = new SlfhTaskRouteRel();
            taskRouteRel.setTaskId(taskId);
            taskRouteRel.setRouteId(routeId);
            taskRouteRel.setExecOrder(routeIds.indexOf(routeId));
            taskRouteRel.setCreateTime(new DateTime());
            taskRouteRel.setCreateBy(sysUser != null ? sysUser.getUsername() : "admin");
            taskRouteRel.setTaskState(TaskStateEnum.PENDING.getCode());
            this.save(taskRouteRel);
        }
    }


}
