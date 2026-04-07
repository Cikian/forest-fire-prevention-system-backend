package org.jeecg.modules.slfh.task.dronetask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.slfh.task.dronetask.dto.SlfhDroneTaskDTO;
import org.jeecg.modules.slfh.task.dronetask.entity.SlfhDroneTask;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.slfh.task.dronetask.vo.SlfhDroneTaskVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
public interface ISlfhDroneTaskService extends IService<SlfhDroneTask> {

    IPage<SlfhDroneTaskVO> queryPageList(SlfhDroneTask slfhDroneTask, Integer pageNo, Integer pageSize, HttpServletRequest req);

    void add(SlfhDroneTaskDTO taskDTO);

    void deleteTask(String taskId);

    void deleteBatch(List<String> taskIds);

    void editTask(SlfhDroneTaskDTO dto);
}
