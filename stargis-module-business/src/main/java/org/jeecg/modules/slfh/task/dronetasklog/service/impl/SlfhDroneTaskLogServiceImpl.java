package org.jeecg.modules.slfh.task.dronetasklog.service.impl;

import org.jeecg.modules.slfh.task.dronetasklog.entity.SlfhDroneTaskLog;
import org.jeecg.modules.slfh.task.dronetasklog.logenum.TaskLogEventEnum;
import org.jeecg.modules.slfh.task.dronetasklog.logenum.TaskLogLevelEnum;
import org.jeecg.modules.slfh.task.dronetasklog.mapper.SlfhDroneTaskLogMapper;
import org.jeecg.modules.slfh.task.dronetasklog.service.ISlfhDroneTaskLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 任务日志表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Service
public class SlfhDroneTaskLogServiceImpl extends ServiceImpl<SlfhDroneTaskLogMapper, SlfhDroneTaskLog> implements ISlfhDroneTaskLogService {

    @Override
    public boolean logTaskStart(String taskId, String msg) {
        return log(taskId, null, null,
                TaskLogEventEnum.TASK_START.getCode(),
                TaskLogLevelEnum.INFO.getCode(),
                msg);
    }

    @Override
    public boolean logRouteStart(String taskId, String relId, String routeId, String msg) {
        return log(taskId, relId, routeId,
                TaskLogEventEnum.ROUTE_START.getCode(),
                TaskLogLevelEnum.INFO.getCode(),
                msg);
    }

    @Override
    public boolean logRouteEnd(String taskId, String relId, String routeId, String msg) {
        return log(taskId, relId, routeId,
                TaskLogEventEnum.ROUTE_END.getCode(),
                TaskLogLevelEnum.INFO.getCode(),
                msg);
    }

    @Override
    public boolean logError(String taskId, String relId, String routeId, String msg) {
        return log(taskId, relId, routeId,
                TaskLogEventEnum.ERROR.getCode(),
                TaskLogLevelEnum.ERROR.getCode(),
                msg);
    }

    // 任务停止
    @Override
    public boolean logStop(String taskId, String msg) {
        return log(taskId, null, null,
                TaskLogEventEnum.STOP.getCode(),
                TaskLogLevelEnum.WARN.getCode(),
                msg);
    }

    // 航线停止
    @Override
    public boolean logStop(String taskId, String relId, String routeId, String msg) {
        return log(taskId, relId, routeId,
                TaskLogEventEnum.STOP.getCode(),
                TaskLogLevelEnum.WARN.getCode(),
                msg);
    }

    @Override
    public boolean log(String taskId, String relId, String routeId, int eventType, int level, String msg) {
        // 参数兜底
        if (taskId == null || taskId.trim().isEmpty()) {
            throw new IllegalArgumentException("taskId is required");
        }
        if (eventType <= 0) {
            throw new IllegalArgumentException("eventType is required");
        }

        if (level <= 0) {
            level = TaskLogLevelEnum.INFO.getCode();
        }


        SlfhDroneTaskLog log = new SlfhDroneTaskLog();
        log.setCreateTime(new Date());
        log.setTaskId(taskId);
        log.setRelId(relId);
        log.setRouteId(routeId);
        log.setEventType(eventType);
        log.setLevel(level);
        log.setMsg(msg);

        return this.save(log);
    }

    @Override
    public SlfhDroneTaskLog getLatestByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<SlfhDroneTaskLog> listByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<SlfhDroneTaskLog> listByRelId(String relId) {
        return null;
    }

    @Override
    public List<SlfhDroneTaskLog> listByTaskIdAndEventType(String taskId, String eventType) {
        return null;
    }
}
