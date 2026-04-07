package org.jeecg.modules.slfh.task.donetaskstatus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.modules.slfh.task.donetaskstatus.mapper.SlfhDroneTaskStatusMapper;
import org.jeecg.modules.slfh.task.donetaskstatus.entity.SlfhDroneTaskStatus;
import org.jeecg.modules.slfh.task.donetaskstatus.service.ISlfhDroneTaskStatusService;
import org.jeecg.modules.slfh.task.donetaskstatus.taskstatusenum.RunStateEnum;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 任务运行态表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Service
public class SlfhDroneTaskStatusServiceImpl extends ServiceImpl<SlfhDroneTaskStatusMapper, SlfhDroneTaskStatus> implements ISlfhDroneTaskStatusService {

    @Override
    public int getTaskState(String taskId) {
        LambdaQueryWrapper<SlfhDroneTaskStatus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SlfhDroneTaskStatus::getTaskId, taskId);
        SlfhDroneTaskStatus status = this.getOne(lambdaQueryWrapper);
        return status == null ? 0 : status.getRunState();
    }

    @Override
    public Map<String, Integer> getTaskStateMap(List<String> taskIds) {
        if (taskIds == null || taskIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<SlfhDroneTaskStatus> list = this.lambdaQuery()
                .select(SlfhDroneTaskStatus::getTaskId, SlfhDroneTaskStatus::getRunState)
                .in(SlfhDroneTaskStatus::getTaskId, taskIds)
                .list();

        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Integer> res = new HashMap<>();
        for (SlfhDroneTaskStatus s : list) {
            if (s == null || s.getTaskId() == null) continue;
            res.putIfAbsent(s.getTaskId(), s.getRunState());
        }
        return res;
    }

    @Override
    public void addStatus(String taskId) {
        SlfhDroneTaskStatus status = new SlfhDroneTaskStatus();
        status.setTaskId(taskId);
        status.setRunState(RunStateEnum.PENDING.getCode());
        this.save(status);
    }

}
