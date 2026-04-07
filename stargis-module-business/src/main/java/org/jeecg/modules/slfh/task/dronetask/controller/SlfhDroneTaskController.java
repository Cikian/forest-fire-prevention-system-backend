package org.jeecg.modules.slfh.task.dronetask.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.slfh.task.dronetask.dto.SlfhDroneTaskDTO;
import org.jeecg.modules.slfh.task.dronetask.entity.SlfhDroneTask;
import org.jeecg.modules.slfh.task.dronetask.service.ISlfhDroneTaskService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.slfh.task.dronetask.vo.SlfhDroneTaskVO;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2026-02-05
 * @Version: V1.0
 */
@Api(tags="任务表")
@RestController
@RequestMapping("/task")
@Slf4j
public class SlfhDroneTaskController extends JeecgController<SlfhDroneTask, ISlfhDroneTaskService> {
	@Autowired
	private ISlfhDroneTaskService slfhDroneTaskService;
	
	/**
	 * 分页列表查询
	 *
	 * @param slfhDroneTask
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "任务表-分页列表查询")
	@ApiOperation(value="任务表-分页列表查询", notes="任务表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SlfhDroneTaskVO>> queryPageList(SlfhDroneTask slfhDroneTask,
														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														HttpServletRequest req) {
		IPage<SlfhDroneTaskVO> pageList = slfhDroneTaskService.queryPageList(slfhDroneTask, pageNo, pageSize, req);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param taskDTO
	 * @return
	 */
	@AutoLog(value = "任务表-添加")
	@ApiOperation(value="任务表-添加", notes="任务表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SlfhDroneTaskDTO taskDTO) {
		slfhDroneTaskService.add(taskDTO);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param slfhDroneTask
	 * @return
	 */
	@AutoLog(value = "任务表-编辑")
	@ApiOperation(value="任务表-编辑", notes="任务表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SlfhDroneTaskDTO dto) {
		try {
			slfhDroneTaskService.editTask(dto);
			return Result.OK("编辑成功");
		} catch (Exception e) {
			log.error("编辑任务失败，dto={}", dto, e);
			return Result.error("编辑失败：" + e.getMessage());
		}
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务表-通过id删除")
	@ApiOperation(value="任务表-通过id删除", notes="任务表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		try {
			if (StringUtils.isBlank(id)) {
				return Result.error("任务ID不能为空");
			}
			slfhDroneTaskService.deleteTask(id);
			return Result.OK("删除成功");
		} catch (Exception e) {
			log.error("删除任务失败，taskId={}", id, e);
			return Result.error("删除失败：" + e.getMessage());
		}
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务表-批量删除")
	@ApiOperation(value="任务表-批量删除", notes="任务表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		try {
			if (StringUtils.isBlank(ids)) {
				return Result.error("任务ID不能为空");
			}

			List<String> taskIds = Arrays.stream(ids.split(","))
					.filter(StringUtils::isNotBlank)
					.map(String::trim)
					.collect(Collectors.toList());

			if (taskIds.isEmpty()) {
				return Result.error("任务ID不能为空");
			}

			slfhDroneTaskService.deleteBatch(taskIds);
			return Result.OK("批量删除成功");
		} catch (Exception e) {
			log.error("批量删除任务失败，ids={}", ids, e);
			return Result.error("批量删除失败：" + e.getMessage());
		}
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "任务表-通过id查询")
	@ApiOperation(value="任务表-通过id查询", notes="任务表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SlfhDroneTask> queryById(@RequestParam(name="id",required=true) String id) {
		SlfhDroneTask slfhDroneTask = slfhDroneTaskService.getById(id);
		if(slfhDroneTask==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(slfhDroneTask);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param slfhDroneTask
    */
    //@RequiresPermissions("org.jeecg.modules.demo:slfh_drone_task:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SlfhDroneTask slfhDroneTask) {
        return super.exportXls(request, slfhDroneTask, SlfhDroneTask.class, "任务表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("slfh_drone_task:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SlfhDroneTask.class);
    }

}
