package org.jeecg.modules.slfh.task.dronetasklog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.slfh.task.dronetasklog.entity.SlfhDroneTaskLog;

import java.util.List;

/**
 * @Description: 任务日志表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
public interface ISlfhDroneTaskLogService extends IService<SlfhDroneTaskLog> {

    // 任务开始
    boolean logTaskStart(String taskId, String msg);

    // 航线开始
    boolean logRouteStart(String taskId, String relId, String routeId, String msg);

    // 航线结束
    boolean logRouteEnd(String taskId, String relId, String routeId, String msg);

    // 任务异常
    boolean logError(String taskId, String relId, String routeId, String msg);

    // 任务停止
    boolean logStop(String taskId, String msg);

    // 航线停止
    boolean logStop(String taskId, String relId, String routeId, String msg);

    // 通用写入（不想暴露到 controller 的话也可设为 protected）
    boolean log(String taskId, String relId, String routeId, int eventType, int level, String msg);

    // ========== 常用查询（wrapper） ==========
    SlfhDroneTaskLog getLatestByTaskId(String taskId);
    List<SlfhDroneTaskLog> listByTaskId(String taskId);
    List<SlfhDroneTaskLog> listByRelId(String relId);
    List<SlfhDroneTaskLog> listByTaskIdAndEventType(String taskId, String eventType);
}
