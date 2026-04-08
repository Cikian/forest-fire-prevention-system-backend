package org.jeecg.modules.slfh.task.dronetask.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.slfh.task.donetaskstatus.entity.SlfhDroneTaskStatus;
import org.jeecg.modules.slfh.task.droneroute.entity.SlfhDroneRoute;
import org.jeecg.modules.slfh.task.droneroute.service.ISlfhDroneRouteService;
import org.jeecg.modules.slfh.task.dronetask.dto.SlfhDroneTaskDTO;
import org.jeecg.modules.slfh.task.dronetask.entity.SlfhDroneTask;
import org.jeecg.modules.slfh.task.dronetask.mapper.SlfhDroneTaskMapper;
import org.jeecg.modules.slfh.task.dronetasklog.entity.SlfhDroneTaskLog;
import org.jeecg.modules.slfh.task.dronetasklog.service.ISlfhDroneTaskLogService;
import org.jeecg.modules.slfh.task.taskrouterel.entity.SlfhTaskRouteRel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.jeecg.modules.slfh.task.donetaskstatus.service.ISlfhDroneTaskStatusService;
import org.jeecg.modules.slfh.task.droneroute.vo.SlfhDroneRouteVO;
import org.jeecg.modules.slfh.task.dronetask.service.ISlfhDroneTaskService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.slfh.task.dronetask.vo.SlfhDroneTaskVO;
import org.jeecg.modules.slfh.task.taskrouterel.service.ISlfhTaskRouteRelService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Service
public class SlfhDroneTaskServiceImpl extends ServiceImpl<SlfhDroneTaskMapper, SlfhDroneTask> implements ISlfhDroneTaskService {

    @Autowired
    private ISlfhTaskRouteRelService relService;

    @Autowired
    private ISlfhDroneTaskStatusService statusService;

    @Autowired
    private ISlfhDroneTaskLogService logService;

    @Autowired
    private ISlfhDroneRouteService routeService;

    @Override
    public IPage<SlfhDroneTaskVO> queryPageList(SlfhDroneTask slfhDroneTask,
                                                Integer pageNo,
                                                Integer pageSize,
                                                HttpServletRequest req) {
        // 先取出 name
        String name = slfhDroneTask.getName();
        // 清空，避免 QueryGenerator 生成 name=xxx
        slfhDroneTask.setName(null);

        QueryWrapper<SlfhDroneTask> queryWrapper =
                QueryGenerator.initQueryWrapper(slfhDroneTask, req.getParameterMap());

        // 再加 name like
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.lambda().like(SlfhDroneTask::getName, name);
        }

        Page<SlfhDroneTask> page = new Page<>(pageNo, pageSize);
        IPage<SlfhDroneTask> pageList = this.page(page, queryWrapper);

        List<SlfhDroneTask> records = pageList.getRecords();
        if (records == null || records.isEmpty()) {
            // 返回空分页
            Page<SlfhDroneTaskVO> empty = new Page<>(pageNo, pageSize);
            empty.setTotal(pageList.getTotal());
            empty.setRecords(Collections.emptyList());
            return empty;
        }

        // 1) 取本页所有 taskIds
        List<String> taskIds = records.stream()
                .map(SlfhDroneTask::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 2) 批量查 routes：taskId -> routeVO list
        Map<String, List<SlfhDroneRouteVO>> routeMap = relService.getRouteVOsByTaskIds(taskIds);
        // 3) 批量查 state：taskId -> runState
        Map<String, Integer> stateMap = statusService.getTaskStateMap(taskIds);

        // 4) 组装 VO
        List<SlfhDroneTaskVO> res = new ArrayList<>(records.size());
        for (SlfhDroneTask record : records) {
            SlfhDroneTaskVO vo = new SlfhDroneTaskVO();
            BeanUtils.copyProperties(record, vo);

            vo.setRouteList(routeMap.getOrDefault(record.getId(), Collections.emptyList()));
            vo.setRunState(stateMap.getOrDefault(record.getId(), 0)); // 0换成你们默认状态

            res.add(vo);
        }

        // 5) 返回 VO 分页（保留 total、current、size）
        Page<SlfhDroneTaskVO> voPage = new Page<>(pageList.getCurrent(), pageList.getSize());
        voPage.setTotal(pageList.getTotal());
        voPage.setRecords(res);
        return voPage;
    }

    @Override
    @Transactional
    public void add(SlfhDroneTaskDTO taskDTO) {
        // 存任务
        SlfhDroneTask slfhDroneTask = new SlfhDroneTask();
        BeanUtils.copyProperties(taskDTO, slfhDroneTask);
        slfhDroneTask.setCreateTime(new DateTime());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        slfhDroneTask.setCreateBy(sysUser != null ? sysUser.getUsername() : "admin");
        //slfhDroneTask.setSysOrgCode(sysUser != null ? sysUser.getOrgCode() : null);//change by njli 2026.4.1此处应该存设备所属组织。
        this.save(slfhDroneTask);
        // 存关联
        String taskId = slfhDroneTask.getId();
        List<String> routeIds = taskDTO.getRouteIds();
        relService.addRel(taskId, routeIds);
        // 存状态
        statusService.addStatus(taskId);
    }

    @Override
    public void deleteTask(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            throw new RuntimeException("任务ID不能为空");
        }

        // 1️⃣ 校验任务是否存在
        SlfhDroneTask task = this.getById(taskId);
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }

        // 2️⃣ 校验任务状态（不能删除运行中的任务）
        Integer runState = statusService.getTaskState(taskId);
        if (runState != null && runState == 1) { // 1 = RUNNING
            throw new RuntimeException("任务正在执行中，禁止删除");
        }

        // 3️⃣ 删除任务-航线关系
        relService.remove(new LambdaQueryWrapper<SlfhTaskRouteRel>()
                .eq(SlfhTaskRouteRel::getTaskId, taskId));

        // 4️⃣ 删除任务状态
        statusService.remove(new LambdaQueryWrapper<SlfhDroneTaskStatus>()
                .eq(SlfhDroneTaskStatus::getTaskId, taskId));

        // 5️⃣ 删除日志
        logService.remove(new LambdaQueryWrapper<SlfhDroneTaskLog>()
                .eq(SlfhDroneTaskLog::getTaskId, taskId));

        // 6️⃣ 删除任务本体
        this.removeById(taskId);
    }

    @Override
    public void deleteBatch(List<String> taskIds) {
        if (CollectionUtils.isEmpty(taskIds)) {
            return;
        }

        // 1️⃣ 查运行中任务
        Map<String, Integer> stateMap = statusService.getTaskStateMap(taskIds);

        List<String> runningTasks = taskIds.stream()
                .filter(id -> stateMap.getOrDefault(id, 0) == 1)
                .collect(Collectors.toList());

        if (!runningTasks.isEmpty()) {
            throw new RuntimeException("存在运行中的任务，禁止删除：" + runningTasks);
        }

        // 2️⃣ 批量删除
        relService.remove(new LambdaQueryWrapper<SlfhTaskRouteRel>()
                .in(SlfhTaskRouteRel::getTaskId, taskIds));

        statusService.remove(new LambdaQueryWrapper<SlfhDroneTaskStatus>()
                .in(SlfhDroneTaskStatus::getTaskId, taskIds));

        logService.remove(new LambdaQueryWrapper<SlfhDroneTaskLog>()
                .in(SlfhDroneTaskLog::getTaskId, taskIds));

        this.removeByIds(taskIds);
    }

    /**
     * 编辑任务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editTask(SlfhDroneTaskDTO dto) {
        if (dto == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        if (StringUtils.isBlank(dto.getId())) {
            throw new RuntimeException("任务ID不能为空");
        }

        // 1. 校验任务是否存在
        SlfhDroneTask task = this.getById(dto.getId());
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }

        // 2. 运行中的任务禁止编辑
        Integer taskState = statusService.getTaskState(dto.getId());
        if (taskState != null && taskState == 1) {
            throw new RuntimeException("任务正在执行中，禁止编辑");
        }

        // 3. 更新任务主表
        SlfhDroneTask updateTask = new SlfhDroneTask();
        BeanUtils.copyProperties(dto, updateTask);

        // 如果你的实体里有 updateTime / updateBy，可在这里补
        updateTask.setUpdateTime(new Date());

        boolean updated = this.updateById(updateTask);
        if (!updated) {
            throw new RuntimeException("更新任务失败");
        }

        // 4. 更新任务-航线关联
        // 策略：先删旧关联，再按 routeIds 重建
        relService.remove(new LambdaQueryWrapper<SlfhTaskRouteRel>()
                .eq(SlfhTaskRouteRel::getTaskId, dto.getId()));

        if (CollectionUtils.isNotEmpty(dto.getRouteIds())) {
            List<SlfhTaskRouteRel> relList = new ArrayList<>();

            for (int i = 0; i < dto.getRouteIds().size(); i++) {
                String routeId = dto.getRouteIds().get(i);
                if (StringUtils.isBlank(routeId)) {
                    continue;
                }

                SlfhDroneRoute route = routeService.getById(routeId);
                if (route == null) {
                    throw new RuntimeException("航线不存在，routeId=" + routeId);
                }

                SlfhTaskRouteRel rel = new SlfhTaskRouteRel();
                rel.setTaskId(dto.getId());
                rel.setRouteId(routeId);
                rel.setExecOrder(i + 1);
                rel.setStage(convertRouteTypeToStage(route.getRouteType()));
                rel.setCreateTime(new Date());

                relList.add(rel);
            }

            if (!relList.isEmpty()) {
                relService.saveBatch(relList);
            }
        }
    }

    /**
     * 航线类型 -> 任务阶段
     * route_type：1起飞 2作业 3返航 4自定义
     * stage：1起飞 2作业 3返航
     */
    private Integer convertRouteTypeToStage(Integer routeType) {
        if (routeType == null) {
            return 2;
        }
        switch (routeType) {
            case 1:
                return 1; // TAKEOFF
            case 3:
                return 3; // RETURN
            case 2:
            case 4:
            default:
                return 2; // WORK
        }
    }

}
