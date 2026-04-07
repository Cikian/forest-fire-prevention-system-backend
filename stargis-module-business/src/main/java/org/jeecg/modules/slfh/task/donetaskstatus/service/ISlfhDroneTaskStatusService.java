package org.jeecg.modules.slfh.task.donetaskstatus.service;

import org.jeecg.modules.slfh.task.donetaskstatus.entity.SlfhDroneTaskStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 任务运行态表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
public interface ISlfhDroneTaskStatusService extends IService<SlfhDroneTaskStatus> {

    int getTaskState(String taskId);

    Map<String, Integer> getTaskStateMap(List<String> taskIds);

    void addStatus(String taskId);
}
